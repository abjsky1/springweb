package example.day042_260904_spring.Spring_JPA_mini2;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class Mini2Service {

    private final Mini2ProductRepository mini2ProductRepository;

    private final Mini2ProductLogRepository mini2ProductLogRepository;

    
    public void method( Mini2ProductLogDto dto) {

        ProductLogEntity productLogEntity = dto.toEntity();
        //  FK --> ENTITY 조회 
        ProductEntity productEntity = mini2ProductRepository.findById( dto.getProduct_no() ).get();

        productLogEntity.setProductEntity( productEntity ); 

        mini2ProductLogRepository.save( productLogEntity );


    }

    public List<Mini2ProductLogDto> method2(){

        List<ProductLogEntity> productLogEntities = mini2ProductLogRepository.findAll();

        List<Mini2ProductLogDto> productLogList = new ArrayList<>();

        productLogEntities.forEach((entity) -> {
            Mini2ProductLogDto productLogDto = Mini2ProductLogDto.from(entity);
            productLogDto.setProduct_no( entity.getProductEntity().getProduct_no());
        });

    //  {MovieDto movieDto = MovieDto.from(entity); list.add(movieDto);}
    /*
        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        
        if(optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            MovieDto movieDto = MovieDto.from(movieEntity);  
            return movieDto;
        }
        return null;
    */
        return productLogList;
    }

}
