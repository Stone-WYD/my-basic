package com.njxnet.yccc.config.constant.mvc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.njxnet.yccc.constant.IEnum;

import java.io.IOException;

public class IEnumJsonSerializer<T extends IEnum> extends JsonSerializer<IEnum> {


    @Override
    public void serialize(IEnum value, JsonGenerator gen, SerializerProvider serializerProvider)
            throws IOException {
        // 将枚举类转换为String，其实就是取其对应的 value
        gen.writeString(value == null ? null : value.getValue());
    }
}
