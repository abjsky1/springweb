package example.day040_260902_spring;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {
    
//  Repository 객체 주입(불러오기)

    private final ExamRepository examRepository;

//  [1] 전체조회
    public List<ExamEntity> findAll(){

//      리포지토리 호출
//      리포지토리객체.findAll() : (구현체) select 지원  ==>  기본 SQL 작성 안 해도 됨.
//      매핑된 테이블의 모든 레코드(엔티티) 반환
        return examRepository.findAll();
        
    }

//  [2] 저장
    public boolean save(ExamEntity entity){

    //  리포지토리 호출
    //  리포지토리객체.save(저장할 entity) : insert 지원
    //  save 반환값은 영속(매핑/저장)된 엔티티 반환
    //  만약에 SAVE 된 entity 가 PK가 존재하면 저장 성공
        ExamEntity savedEntity = examRepository.save(entity);
        if(savedEntity.getEno() >= 1 ){return true;}
        return false;
    }
// 
// 
// 
// 
// 
// 
// 

}
