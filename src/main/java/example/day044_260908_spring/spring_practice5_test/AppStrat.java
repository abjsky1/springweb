package example.day044_260908_spring.spring_practice5_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication 
@EnableJpaAuditing 
public class AppStrat {
    public static void main(String[] args) {
        SpringApplication.run(AppStrat.class);
    }
}
