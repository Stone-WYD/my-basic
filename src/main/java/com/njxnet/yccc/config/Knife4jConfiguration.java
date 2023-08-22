package com.njxnet.yccc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        // 描述字段
                        .title("盐城云柜")
                        .description("盐城高院云柜定制，后台管理系统")
                        .contact(new Contact("南京铉盈", "http://www.njxnet.com/", ""))
                        // 团队地址 .termsOfServiceUrl("")
                        // 联系我们 .contact("")
                        .version("1.0")
                        .build()
                )
                // 分组名称
                // .groupName("springboot-1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.njxnet.yccc.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
}
