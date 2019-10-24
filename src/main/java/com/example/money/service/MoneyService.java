package com.example.money.service;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.dto.WastingDTO;
import com.example.money.entity.Money;
import com.example.money.entity.Person;
import com.example.money.entity.Wasting;
import com.example.money.repository.MoneyRepository;
import com.example.money.repository.UserRepository;
import com.example.money.repository.WastingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoneyService {

    private final UserRepository userRepository;
    private final WastingRepository wastingRepository;
    private final MoneyRepository moneyRepository;

    public MoneyPerPeriod getMoneyOnCurrentDay(final LocalDateTime time, final String userName) {
        final LocalDateTime startDate = LocalDateTime.of(time.getYear(), time.getMonth(), 1, 0, 0);

        final Person person = userRepository.findById(userName)
                .orElseThrow(() -> new ErrorException("Person " + userName + " not found", ErrorType.PERSON_NOT_FOUND));

        final Money money = moneyRepository.findByCurrentDateAndPerson(startDate, person)
                .orElseThrow(() -> new ErrorException("money is not found on " + startDate, ErrorType.MONEY_NOT_FOUND));

        final List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetween(startDate, time);

        final Long finalAmount = money.getValue() + wastings.stream().map(Wasting::getValue).reduce((long) 0, Long::sum);

        return new MoneyPerPeriod(
                money.getValue(),
                startDate,
                finalAmount,
                wastings.stream()
                        .map(wasting ->
                                new WastingDTO(
                                        wasting.getPerson().getId(),
                                        wasting.getTimePaying(),
                                        wasting.getValue(),
                                        wasting.getDescription()
                                )
                        ).collect(Collectors.toList()
                )
        );
    }


    public String addWastingAndRecalculateMoney(final WastingDTO wastingDTO) {

        final Person person = userRepository.findById(wastingDTO.getUserName())
                .orElseThrow(() -> new ErrorException(
                        "Person " + wastingDTO.getUserName() + " not found", ErrorType.PERSON_NOT_FOUND));

        person.addWasting(wastingDTO);

        person.getMonies().stream()
                .filter(money -> money.getCurrentDate().isAfter(wastingDTO.getTimePaying()))
                .forEach(money -> money.setValue(money.getValue() + wastingDTO.getValue()));

        userRepository.save(person);
        return wastingDTO.getUserName();
    }


}
