package example.day056_260928_spring;

import java.nio.file.OpenOption;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface MemberRepository extends JpaRepository<MemberEntity,Long>{

//  JPA 사용시 기본적인 CRUD 메소드 제공 , save findAll findById delete delteById 등

//  * 메소드쿼리(망명규칙) 또는 네이티브쿼리 추가 정의

//  findByXXX : XXX에 필드명 넣어서 조회 추상 메소드 만들기

//  주의 : 카멜표기법


//  mid 일치하면 엔티티 조회
    MemberEntity findByMid(String mid);

//  Mname 일치하면 엔티티조회
    Optional<MemberEntity> findByMname(String mname);

}
