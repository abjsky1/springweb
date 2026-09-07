package example.day043_260905_spring.Spring_practice4.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "enroll")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class EnrollEntity extends BaseTime{

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer enrollId;

    @Column 
    private String status;

    @ManyToOne 
    @JoinColumn(name="courseId")
    private CourseEntity courseEntity;

    @ManyToOne
    @JoinColumn(name="studentId")
    private StudentEntity studentEntity;
    
}
