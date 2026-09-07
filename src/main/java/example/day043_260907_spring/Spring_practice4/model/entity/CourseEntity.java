package example.day043_260907_spring.Spring_practice4.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table (name = "course")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class CourseEntity extends BaseTime{

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column 
    private String courseName;

    @OneToMany(mappedBy = "courseEntity" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<EnrollEntity> enrollEntities = new ArrayList<>();
    
}
