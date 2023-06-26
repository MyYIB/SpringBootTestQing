package com.example.springboottestqing;

import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.List;


@SpringBootApplication
@EnableOpenApi
public class SpringBootTestQingApplication {
    @Autowired
    UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestQingApplication.class, args);
    }

}
