package com.example.graduation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: huiyuan
 * @date: 2020/5/6 11:20
 * @desc: 这是描述
 **/
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper ObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }
}
