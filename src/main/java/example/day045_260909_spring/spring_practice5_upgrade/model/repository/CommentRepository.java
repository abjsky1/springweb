package example.day045_260909_spring.spring_practice5_upgrade.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day045_260909_spring.spring_practice5_upgrade.model.entity.CommentEntity;

@Repository 
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{

}
