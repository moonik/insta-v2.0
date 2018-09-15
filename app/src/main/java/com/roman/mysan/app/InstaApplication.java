package com.roman.mysan.app;

import com.roman.mysan.app.user.domain.Authority;
import com.roman.mysan.app.user.domain.AuthorityName;
import com.roman.mysan.app.user.repository.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
public class InstaApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InstaApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InstaApplication.class, args);
    }

    @Bean
    CommandLineRunner employees(AuthorityRepository authorityRepository) {
        return args -> {
            final Authority ROLE_USER = new Authority(AuthorityName.ROLE_USER);
            final Authority ROLE_ADMIN = new Authority(AuthorityName.ROLE_ADMIN);
            final Authority ROLE_CHAT_CREATOR = new Authority(AuthorityName.ROLE_CHAT_CREATOR);
            final Authority ROLE_MODERATOR = new Authority(AuthorityName.ROLE_MODERATOR);
            authorityRepository.saveAll(Arrays.asList(ROLE_USER, ROLE_ADMIN, ROLE_CHAT_CREATOR, ROLE_MODERATOR));
        };
    }
}
