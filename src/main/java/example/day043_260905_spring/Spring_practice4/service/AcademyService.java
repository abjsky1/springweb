package example.day043_260905_spring.Spring_practice4.service;

import org.springframework.stereotype.Service;

import example.day043_260905_spring.Spring_practice4.model.repository.CourseRepository;
import example.day043_260905_spring.Spring_practice4.model.repository.EnrollRepository;
import example.day043_260905_spring.Spring_practice4.model.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AcademyService {

    private final StudentRepository studentRepository;
    private final EnrollRepository enrollRepository;
    private final CourseRepository courseRepository;



}
