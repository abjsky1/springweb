package example.day040_260902_spring.Spring_practice1;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Component  // 스프링 컨테이너 빈(객체) 등록
// @Controller  // HTTP 서블릿 + @Component
@RestController  // restful 웹 서비스 제공하는 컨트롤러를 스프링 빈에 등록하고 메소드의 반환값을 VIEW 가 아닌 HTTP 응답 본문에 담아 전달하는 어노테이션
// @ResponseBody + @controller
@RequestMapping("/test") // 클래스 또는 매소드 레벨에서 요청 URI 와 HTTP 요청 방식을 특정 컨트롤러/메소드에 매핑하는 어노테이션
public class TestController {

//  [1] 게시물 등록
    @PostMapping("")
    public boolean testWrite( TestDto testDto ){
        System.out.println("TestController.testWrite()");
        return true;
    }

//  [2] 게시물 전체 조회
    @GetMapping("")
    public ArrayList<TestDto> testPrint(){
        System.out.println("TestController.testPrint()");
        ArrayList<TestDto> list = new ArrayList<>();
        list.add(new TestDto(1 , "내용1" , "작성자1"));
        list.add(new TestDto(2 , "내용2" , "작성자2"));
        return list;
    }

//  [3] 게시물 개별 조회
    @GetMapping("/detail")
    public TestDto testDetail(@RequestParam(name = "no") int no){
        System.out.println("TestController.testDetail()");
        return new TestDto(3 , "내용3" , "작성자3");
    }

//  [4] 게시물 삭제
    @DeleteMapping("/{no}")
    public boolean testDelete(@PathVariable(name = "no") int no){
        System.out.println("TestController.testDelete()");
        return true;
    }

//  [5] 게시물 수정
    @PutMapping("")
    public boolean testUpdate(@RequestBody TestDto testDto){
        System.out.println("TestController.testUpdate()");
        return true;
    }
// 
// 
// 
// 
// 
// 


}

@Data  // @Getter @Setter @ToString @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
class TestDto{
    private Integer no;
    private String content;
    private String writer;
}

/*
    - int : 기본타입 +- 21억 저장
    - Integer : 참조타입 (int 래퍼클래스) + null 저장
    주의 : HTTP 파싱하는 경우에 int 에는 Null 값 저장 불가
    권장 : 기본타입 대신에 래퍼타입 사용하자.

    - DTO 에서는 기본타입 말고 대문자로 적는 래퍼타입을 사용할 것.
*/