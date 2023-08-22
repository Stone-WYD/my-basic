package com.njxnet.yccc.util;

import com.njxnet.framework.common.utils.DESUtil;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

/**
 * @program: HNCC
 * @description: 配置文件配置加密类
 * @author: Stone
 * @create: 2023-07-24 13:41
 **/
@Component("myStringEncryptor")
public class MyStringEncryptor implements StringEncryptor {


    @Override
    public String encrypt(String message) {
        return DESUtil.encrypt(Constants.secretKey, message);
    }

    @Override
    public String decrypt(String encryptedMessage) {
        return DESUtil.decrypt(Constants.secretKey, encryptedMessage);
    }
}
