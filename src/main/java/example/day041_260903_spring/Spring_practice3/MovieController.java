package example.day041_260903_spring.Spring_practice3;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    
//  [1] 등록
    @PostMapping("/movie")
    public boolean movieSave(@RequestBody MovieDto movieDto){

        boolean result = movieService.movieSave(movieDto);

        return result;
    }


//  [2] 전체조회
    @GetMapping("/movie")
    public List<MovieDto> movieFindAll(){

        List<MovieDto> list = movieService.movieFindAll();

        return list;
    } 


//  [3] 개별조회
    @GetMapping("/movie/detail")
    public MovieDto movieFindDetail(int movieid){

        MovieDto movieDto = movieService.movieFindDetail(movieid);

        return movieDto;
    }


//  [4] 수정
    @PutMapping("/movie")
    public boolean movieUpdate(@RequestBody MovieDto movieDto){

        boolean result = movieService.movieUpdate(movieDto);

        return result;
    }



//  [5] 삭제
    @DeleteMapping("/movie/{movieid}")
    public boolean movieDelete(@PathVariable(name = "movieid") int movieid){

        boolean result = movieService.movieDelete(movieid);

        return result;
    }



}
