package example.day045_260909_spring.spring_practice5_upgrade.model.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import example.day045_260909_spring.spring_practice5_upgrade.model.entity.BoardEntity;

@Repository 
public interface BoardRepository extends JpaRepository<BoardEntity,Integer>{

//  댓글 조회는 게시물 레코드 안에 comments 로 불러와서 실행되지만
//  DB SQL 쿼리문에서는 엔티티에 comments 가 (or 양방향이) 없기때문에 조회 불가능.
//  따라서, 쿼리문을 2개 사용하여 불러오고 Service에서 제작해야함.
//  지금이 아닌 다른 경우에 따라서는 쿼리문에 Join 을 사용하여 2개의 테이블을 연결하며 조회할 것. 

//  게시물 목록 조회 기능
    @Query(value = "select * from board" , nativeQuery = true)
    // List<Map<BoardDto,Object>> boardFindAllQuery(Integer id , String author , String password , String content , LocalDateTime createdAt , LocalDateTime updatedAt , List<CommentDto> comments , private Integer id);
    List<Map<String,Object>> boardFindAllQuery();
    // 질문 password 필요한가?

    @Query(value = "select comment_id as \"id\", author, password, content, created_at, updated_at, board_id from comment where board_id = :id" , nativeQuery = true)
    List<Map<String,Object>> commentQuery(@Param(value = "id")  Object id );


// +게시물 목록 개별 조회 기능
    @Query(value = "select * from board where id = :id" , nativeQuery = true)
    Map<String,Object> boardFindDetailQuery(@Param(value = "id") Integer id);


}
