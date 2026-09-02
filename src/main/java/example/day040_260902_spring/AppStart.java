package example.day040_260902_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 톰캣 자동 구성 , 컴포넌트 스캔 및 설정 기능 포함한 시작점 지정
public class AppStart {

    public static void main(String[] args) {
        
        SpringApplication.run(AppStart.class);

    }
    
}