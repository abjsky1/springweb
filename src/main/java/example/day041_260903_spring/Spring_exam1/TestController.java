package example.day041_260903_spring.Spring_exam1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController{

    private final TestService testService;


//  [1] 전체조회 : http://localhost:8080/test
    @GetMapping("/test")
    public List<TestDto> 전체조회(){
        return testService.전체조회();
    }


//  [2] 등록 : http://localhost:8080/test
//      body : {"name" : "과자" , "descri" : "콰삭" , "price" : 1700 }
    @PostMapping("/test")
    public boolean 저장(@RequestBody TestDto testDto){
        return testService.저장(testDto);
    }


//  [3] 수정 : http://localhost:8080/test
//      body : { "no" : 1 , "descri" : "펩시보다 맛있는 콜라" , "price" : 3000 }
    @PutMapping("/test")
    public boolean 수정(@RequestBody TestDto testDto){
        return testService.수정(testDto);
    }


}
