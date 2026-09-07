package example.day042_260904_spring.Spring_JPA_mini2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mini2MenuRepository extends JpaRepository<MenuEntity,Integer>{

}
