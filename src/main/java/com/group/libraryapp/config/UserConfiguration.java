package com.group.libraryapp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    // 왼쪽에 스프링빈이 나가는 모양과 들어오는 모양이 있다
    // 들어오는 모양의 뜻은 스프링 컨테이너를 관리하는 jdbc 템플릿이 들어온다는 의미
    // 빠져나간다는 모양의 뜻은 스프링 컨테이너가 bean 어노테이션을 통해 등록된
    // 유저 레포지토리를 주입한다는 뜻이다
//    @Bean
//    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
//        return new UserRepository(jdbcTemplate);
//    }
}
