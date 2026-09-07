package example.day043_260905_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260905_spring.Spring_practice4.model.dto.StudentDto;
import example.day043_260905_spring.Spring_practice4.service.StudentService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/student")
public class StudentController {

    private final StudentService studentService;


//  [등록]
    @PostMapping("")
    public boolean save(StudentDto studentDto){
        
        return studentService.save(studentDto);
    }

    


}
