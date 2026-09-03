package example.day041_260903_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // Spring Boot 애플리케이션에 JPA Auditing(등록/수정) 감시 기능을 활성화하는 트리거 어노테이션. AppStart 클래스 상단에 선언.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
