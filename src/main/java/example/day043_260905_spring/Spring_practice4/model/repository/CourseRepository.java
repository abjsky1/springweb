package example.day043_260905_spring.Spring_practice4.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day043_260905_spring.Spring_practice4.model.entity.CourseEntity;

@Repository 
public interface CourseRepository extends JpaRepository<CourseEntity,Integer>{

}
