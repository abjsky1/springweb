package example.day036_260826_spring_talendTester.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import example.day036_260826_spring_talendTester.model.dto.WaitingDto;

public class WaitingDao extends BaseDao{

    private WaitingDao(){}
    private static final WaitingDao instance = new WaitingDao();
    public static WaitingDao getInstance(){return instance;}

//  [1] 등록

        public boolean save( WaitingDto waitingDto ){
        
        //  1. SQL 작성
            
            String sql = "insert into waitingList( phoneNumber , people ) values( ? , ? )";

        //  * try & catch
            try{

        //  2. SQL 기재 ( SQL 은 자바가 아니고 외부(MySQL서버)에 전달 )

            PreparedStatement ps = conn.prepareStatement(sql);

        //  3. 기재된 SQL에 매개변수 대입

        //      ps.set타입( ?순서번호 , 입력받은값 )

            ps.setString(1 , waitingDto.getPhoneNumber());
            ps.setInt(2, waitingDto.getPeople());

        //  4. 기재된 SQL 실행
        
        //      ps.execute() : 단순 실행만 함.

        //      ps.executeUpdate() : 실행 후 업데이트 횟수 반환함.

            int result = ps.executeUpdate();


        //  5. SQL 실행 결과

            if (result == 1) { return true; }

            }catch(Exception e){System.out.println(e);}

            return false;

        }


//  [2] 전체조회

    public ArrayList<WaitingDto> findAll(){

        ArrayList<WaitingDto> list = new ArrayList<>();

        try{
        //  1. SQL 작성

            String sql = "select * from waitingList";

        //  2. SQL 기재

            PreparedStatement ps = conn.prepareStatement(sql);

        //  3. 기재된 SQL 매개변수 대입

        //  4. SQL 실행

        //      - insert/update/delete => executeUpdate()

        //      - select => executeQuery()

            ResultSet rs = ps.executeQuery();

        //  5. SQL 결과 처리

            while(rs.next()){

                WaitingDto waitingDto = new WaitingDto();

        //      rs.get타입( "가져올속성명" )

                waitingDto.setNo( rs.getInt("no") );

                waitingDto.setPhoneNumber( rs.getString("phoneNumber") );

                waitingDto.setPeople( rs.getInt("people") );

                list.add(waitingDto);

            }

        }catch(Exception e){System.out.println(e);}

        return list;

    }


//  [3] 수정

    public boolean update( WaitingDto waitingDto ){
        try{
            String sql = "update waitingList set people = ? where phoneNumber = ? ";// 1.1 SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql); // 1.2 SQL 기재 *예외*
            ps.setInt( 1 , waitingDto.getPeople() );// 1.3 SQL내 ? 매개변수대입
            ps.setString( 2 , waitingDto.getPhoneNumber() );
            int result = ps.executeUpdate(); // 1.4 SQL 실행
            if( result == 1 ) return true; // 1.5 실행 결과 반환
        }catch( SQLException e ){ System.out.println( e ); }
        return false; // 1.5 실행 결과 반환
    }


// [4] 삭제

    public boolean delete( String phoneNumber ){
        try{ String sql = "delete from waitingList where phoneNumber = ?";
            PreparedStatement ps = conn.prepareStatement( sql );
            ps.setString( 1 , phoneNumber ); // SQL 문법내 첫번째 ? 에 매개변수 값 대입 
            int result = ps.executeUpdate();
            if( result == 1 ) return true;
        }catch( SQLException e ){ System.out.println( e ); }
        return false;
    }

}
