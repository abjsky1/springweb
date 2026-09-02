package example.day040_260902_spring.Spring_practice2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {

    private Integer no;
    private String content;
    private String writer;

}

//  - DTO 에서는 기본타입 말고 대문자로 적는 래퍼타입을 사용할 것.