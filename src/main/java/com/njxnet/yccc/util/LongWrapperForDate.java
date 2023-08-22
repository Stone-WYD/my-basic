package com.njxnet.yccc.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.njxnet.yccc.config.constant.mvc.LongWrapperForDateJsonDeserializer;
import com.njxnet.yccc.config.constant.mvc.LongWrapperForDateJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @program: HNCC
 * @description: 用Long封装时间
 * @author: Stone
 * @create: 2023-07-21 14:51
 **/
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = LongWrapperForDateJsonSerializer.class)
@JsonDeserialize(using = LongWrapperForDateJsonDeserializer.class)
public class LongWrapperForDate {

    private Long dateTime;
    private LocalDateTime localDateTime;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Long getDateTime() {
        if (dateTime!=null) return dateTime;
        if (localDateTime!=null) dateTime = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        return null;
    }

    public LocalDateTime getLocalDateTime() {
        if (localDateTime!=null) return localDateTime;
        if (dateTime!=null) return LocalDateTime.ofEpochSecond(dateTime, 0, ZoneOffset.ofHours(8));
        return null;
    }

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(
                LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)), 0, ZoneOffset.ofHours(8));
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(dateTimeString);
    }


}
