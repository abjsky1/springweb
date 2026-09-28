package example.day052_260918_spring.spring_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication 
@EnableJpaAuditing 
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}

/*
    스프링에서 외부 API 정보 가져오기
        
        - Start.spring.io 접속

        - Add dependencies -> Spring Reactive Web 검색

        - Explore

        - implementation 'org.springframework.boot:spring-boot-starter-webflux' 복사

        - build.gradle 파일에 붙여넣기
*/