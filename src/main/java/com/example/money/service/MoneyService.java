package com.example.money.service;

import com.example.money.dto.MoneyDTO;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.entity.Person;
import com.example.money.entity.Wasting;
import com.example.money.repository.MoneyOnCurrentDayRepository;
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
    private final MoneyOnCurrentDayRepository moneyOnCurrentDayRepository;

    public MoneyPerPeriod getMoneyForPeriod(LocalDateTime time) {
        LocalDateTime month = LocalDateTime.of(time.getYear(), time.getMonth(), 1, 0, 0);

        List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetween(month, time);

//        MoneyOnCurrentDay currentMoney =
//                moneyOnCurrentDayRepository.findByCurrentDate(
//                        LocalDateTime
//                                .of(time.getYear(), time.getMonth(), 1, 0, 0)
//                                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
//                ).orElseThrow(() -> new RuntimeException("didn't find current Money"));

//        Long finishedMoney = currentMoney.getValue() + moneys.stream().map(Money::getValue).reduce((long) 0, Long::sum);


        return new MoneyPerPeriod(
//                finishedMoney,
                0L,
                wastings.stream()
                        .map(wasting ->
                                new MoneyDTO(
                                        wasting.getPerson().getId(),
                                        wasting.getTimePaying(),
                                        wasting.getValue(),
                                        wasting.getDescription()
                                )
                        ).collect(Collectors.toList()
                )
        );
    }


    public String addMoney(final MoneyDTO moneyDTO) {

        final Person person = userRepository.findById(moneyDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        person.addWasting(moneyDTO);
        userRepository.save(person);
        return moneyDTO.getUserName();
    }

    public String calculateMoneyOnMonth(final LocalDateTime date) {
//        final LocalDateTime beginMonth = LocalDateTime
//                .of(date.getYear(), date.getMonth(), 1, 0, 0);
//        final LocalDateTime finishMonth = LocalDateTime
//                .of(date.getYear(), date.getMonth(), date.getMonth().maxLength(), 23, 59);
//
//        final List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetween(beginMonth, finishMonth);
//
//        final LocalDateTime lastDayOfPreviousMonth =
//                date.minusMonths(1).withDayOfMonth(date.minusMonths(1).getMonth().maxLength());
//        final Long previousMonth = lastDayOfPreviousMonth
//                .atZone(ZoneId.systemDefault())
//                .toInstant()
//                .toEpochMilli();
//        Long moneyOnLastDayPreviousMonth = moneyOnCurrentDayRepository.findByCurrentDate(previousMonth)
//                .orElseThrow(() -> new RuntimeException("wasnt found money on " + lastDayOfPreviousMonth)
//                )
//                .getValue();
//
//        LocalDateTime startDay;
//        LocalDateTime endDay;
//        for (int i = 1; i <= date.getMonth().maxLength(); i++) {
//            startDay = LocalDateTime.of(date.getYear(), date.getMonth(), i, 0, 0, 0);
//            endDay = LocalDateTime.of(date.getYear(), date.getMonth(), i, 23, 59, 59);
//            Long startDayLong = startDay
//                    .atZone(ZoneId.systemDefault())
//                    .toInstant()
//                    .toEpochMilli();
//            Long endDayLong = endDay
//                    .atZone(ZoneId.systemDefault())
//                    .toInstant()
//                    .toEpochMilli();
//
//            moneyOnLastDayPreviousMonth -= wastings.stream()
//                    .filter(wasting -> wasting.getTimePaying() < endDayLong && wasting.getTimePaying() > startDayLong)
//                    .map(Wasting::getValue).reduce((long)0, Long::sum);
//            moneyOnCurrentDayRepository.save(new MoneyOnCurrentDay(startDayLong, moneyOnLastDayPreviousMonth));
//        }
        return "success";

    }
}
