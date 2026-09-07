package example.day042_260904_spring.Spring_JPA_mini2;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuEntity extends BaseTime{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer menu_no;

    @Column(length = 50)
    private String menu_name;

    private Integer menu_price;

    @OneToMany(mappedBy = "menuEntity")
    @ToString.Exclude
    @Builder.Default
    private List<RecipeEntity> recipeList = new ArrayList<>();

/*
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    menu_name VARCHAR(50) NOT NULL,
    menu_price INT NOT NULL
*/
    
}
