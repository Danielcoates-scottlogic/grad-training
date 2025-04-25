package com.example.friendface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public GreetingController.EmployeeOfTheMonthService employeeOfTheMonthService() {
        return new GreetingController.EmployeeOfTheMonthService(true);
    }
}
