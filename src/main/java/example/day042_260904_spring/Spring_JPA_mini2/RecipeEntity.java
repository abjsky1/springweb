package example.day042_260904_spring.Spring_JPA_mini2;


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
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeNo;

//  private Integer menu_no;

//  private Integer product_no;

    private Integer recipe_order;

    @ManyToOne
    @JoinColumn(name="product_no")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name="menu_no")
    private MenuEntity menuEntity;

/*
    menu_no INT NOT NULL,
    product_no INT NOT NULL,
    recipe_order INT NOT NULL,

    CONSTRAINT fk_recipe_menu
        FOREIGN KEY (menu_no)
        REFERENCES menu(menu_id),

    CONSTRAINT fk_recipe_product
        FOREIGN KEY (product_no)
        REFERENCES product(product_no)
*/

    
    
}
