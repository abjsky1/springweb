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
public class MovieDto {

    private Integer movieid;
    private String title;
    private String director;
    private String releasedate;
    private Double rating;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public MovieEntity toEntity(){

        return MovieEntity.builder()
                          .title(this.title)
                          .director(this.director)
                          .releasedate(this.releasedate)
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
    //                 .updateDate(movieEntity.getUpdateDate().format(포멧설정))  // : Dto에서 format 으로 정의하여 개선 가능
                       .build();
    }

}
