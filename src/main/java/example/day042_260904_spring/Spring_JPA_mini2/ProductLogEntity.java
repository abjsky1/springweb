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
@Table(name = "productlog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductLogEntity extends BaseTime{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer product_log_no;

//  private Integer product_id;
    private Integer product_qty;
    private String product_condition;
    private Integer product_log_price;
    private Integer customer_log_day;

    @ManyToOne
    @JoinColumn(name="product_no")
    private ProductEntity productEntity;

    


/*

    productLog_no INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    product_qty INT NOT NULL,
    product_condition VARCHAR(20) NOT NULL,
    productLog_price INT NOT NULL,
    customerLog_day INT NOT NULL,

    CONSTRAINT fk_productLog_product
        FOREIGN KEY (product_id)
        REFERENCES product(product_no)

*/


}
