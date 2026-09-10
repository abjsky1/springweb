package example.day045_260909_spring.spring_practice5_upgrade.model.entity;

import java.time.LocalDateTime;

import org.hibernate.boot.models.spi.JpaEventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@NoArgsConstructor 
@EntityListeners (JpaEventListener.class)
@MappedSuperclass 
public class BaseTime {

    @CreatedDate 
    private LocalDateTime createdAt;

    @LastModifiedDate 
    private LocalDateTime updatedAt;

}
