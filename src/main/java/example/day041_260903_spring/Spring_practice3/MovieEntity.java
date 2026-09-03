package example.day041_260903_spring.Spring_practice3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieid;

    @Column(nullable = true , length = 100 , unique = true , updatable = false)
    private String title;

    @Column(nullable = true , length = 100 , updatable = false)
    private String director;

    @Column(nullable = true , length = 100 , updatable = false)
    private String releasedate;

    @Column(nullable = true)
    private Double rating;

    
}
