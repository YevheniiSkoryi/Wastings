package com.example.money.dto.infrastracture;

import com.example.money.dto.binding.JsonDateTimeDeserializer;
import com.example.money.dto.binding.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyPerPeriod {

    @JsonProperty("amountOnStartOfMonth")
    private Long amountOnStartOfMonth;

    @JsonProperty("startDate")
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime startDate;

    @JsonProperty("amountOnCurrentDay")
    private Long amountOnCurrentDay;

    @JsonProperty("wastingDTOS")
    private List<WastingDTO> wastingDTOS;


}
