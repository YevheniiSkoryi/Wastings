package com.example.money.service;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;
import com.example.money.dto.application.MoneyPerPeriodData;
import com.example.money.dto.application.WastingData;
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

    public MoneyPerPeriodData getMoneyOnCurrentDay(final LocalDateTime time, final String userName) {
        final LocalDateTime startDate = LocalDateTime.of(time.getYear(), time.getMonth(), 1, 0, 0);

        final Person person = userRepository.findById(userName)
                .orElseThrow(() -> new ErrorException("Person " + userName + " not found", ErrorType.PERSON_NOT_FOUND));

        final Money money = moneyRepository.findByCurrentDateAndPerson(startDate, person)
                .orElseGet(() -> calculateMoneyOnCurrentMonth(person, startDate));


        final List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetweenAndPerson(startDate, time, person);


        final Long finalAmount = money.getValue() + wastings.stream().map(Wasting::getValue).reduce((long) 0, Long::sum);
        return new MoneyPerPeriodData(
                money.getValue(),
                startDate,
                finalAmount,
                wastings.stream()
                        .map(wasting ->
                                new WastingData(
                                        wasting.getId(),
                                        wasting.getTimePaying(),
                                        wasting.getValue(),
                                        wasting.getDescription()
                                )
                        )
                        .collect(Collectors.toList())
        );
    }


    private Money calculateMoneyOnCurrentMonth(Person person, LocalDateTime startDate) {

        final Money money = moneyRepository.findFirstByPersonAndCurrentDateBeforeOrderByIdDesc(person, startDate)
                .orElseThrow(() -> new ErrorException("money is not found", ErrorType.MONEY_NOT_FOUND));


        final List<Wasting> wastingInPreviousMonth =
                wastingRepository.findAllByTimePayingIsBetweenAndPerson(money.getCurrentDate(), startDate, person);


        final Long finalAmount = money.getValue()
                + wastingInPreviousMonth.stream().map(Wasting::getValue).reduce((long) 0, Long::sum);

        Money moneyOnCurrentMonth = new Money(startDate, finalAmount);
        person.addMoney(moneyOnCurrentMonth);
        userRepository.save(person);
        return moneyOnCurrentMonth;
    }

    public String addWastingAndRecalculateMoney(final WastingData data, final String userName) {

        final Person person = userRepository.findById(userName)
                .orElseThrow(() -> new ErrorException(
                        "Person " + userName + " not found", ErrorType.PERSON_NOT_FOUND));

        person.getMonies().stream()
                .filter(money -> money.getCurrentDate().isAfter(data.getTimePaying()))
                .forEach(money -> money.setValue(money.getValue() + data.getValue()));

        person.addWasting(data);
        userRepository.save(person);
        return userName;
    }


    public WastingData getWastingById(final String userName, final Long wastingId) {

        if (!userRepository.findById(userName).isPresent()) {
            throw new ErrorException("Person " + userName + " not found", ErrorType.PERSON_NOT_FOUND);
        }

        final Wasting wasting = wastingRepository.findById(wastingId)
                .orElseThrow(() -> new ErrorException(
                        "Wasting with id=" + wastingId + " not found",
                        ErrorType.WASTING_NOT_FOUND)
                );

        return new WastingData(
                wasting.getId(),
                wasting.getTimePaying(),
                wasting.getValue(),
                wasting.getDescription()
        );
    }

    public Long deleteWastingById(final String userName, final Long wastingId) {

        if (!userRepository.findById(userName).isPresent()) {
            throw new ErrorException("Person " + userName + " not found", ErrorType.PERSON_NOT_FOUND);
        }

        final Wasting wasting = wastingRepository.findById(wastingId)
                .orElseThrow(() -> new ErrorException(
                        "Wasting with id=" + wastingId + " not found",
                        ErrorType.WASTING_NOT_FOUND)
                );
        wastingRepository.delete(wasting);
        return wastingId;
    }
}
