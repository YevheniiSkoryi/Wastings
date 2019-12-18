package com.example.money.dto.converter;

import com.example.money.dto.application.WastingData;
import com.example.money.dto.binding.JsonDateTimeFormatter;
import com.example.money.dto.infrastracture.WastingDTO;

import java.time.LocalDateTime;


public class WastingDTOConverter {

    public static WastingData convert(WastingDTO wastingDTO) {
        return new WastingData(
                wastingDTO.getId(),
                LocalDateTime.parse(wastingDTO.getTimePaying(), JsonDateTimeFormatter.formatter),
                Long.parseLong(wastingDTO.getValue()),
                wastingDTO.getDescription()
        );
    }

    public static WastingDTO convert(WastingData wastingData) {
        return new WastingDTO(
                wastingData.getId(),
                wastingData.getTimePaying().format(JsonDateTimeFormatter.formatter),
                String.valueOf(wastingData.getValue()),
                wastingData.getDescription()
        );
    }
}
