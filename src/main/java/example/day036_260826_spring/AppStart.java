package example.day036_260826_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  스프링은 프레임워크이므로 다양한 도구와 틀을 제공 받음.  (도구싸움)

//  @어노테이션 : 코드에 추가적인 설명과 의미 부여할 때 사용 (라벨/주석)

//  1. @SpringBootApplication :

//      1) 내장 톰캣 세팅

//      2) 서블릿(Controller/컴포넌트) 자동 등록

//      3) 등등
//
//
//
//
//
//
//

@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {

    //  2. Spring 실행 , SpringApplication

    //      SpringApplication.run( 현재클래스명.class );

    //      클래스.class : 클래스 메타정보(멤버변수/생성자/메소드) 반환

    //      SpringApplication.run( Springboot 정보 );

            SpringApplication.run(AppStart.class);

    //  3. Ctrl + F5 실행

    //      - 주의 : 2개 이상 실행 불가

    //  4. 실행 확인 : 

    //      - http://127.0.0.1:8080

    //      - http://localhost:8080

    //      - http://IP주소:8080

    //
    //
    //
    //
    //
    //
    //
    //
    //
        

    }


}  // class END
