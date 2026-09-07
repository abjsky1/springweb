package example.day043_260907_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260907_spring.Spring_practice4.model.dto.StudentDto;
import example.day043_260907_spring.Spring_practice4.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/student")
public class StudentController {

    private final StudentService studentService;


//  [등록]
    @PostMapping("")
    public boolean save(@RequestBody StudentDto studentDto){
        
        return studentService.save(studentDto);
    }


//  [삭제]
    @DeleteMapping("")
    public boolean delete(@RequestParam(name = "studentId") Integer studentId){

        return studentService.delete(studentId);
    }


}
