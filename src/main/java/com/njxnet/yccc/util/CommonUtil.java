package com.njxnet.yccc.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @program: HNCC
 * @description: 类型转换工具类
 * @author: Stone
 * @create: 2023-07-21 14:28
 **/
public class CommonUtil {

    public static Long localDateTimeToLong(LocalDateTime time){
        Long result = null;
        if (time!=null){
            result = Timestamp.valueOf(time).getTime() / 1000;
        }
        return result;
    }
}
