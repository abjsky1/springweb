package example.day043_260907_spring.Spring_practice5.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day043_260907_spring.Spring_practice5.model.entity.CommentEntity;

@Repository 
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{

}
