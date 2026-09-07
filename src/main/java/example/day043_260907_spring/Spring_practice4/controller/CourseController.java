package example.day043_260907_spring.Spring_practice4.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260907_spring.Spring_practice4.model.dto.CourseDto;
import example.day043_260907_spring.Spring_practice4.model.repository.CourseRepository;
import example.day043_260907_spring.Spring_practice4.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/course")
public class CourseController {

    private final CourseService courseService;


//  [등록]
    @PostMapping("")
    public boolean save(@RequestBody CourseDto courseDto){

        return courseService.save(courseDto);
    }


//  [전체조회]
    @GetMapping("")
    public List<CourseDto> findAll(){

        return courseService.findAll();
    }




}
