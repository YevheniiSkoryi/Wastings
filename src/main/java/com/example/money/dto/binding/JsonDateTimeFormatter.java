package com.example.money.dto.binding;

import java.time.format.DateTimeFormatter;

public final class JsonDateTimeFormatter {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            .withResolverStyle(ResolverStyle.STRICT);
}
