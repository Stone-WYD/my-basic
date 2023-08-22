package com.njxnet.yccc.config.constant.mvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.njxnet.yccc.util.LongWrapperForDate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LongWrapperForDateJsonSerializer extends JsonSerializer<LongWrapperForDate> {

    @Override
    public void serialize(LongWrapperForDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 将 LongWrapperForDate 转换为String
        String resultString = null;
        LocalDateTime localDateTime = value.getLocalDateTime();
        if (localDateTime != null){
           resultString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        gen.writeString(resultString);
    }
}
