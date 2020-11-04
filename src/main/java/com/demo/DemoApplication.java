package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @GetMapping("/hello")
    public String sayHello() {

        logger.debug("hello debug - sak - 2");
        logger.info("hello info - sak - 2");
        logger.warn("hello warn - sak - 2");
        logger.error("hello error - sak - 2");
        return String.format("Hello test - sak - 2");
    }
}
