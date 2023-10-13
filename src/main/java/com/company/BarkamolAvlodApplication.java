package com.company;

import io.swagger.models.HttpMethod;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@SpringBootApplication
@Configuration
public class BarkamolAvlodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarkamolAvlodApplication.class, args);
    }

//    public class ConfigUtil {
//        public static void main(String[] args) {
//
//            Locale locale = new Locale("ru", "RU");
//            ResourceBundle bundle = ResourceBundle.getBundle("Web_application", locale);
//            System.out.println(bundle.getString("news"));
//            System.out.println(bundle.getString("contact.name"));
//
//        }
//    }
}
