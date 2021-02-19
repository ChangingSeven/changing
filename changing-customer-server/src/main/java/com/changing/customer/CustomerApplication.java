package com.changing.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-03-17 13:18
 */
@SpringBootApplication
@MapperScan("com.changing.**.mapper")
public class CustomerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CustomerApplication.class);
    }

}