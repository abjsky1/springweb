package example.day040_260902_spring;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor  // final 필드만 포함한 생성자 자동 생성 
public class ExamController {

//  HTTP ==>> Controller ==>> Service ==>> Repository ==>> Entity ==>> Repository ==>> Service ==>> Controller ==>> HTTP

//  * 서비스 객체 (주입)불러오기
//  @Autowired   사용가능 but @RequiredArgsConstructor 지우고. final 권장.
    private final ExamService examService;

//  [1] 전체조회 : http://localhost:8080/day040_260902_spring/exam
    @GetMapping("/day040_260902_spring/exam")
    public List<ExamEntity> findAll(){

        // 서비스 호출
        return examService.findAll();

    }

//  [2] 저장 : 
    @PostMapping("/day040_260902_spring/exam")
    public boolean save( @RequestBody ExamEntity entity ){
        
    //  서비스 호출
        return examService.save(entity);

    }
// 
// 
// 
// 
// 
// 
// 
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
