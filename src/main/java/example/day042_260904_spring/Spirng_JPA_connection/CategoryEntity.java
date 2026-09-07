package example.day042_260904_spring.Spirng_JPA_connection;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
public class CategoryEntity {

    @Id
    private Integer cno;
    private String cname;

//  양방향 참조 , 비권장
    @OneToMany(mappedBy = "categoryEntity")
    @Builder.Default
    @ToString.Exclude
    private List<BoardEntity> boardList = new ArrayList<>();

}
