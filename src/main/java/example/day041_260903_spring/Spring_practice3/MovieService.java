package example.day041_260903_spring.Spring_practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;


//  [1] 등록
    public boolean movieSave(MovieDto movieDto){

        MovieEntity movieEntity = movieDto.toEntity();

        MovieEntity savedEntity = movieRepository.save(movieEntity);

        if(savedEntity.getMovieid() >= 1){ return true; }
        return false;

    //  오류 발생
    //
    //  MovieEntity movieEntity = movieDto.toEntity();
    //
    //  if(movieEntity.getMovieid() >= 1){
    //      movieRepository.save(movieEntity);
    //      return true;
    //  }
    //  return false;
    }


//  [2] 전체조회
    public List<MovieDto> movieFindAll(){

        List<MovieEntity> movieEntities = movieRepository.findAll();

        List<MovieDto> list = new ArrayList<>();

        movieEntities.forEach( (entity) -> {MovieDto movieDto = MovieDto.from(entity); list.add(movieDto);} );

        return list;
    } 


//  [3] 개별조회
    public MovieDto movieFindDetail(int movieid){

        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        
        if(optional.isPresent()){
            MovieEntity movieEntity = optional.get();
        //  MovieDto movieDto = new MovieDto();  ==>  MovieDto.from()는 스태틱이기 때문에 new 생략가능.
            MovieDto movieDto = MovieDto.from(movieEntity);
            movieDto.setTitle(movieEntity.getTitle());
            movieDto.setDirector(movieEntity.getDirector());
            movieDto.setReleasedate(movieEntity.getReleasedate());
            movieDto.setRating(movieEntity.getRating());
            movieDto.setCreateDate(movieEntity.getCreateDate());
            movieDto.setUpdateDate(movieEntity.getUpdateDate());
            return movieDto;
        }
        return null;
    }


//  [4] 수정
    @Transactional
    public boolean movieUpdate(MovieDto movieDto){

        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovieid());

        if(optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            movieEntity.setRating(movieDto.getRating());
            return true;
        }
        return false;
    }

    // 질문 : 업데이트에서 toEntity() 나 from() 사용을 안 하는데 그러면 MovieUpdateDto 에서 Dto <-> Entity 변경 함수 설정을 안 해도 되는지.


//  [5] 삭제
    public boolean movieDelete(int movieid){

        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        if(optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            movieRepository.delete(movieEntity);
            return true;
        }
        return false;
        
    //  movieRepository.deleteById(movieid);
    //  deleteById 는 Void 라서 반환이 없다.
    //  따라서 삭제 여부를 판단할 때는 강제 예외를 걸어 확인한다.

        
    }

}
