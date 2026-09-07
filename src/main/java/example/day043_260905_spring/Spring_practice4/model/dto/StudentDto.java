package example.day043_260905_spring.Spring_practice4.model.dto;

import java.time.LocalDateTime;

import example.day043_260905_spring.Spring_practice4.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class StudentDto {

    private Integer studentId;
    private String studentName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // 생략 : 학생이 등록한 수강내역

    public StudentEntity toEntity(){

        return StudentEntity.builder()
            .studentName(this.studentName)
            .build();
    }

    public static StudentDto from(StudentEntity studentEntity){

        return StudentDto.builder()
            .studentId(studentEntity.getStudentId())
            .studentName(studentEntity.getStudentName())
            .createdAt(studentEntity.getCreatedAt())
            .updatedAt(studentEntity.getUpdatedAt())
            .build();
    }
}
