package com.njxnet.yccc.config.constant.mvc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.njxnet.yccc.util.LongWrapperForDate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: HNCC
 * @description: LongWrapperForDate反序列化
 * @author: Stone
 * @create: 2023-07-21 15:01
 **/
public class LongWrapperForDateJsonDeserializer extends JsonDeserializer<LongWrapperForDate> {
    @Override
    public LongWrapperForDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // 包装成 LongWrapperForDate
        LongWrapperForDate result = new LongWrapperForDate();
        final String text = p.getText();
        LocalDateTime localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        result.setLocalDateTime(localDateTime);
        return result;
    }
}
