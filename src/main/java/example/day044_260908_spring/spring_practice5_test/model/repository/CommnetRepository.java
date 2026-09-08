package example.day044_260908_spring.spring_practice5_test.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day044_260908_spring.spring_practice5_test.model.entity.CommentEntity;

@Repository 
public interface CommnetRepository extends JpaRepository<CommentEntity,Integer>{

}
