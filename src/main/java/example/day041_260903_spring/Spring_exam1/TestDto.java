package example.day041_260903_spring.Spring_exam1;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data // @Getter + @Setter + @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
//  서로 다른 계층간에 이동객체 (controller 에서는 Entity 사용 비권장. = 보안 문제)
public class TestDto {

//  Entity 와 동일하게 멤버변수 구성 : 

//      기능별로 DTO 구성 

//          등록DTO , 조회DTO , 수정DTO

    private Integer no;
    private String name;
    private String descri;
    private Integer price;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Entity --> DTO함수  :  Controller ->  Service       =======    toEntity()  , 주로 save/update 목적
    public TestEntity toEntity(){

    //  빌터패턴방식 : new(생성자) 대신에 메소드 방식으로 객체 생성 지원
    //  this : 해당 메소드 호출한 인스턴스 가리킴
        return TestEntity.builder()
                         .name(this.name)
                         .descri(this.descri)
                         .price(this.price)
                         .build();
    }

    // DTO함수 --> Entity  :    Service  -> Controller     =======    toDto()    , from(Entity entity) , 주로 find 목적
    // static : 인스턴스 없이 호출 가능한 메소드/변수
    // this 없음 : 인스턴스 없이 사용하는 메소드
    public static TestDto from(TestEntity testEntity){
        return TestDto.builder()
                      .no(testEntity.getNo())
                      .name(testEntity.getName())
                      .descri(testEntity.getDescri())
                      .price(testEntity.getPrice())
                      .createDate(testEntity.getCreateDate())
                      .updateDate(testEntity.getUpdateDate())
                      .build();
    }


}
