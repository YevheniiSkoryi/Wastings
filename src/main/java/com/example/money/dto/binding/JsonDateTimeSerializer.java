package com.example.money.dto.binding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

public class JsonDateTimeSerializer extends JsonSerializer<LocalDateTime> {


    @Override
    public void serialize(
            LocalDateTime localDateTime,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeString(localDateTime.format(JsonDateTimeFormatter.formatter));
    }
}
