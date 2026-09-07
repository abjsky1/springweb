package example.day042_260904_spring.Spring_JPA_mini2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mini2ProductLogDto {

    private Integer product_log_no;
    private Integer product_qty;
    private String product_condition; 
    private Integer product_log_price;
    private Integer customer_log_day;
    private Integer product_no;

    public ProductLogEntity toEntity(){

        return ProductLogEntity.builder()
                               .product_log_no(product_log_no)
                               .product_qty(product_qty)
                               .product_condition(product_condition)
                               .product_log_price(product_log_price)
                               .customer_log_day(customer_log_day)
                               .build();
    }


    public static Mini2ProductLogDto from(ProductLogEntity productLogEntity){

        return Mini2ProductLogDto.builder()
                                 .product_log_no(productLogEntity.getProduct_log_no())
                                 .product_qty(productLogEntity.getProduct_qty())
                                 .product_condition(productLogEntity.getProduct_condition())
                                 .product_log_price(productLogEntity.getProduct_log_price())
                                 .customer_log_day(productLogEntity.getCustomer_log_day())
                                 .product_no(productLogEntity.getProductEntity().getProduct_no())
                                 .build();
    }
/*
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
*/

}
