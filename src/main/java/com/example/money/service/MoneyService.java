package com.example.money.service;

import com.example.money.dto.MoneyDTO;
import com.example.money.dto.MoneyPerPeriod;
import com.example.money.entity.MoneyOnCurrentDay;
import com.example.money.entity.Wasting;
import com.example.money.repository.MoneyOnCurrentDayRepository;
import com.example.money.repository.WastingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoneyService {

    private final WastingRepository wastingRepository;
    private final MoneyOnCurrentDayRepository moneyOnCurrentDayRepository;

    public MoneyPerPeriod getMoneyForPeriod(LocalDateTime time) {
        Long month = LocalDateTime.of(time.getYear(), time.getMonth(), 1, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long toDate = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetween(month, toDate);

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
                                        millsToLocalDateTime(wasting.getTimePaying()),
                                        wasting.getValue(),
                                        wasting.getDescription()
                                )
                        ).collect(Collectors.toList()
                )
        );
    }

    private static LocalDateTime millsToLocalDateTime(Long millis) {
        Instant instant = Instant.ofEpochMilli(millis);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public Wasting addMoney(final MoneyDTO moneyDTO) {
        Wasting wasting = new Wasting(moneyDTO.getTimePaying().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), moneyDTO.getValue(), moneyDTO.getDescription());
        return wastingRepository.save(wasting);
    }

    public String calculateMoneyOnMonth(final LocalDateTime date) {
        final Long beginMonth = LocalDateTime
                .of(date.getYear(), date.getMonth(), 1, 0, 0)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        final Long finishMonth = LocalDateTime
                .of(date.getYear(), date.getMonth(), date.getMonth().maxLength(), 23, 59)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

        final List<Wasting> wastings = wastingRepository.findAllByTimePayingIsBetween(beginMonth, finishMonth);

        final LocalDateTime lastDayOfPreviousMonth =
                date.minusMonths(1).withDayOfMonth(date.minusMonths(1).getMonth().maxLength());
        final Long previousMonth = lastDayOfPreviousMonth
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        Long moneyOnLastDayPreviousMonth = moneyOnCurrentDayRepository.findByCurrentDate(previousMonth)
                .orElseThrow(() -> new RuntimeException("wasnt found money on " + lastDayOfPreviousMonth)
                )
                .getValue();

        LocalDateTime startDay;
        LocalDateTime endDay;
        for (int i = 1; i <= date.getMonth().maxLength(); i++) {
            startDay = LocalDateTime.of(date.getYear(), date.getMonth(), i, 0, 0, 0);
            endDay = LocalDateTime.of(date.getYear(), date.getMonth(), i, 23, 59, 59);
            Long startDayLong = startDay
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();
            Long endDayLong = endDay
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();

            moneyOnLastDayPreviousMonth -= wastings.stream()
                    .filter(wasting -> wasting.getTimePaying() < endDayLong && wasting.getTimePaying() > startDayLong)
                    .map(Wasting::getValue).reduce((long)0, Long::sum);
            moneyOnCurrentDayRepository.save(new MoneyOnCurrentDay(startDayLong, moneyOnLastDayPreviousMonth));
        }
        return "success";

    }
}
