package com.example.graduation;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.graduation.sys.mapper")
@Log4j2
public class GraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationApplication.class, args);
    }

}
