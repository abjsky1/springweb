package example.day043_260905_spring.Spring_practice4.service;

import org.springframework.stereotype.Service;

import example.day043_260905_spring.Spring_practice4.model.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CourseService {

    private final CourseRepository courseRepository;

    
}
