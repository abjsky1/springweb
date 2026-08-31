package example.day039_260831_spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;


// @Component  // 미니프로젝트2 싱글톤 대신 , 스프링 컨테이너에 해당 클래스의 객체(빈) 등록
@Controller // 서블릿 대신 , HTTP 통신을 지원하는 서블릿 제공 + @Component
public class RestController {

//  1. http://localhost:8080/day039_260831_spring/task1
    @GetMapping(value = "/day039_260831_spring/task1") // HTTP 요청 url 매핑/연결
    @ResponseBody // HTTP 응답 : JSON 타입 변환
    public int task1(){
        System.out.println("RestController.task1()");  // soutm + Enter = 현재 메소드명 출력
        return 10;  // Content-Type: application/json
    }

//  2. 
    @GetMapping("/day039_260831_spring/task2")
    @ResponseBody
    public String task2(){
        System.out.println("RestController.task2()");
        return "안녕하세요";  // Content-Type: text/plain;charset=UTF-8
    }

//  3.
    @GetMapping("/day039_260831_spring/task3")
    @ResponseBody
    public Map<String,Object> task3(){ // map 컬렉션 프레임워크 [ { key : value } , { key : value } ]
        Map<String, Object> map = new HashMap<>();
        map.put("김길리", 100);
        map.put("홍길동", 78);
        return map;  // Content-Type: application/json
    }

//  4.
    @GetMapping("/day039_260831_spring/task4")
    @ResponseBody
    public ExamDto task4(){
        ExamDto dto = new ExamDto();
        dto.setName("김길리");
        dto.setAge(22);
        return dto;  // // Content-Type: application/json
    }
    
}

//  dto
@Data  // 롬복 lombok
class ExamDto{ String name; int age; }

/*
    @ResponseBody : 자바의 타입을 HTTP content-type 으로 변환하여 동일하게 사용/직렬화
    - String : text/plain
    - 그 외 : application/json
*/