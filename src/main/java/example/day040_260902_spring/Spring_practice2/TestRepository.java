package example.day040_260902_spring.Spring_practice2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // 데이터베이스 접근을 담당하는 영속성계층 스프링 빈에 등록
public interface TestRepository extends JpaRepository<TestEntity, Integer>{

/*
    JpaRepository 상속 받기 : 
        
        기본적인 CRUD 제공 받음

            save() , findById() , findAll() , deleteById 

        페이징과 정렬 제공 받음

        쿼리 커스텀 제공 받음

    <TestEntity, Integer> :

        엔티티에 PK 가 필수로 들어가야 사용 가능

*/
  
}
