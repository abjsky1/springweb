package example.day043_260907_spring.Spring_practice4.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.day043_260907_spring.Spring_practice4.model.dto.StudentDto;
import example.day043_260907_spring.Spring_practice4.model.entity.StudentEntity;
import example.day043_260907_spring.Spring_practice4.model.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor   
public class StudentService {

    private final StudentRepository studentRepository;


//  [등록]

    public boolean save(StudentDto studentDto){

        StudentEntity studentEntity = studentDto.toEntity();

        StudentEntity savedstudEntity = studentRepository.save(studentEntity);

        if(savedstudEntity.getStudentId() >= 1){return true;}
        return false;

    }


//  [삭제] : 제약조건 cascade = CascadeType.All or CascadeTpye.Remove 

    public boolean delete(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isPresent()) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }


}
