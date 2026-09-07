package example.day043_260905_spring.Spring_practice4.model.dto;

import java.time.LocalDateTime;

import example.day043_260905_spring.Spring_practice4.model.entity.EnrollEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor 
@Builder 
public class EnrollDto {

    private Integer enrollId;
    private String sataus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // 자바(JPA)에서 entity 로 FK 사용하지만 입력받을 경우 FK번호를 받는다
    private Integer courseId;
    private Integer studentId;

    // + 과정명 , 학생명
    private String courseName;
    private String StudentName;

    public EnrollEntity toEntity(){

        return EnrollEntity.builder()
            .status(this.sataus)
            // 학생 FK 와 과정 FK 는 Service 계층에서 등록예정
            .build();
    }

    public static EnrollDto from(EnrollEntity enrollEntity){

        return EnrollDto.builder()
            .enrollId(enrollEntity.getEnrollId())
            .sataus(enrollEntity.getStatus())
            // 수강 엔티티 안에 과정 엔티티 안에 과정명만 조회
            .courseName(enrollEntity.getCourseEntity().getCourseName())
            // 수강 엔티티 안에 학생 엔티티 안에 학생명만 조회
            .StudentName(enrollEntity.getStudentEntity().getStudentName())
            .createdAt(enrollEntity.getCreatedAt())
            .updatedAt(enrollEntity.getUpdatedAt())
            .build();
    }
}
