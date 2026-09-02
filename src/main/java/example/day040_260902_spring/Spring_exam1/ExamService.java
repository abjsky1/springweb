package example.day040_260902_spring.Spring_exam1;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {
    
//  Repository 객체 주입(불러오기)

    private final ExamRepository examRepository;

//  [1] 전체조회
    public List<ExamEntity> findAll(){

//      리포지토리 호출
//      리포지토리객체.findAll() : (구현체) select SQL 지원  ==>  기본 SQL 작성 안 해도 됨.
//      매핑된 테이블의 모든 레코드(엔티티) 반환
        return examRepository.findAll();
        
    }

//  [2] 저장
    public boolean save(ExamEntity entity){

    //  리포지토리 호출
    //  리포지토리객체.save(저장할 entity) : insert SQL 지원
    //  save 반환값은 영속(매핑/저장)된 엔티티 반환
    //  만약에 SAVE 된 entity 가 PK가 존재하면 저장 성공
        ExamEntity savedEntity = examRepository.save(entity);
        if(savedEntity.getEno() >= 1 ){return true;}
        return false;
    }

//  [3] 삭제
    public boolean delete(int no){
    
    //  리포지토리 호출
    //  리포지토리객체.deleteById( 삭제할 PK번호 ) : delete SQL 지원
    //  리포지토리객체.delete(삭제할엔티티)
    //  반환타입 : 없음 , 삭제여부 findXXX 이용하여 확인
    examRepository.deleteById(no);
    return true;

    }

//  [4] 수정
//  트랜잭션 : 여러개 SQL 하나의 단위로 묶음
//  만약 여러개 SQL 중 하나라도 SQL 오류가 생기면 전체 ROLLBACK, 모두 성공하면 COMMIT
//  활용처 : 계좌이체(출금/입금) , 회원가입포인트(회원가입/최초포인트지급)
//  2개 이상의 기능을 하나로 묶을 때
    @Transactional
    public boolean update(ExamEntity entity){
    
    //  1. 영속된 엔티티 조회[ PK : 수정할 번호 ]
    //  리포지토리객체.findById( 조회할 PK번호 ) : select SQL 지원
    //  반환타입 : Optional<엔티티>
    //  Optional 클래스 : 본문(객체)을 감싼 클래스 ( null 예외 안전하게 사용하기 위함 )
    //  자바에서 예외 1등 = 객체가 없을 때(null 일 때) .점 찍고 사용하는 것
    //  만약 조회결과 엔티티가 없으면 .getEno() 오류 발생
    //  Optional<객체타입> 변수명; 객체 랩핑하여 null 검사 지원
        Optional<ExamEntity> optional = examRepository.findById(entity.getEno());

    //  2. 조회된 결과 엔티티 여부 확인
    //  객체가 있으면 true , 없으면 false
        if( optional.isPresent() ){ 
        
        //  래핑된 Optional 에서 엔티티 꺼내기
            ExamEntity savedEntity = optional.get();
        //  만약 엔티티가 존재하면 수정 , update SQL 대신에 setter
            savedEntity.setEname(entity.getEname());
            return true;
        }

        return false;
    }
// 
// 
// 
// 
// 

}
