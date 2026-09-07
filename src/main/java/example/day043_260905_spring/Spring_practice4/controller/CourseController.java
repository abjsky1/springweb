package example.day043_260905_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260905_spring.Spring_practice4.model.repository.CourseRepository;
import example.day043_260905_spring.Spring_practice4.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/course")
public class CourseController {

    private final CourseService courseService;


}
