package example.day055_260923_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import example.day055_260923_spring.model.dto.ApiDto;
import example.day055_260923_spring.model.entity.ApiEntity;
import example.day055_260923_spring.model.repository.ApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class ApiService {

    private final ApiRepository apiRepository;

    public List<ApiDto> findAll(){

        List<ApiEntity> apiEntities = apiRepository.findAll();

        List<ApiDto> apiDtos = apiEntities.stream().map( (entity) -> {return ApiDto.from(entity);} ).toList();

        return apiDtos;
        
    }

    public boolean save(ApiDto apiDto){

        ApiEntity apiEntity = apiDto.toEntity();

        ApiEntity savedApiEntity = apiRepository.save(apiEntity);

        if(savedApiEntity.getIdx() >= 1){
            return true;
        }
        return false;
    }



}