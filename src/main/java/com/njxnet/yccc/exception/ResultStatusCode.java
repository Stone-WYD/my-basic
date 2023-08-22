package com.njxnet.yccc.exception;

/**
 * 返回状态码枚举
 */
public enum ResultStatusCode {
    SUCCESS(0, "操作成功"),
    FAIL(1, "操作失败"),
    ERROR(500, "服务器内部错误"),
    TOKEN_NOT_FOUND(1000,"用户token信息不存在"),
    UNKNOWN_FILE(1011, "未知文件"),
    FILE_UPLOAD_FAIL(1012, "文件上传失败"),
    CAPTCHA_ERROR(2001, "验证码输入错误"),
    LOGIN_ERROR(2002, "用户名或者密码错误"),
    NO_RIGHT_TO_DO_IT(2003, "对不起，您没有权限执行本操作"),
    LOGIN_FREEZE(2004,"账号已被冻结"),
    MISSING_PARAM(2005, "参数缺失"),
    USER_EXIST(2006, "本法院或其他法院已存在该用户名"),
    NEW_MODULE(3001,"新建短信模板失败"),
    UPDATE_MODULE(3002,"修改短信模板失败"),
    UPDATE_MODULE_2(3003,"无法修改接口平台短信模板"),
    DELETE_MODULE(3004,"删除短信模板失败"),
    MODULE_TYPE_REPEAT(3005, "短信模板的模板id已存在"),
    MODULE_TYPE_NOT_FIND(3006, "未能查询到短信模板的内容"),
    PHONES_EMPTY_ERROR(4001, "没有可使用的手机号，可能原因：手机号存在于黑名单中"),
    BLACKLIST_REPEAT(4002, "该号码已添加至黑名单"),
    ;

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String name;

    ResultStatusCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
