package com.example.pf_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.pf_management.mapper")
public class PfManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfManagementApplication.class, args);
    }

}
