package example.day039_260831_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  @SpringBootApplication
//  1. 내장톰캣지원 : @EnableAutoConfiguration
//  2. IOC/DI 컴포넌트 등록 : @ComponentScan
//
@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {

    //  AppStart.class : SpringBoot 어노테이션을 포함한 정보. 리플렉션.
        SpringApplication.run(AppStart.class);  
    //  실행 : http://localhost:8080

    }

}
