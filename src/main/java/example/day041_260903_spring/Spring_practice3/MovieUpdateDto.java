package example.day041_260903_spring.Spring_practice3;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieUpdateDto {

    private Integer movieid;
    private Double rating;

    public MovieEntity toEntity(){

        return MovieEntity.builder()
                          .rating(this.rating)
                          .build();
    }

    public static MovieDto from(MovieEntity movieEntity){

        return MovieDto.builder()
                       .movieid(movieEntity.getMovieid())
                       .title(movieEntity.getTitle())
                       .director(movieEntity.getDirector())
                       .releasedate(movieEntity.getReleasedate())
                       .rating(movieEntity.getRating())
                       .createDate(movieEntity.getCreateDate())
                       .updateDate(movieEntity.getUpdateDate())
                       .build();
    }

}
