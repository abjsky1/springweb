package example.day040_260902_spring.Spring_practice2;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

//  [1] 저장
    public boolean save(TestEntity testEntity){

        TestEntity savedEntity = testRepository.save(testEntity);

        if(savedEntity.getNo() >= 1){return true;}

        return false;
    }

//  [2] 전체조회
    public List<TestEntity> findAll(){

        return testRepository.findAll();
    }

//  [3] 개별조회
    public TestEntity testDetail(int no){

        Optional<TestEntity> optional = testRepository.findById(no);

        TestEntity testEntity = optional.get();

        return testEntity;
    }

//  [4] 삭제
    public boolean delete(int no){

        testRepository.deleteById(no);

        return true;
    }

//  [5] 수정
    @Transactional
    public boolean update(TestEntity testEntity){

        Optional<TestEntity> optional = testRepository.findById(testEntity.getNo());

        if(optional.isPresent()){

            TestEntity savedTestEntity = optional.get();

            savedTestEntity.setContent(testEntity.getContent());

            return true;

        }
        return false;

    }



    
}
