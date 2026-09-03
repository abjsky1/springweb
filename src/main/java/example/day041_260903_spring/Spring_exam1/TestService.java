package example.day041_260903_spring.Spring_exam1;

import example.day040_260902_spring.Spring_exam1.AppStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService{

    private final TestRepository testRepository;


//  [1] 전체조회
    public List<TestDto> 전체조회(){
    
    //  1. 모든 엔티티 조회
        List<TestEntity> testEntity = testRepository.findAll();

    //  2. 모든 엔티티를 DTO 로 변환하기

    //      2-1. 빈 리스트 생성

            List<TestDto> list = new ArrayList<>();

    //      2-2. 모든 엔티티 반복하여 DTO 타입 변환 후 새 리스트에 저장

    //      리스트객체.forEach( (반복변수) -> {실행문} )
            testEntity.forEach( (entity) -> {
    //          TestDto 안에 entity -> dto 변환함수 : from
                TestDto dto = TestDto.from(entity); 
                list.add(dto);
            } );   
            return list; 
    }


//  [2] 등록
    public boolean 저장(TestDto testDto){

    //  1. DTO 를 entity 로 변환하는 함수 : toEntity 함수
        TestEntity testEntity = testDto.toEntity();
    //  2. entity save 저장
        TestEntity savedEntity = testRepository.save(testEntity);
    //  3. 저장 결과 pk값 1 이상이면 저장 완료
        if(savedEntity.getNo() >= 1){ return true; }
        return false;
    }


//  [3] 수정
    @Transactional
    public boolean 수정(TestDto testDto){

    //  1. 수정할 엔티티 찾기
        Optional<TestEntity> optional = testRepository.findById(testDto.getNo());

    //  2. 찾은 엔티티가 존재하는지 확인
        if (optional.isPresent()) {

    //      엔티티 꺼내기
            TestEntity entity = optional.get();

    //      setter 메소드 이용한 수정
            entity.setPrice(testDto.getPrice());
            entity.setDescri(testDto.getDescri());
            return true;    
        } 
        return false;
    }

    
}
