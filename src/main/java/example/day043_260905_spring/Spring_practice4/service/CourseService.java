package example.day043_260905_spring.Spring_practice4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import example.day043_260905_spring.Spring_practice4.model.dto.CourseDto;
import example.day043_260905_spring.Spring_practice4.model.dto.StudentDto;
import example.day043_260905_spring.Spring_practice4.model.entity.CourseEntity;
import example.day043_260905_spring.Spring_practice4.model.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CourseService {

    private final CourseRepository courseRepository;


//  [등록]

    public boolean save(CourseDto courseDto){

        CourseEntity courseEntity = courseDto.toEntity();

        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);

        if (savedCourseEntity.getCourseId() >= 1) { return true;}
        return false;

    }


//  [전체조회]
    public List<CourseDto> findAll(){

        List<CourseEntity> courseEntities = courseRepository.findAll();

        List<CourseDto> courseDtos = new ArrayList<>();

        courseEntities.forEach((courseEntity) -> {

            CourseDto courseDto = CourseDto.from(courseEntity);

            courseEntity.getEnrollEntities().forEach((enroll)->{

                StudentDto studentDto = StudentDto.from(enroll.getStudentEntity());

                courseDto.getStudentDtos().add(studentDto);
            });

            courseDtos.add(courseDto); 
        });

        return  courseDtos;

    }



}
