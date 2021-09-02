package com.huiminpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(builoapiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huiminpay.contruller"))
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo builoapiInfo(){
        Contact zjp = new Contact("zjp", "", "");
        return new ApiInfoBuilder()
                .title("惠民支付API接口文档")
                .description("该文档由后端编写，供前端进行测试使用")
                .version("V1.0.1")
                .contact(zjp)
                .build();
    }


}
