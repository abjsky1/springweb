package example.day042_260904_spring.Spirng_JPA_connection;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
public class BoardEntity {

    @Id
    private Integer bno;
    private String bname;

//  단방향 참조 : FK
    @ManyToOne
    @JoinColumn(name = "cno") // FK 이름 지정. 주로 PK 와 동일하게 설정.
    private CategoryEntity categoryEntity;

//  양방향 참조 : PK
    @OneToMany(mappedBy = "boardEntity")
    @Builder.Default
    @ToString.Exclude
    private List<ReplyEntity> replylList = new ArrayList<>();

}
