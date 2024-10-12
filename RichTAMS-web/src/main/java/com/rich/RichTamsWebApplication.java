package com.rich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;


@ServletComponentScan
@SpringBootApplication
public class RichTamsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RichTamsWebApplication.class, args);
    }
}
