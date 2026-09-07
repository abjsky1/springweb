package example.day042_260904_spring.Spirng_JPA_connection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reply")
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
public class ReplyEntity {

    @Id
    private Integer rno;
    private String rname;

//  단방향
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;


}

/*

    1) cascade

        cascade = CascadeType.ALL       :   부모가 저장/수정/삭제/REFRESH/DETACH 되면 자식도 같이 저장/수정/삭제/REFRESH/DETACH 된다.

        cascade = CascadeType.PERSIST   :   부모가 *저장* 되면 자식도 같이 *저장* 된다.

        cascade = CascadeType.MERGE     :   부모가 *수정* 되면 자식도 같이 *수정* 된다.

        cascade = CascadeType.REMOVE    :   부모가 *삭제* 되면 자식도 같이 *삭제* 된다.

        cascade = CascadeType.REFRESH   :   부모가 재호출(갱신) 되면 자식도 같이 재호출(갱신) 된다.

        cascade = CascadeType.DETACH    :   부모가 영속 해제 되면 자식도 영속 해제 된다.

    2) fetch

        fetch = FetchType.EAGER   :   해당 엔티티를 조회하면 참조 엔티티도 즉시 조회한다.

            특징 : 기본값 , 초기 로딩 느리다 , *불필요한 엔티티 정보* 가 있을경우 기능 저하

        fetch = FetchType.LAZY    :   해당 엔티티를 조회하면 참조 엔티티를 조회하지 않는다.

            특징 : 초기 로딩 빠르다, 사용할 엔티티 정보를 적절하게 사용하면 성능 최적화

        해당엔티티.getXXX() 하는 순간 그때 참조 엔티티 조회 ( 지연 로딩 )

*/