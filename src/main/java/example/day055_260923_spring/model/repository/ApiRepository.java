package example.day055_260923_spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day055_260923_spring.model.entity.ApiEntity;

@Repository 
public interface ApiRepository extends JpaRepository<ApiEntity,Integer>{

}