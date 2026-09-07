package example.day043_260907_spring.Spring_practice4.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.day043_260907_spring.Spring_practice4.model.dto.EnrollDto;
import example.day043_260907_spring.Spring_practice4.model.entity.CourseEntity;
import example.day043_260907_spring.Spring_practice4.model.entity.EnrollEntity;
import example.day043_260907_spring.Spring_practice4.model.entity.StudentEntity;
import example.day043_260907_spring.Spring_practice4.model.repository.CourseRepository;
import example.day043_260907_spring.Spring_practice4.model.repository.EnrollRepository;
import example.day043_260907_spring.Spring_practice4.model.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EnrollService {

    private final EnrollRepository enrollRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


//  [등록]
    @Transactional
    public boolean save(EnrollDto enrollDto){

        EnrollEntity enrollEntity = enrollDto.toEntity();

        Optional<StudentEntity> optional1 = studentRepository.findById(enrollDto.getStudentId());

        Optional<CourseEntity> optional2 = courseRepository.findById(enrollDto.getCourseId());

        if(optional1.isPresent() && optional2.isPresent()){

            StudentEntity studentEntity = optional1.get();

            CourseEntity courseEntity = optional2.get();

            enrollEntity.setStudentEntity(studentEntity);

            enrollEntity.setCourseEntity(courseEntity);

            EnrollEntity savedEnrollEntity = enrollRepository.save(enrollEntity);

            if (savedEnrollEntity.getEnrollId() >= 1) { return true;}

        }
        return false;

    }
    
//  [조회]

    public EnrollDto findDetail(Integer enrollId){

        Optional<EnrollEntity> optional = enrollRepository.findById(enrollId);

    //  없을 때 자료
        optional.orElse(null);

        if (optional.isPresent()) {
            EnrollEntity enrollEntity = optional.get();
            EnrollDto enrollDto = EnrollDto.from(enrollEntity);
            return enrollDto;
        }
        return null;
    }



}
