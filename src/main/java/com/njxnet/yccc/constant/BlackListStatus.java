package com.njxnet.yccc.constant;


public enum BlackListStatus implements IEnum{
    USING(1, "已生效"),
    NO_USING(0, "未生效")
    ;

    private final Integer code;
    private final String desc;

    BlackListStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return desc;
    }
}
