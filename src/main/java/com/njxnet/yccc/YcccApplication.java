package com.njxnet.yccc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.njxnet.yccc.dao"})
public class YcccApplication {

    public static void main(String[] args) {
        SpringApplication.run(YcccApplication.class, args);
    }

}
