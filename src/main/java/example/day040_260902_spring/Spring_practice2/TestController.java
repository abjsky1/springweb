package example.day040_260902_spring.Spring_practice2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("Spring_practice2")
public class TestController {

    private final TestService testService;

//  [1] 게시물 등록
    @PostMapping("")
    public boolean save(@RequestBody TestEntity testEntity){
        System.out.println("TestController.testWrite()");

        return testService.save(testEntity);
    }

//  [2] 게시물 전체 조회
    @GetMapping("")
    public List<TestEntity> findAll(){
        System.out.println("TestController.testPrint()");
        
        return testService.findAll();
    }

//  [3] 게시물 개별 조회
    @GetMapping("/detail")
    public TestEntity testDetail(@RequestParam(name = "no") int no){
        System.out.println("TestController.testDetail()");

        return testService.testDetail(no);
    }

//  [4] 게시물 삭제
    @DeleteMapping("/{no}")
    public boolean delete(@PathVariable(name = "no") int no){
        System.out.println("TestController.testDelete()");

        return testService.delete(no);
    }


//  [5] 게시물 수정
    @PutMapping("")
    public boolean testUpdate(@RequestBody TestEntity testEntity){
        System.out.println("TestController.testUpdate()");

        return testService.update(testEntity);
    }

// 
// 
// 
// 
// 
// 
// 
// 

}
