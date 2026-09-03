package example.day041_260903_spring.Spring_exam1;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter  // 조회용
@NoArgsConstructor  
@MappedSuperclass  // 공통 매핑 정보가 필요할 때 상속용 추상 부모 클래스에 선언.
@EntityListeners( AuditingEntityListener.class )  // 엔티티의 생명주기(Lifecycle: Persist, Update 등) 이벤트를 감지하는 리스너(구현체) 등록.
public class BaseTime {

//  1. 레코드 생성시점
//  @CreatedDate : 엔티티가 영속화(INSERT)될 때의 현재 일시를 자동으로 기록.
    @CreatedDate
    private LocalDateTime createDate;

//  2. 레코드 변경시점
//  @LastModifiedDate : 엔티티의 데이터가 변경(UPDATE)될 때의 변경 일시를 자동으로 갱신.
    @LastModifiedDate
    private LocalDateTime updateDate;

// 
// 
// 
// 

}
