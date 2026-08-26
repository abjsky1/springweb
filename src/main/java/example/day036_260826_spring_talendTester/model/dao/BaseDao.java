package example.day036_260826_spring_talendTester.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {

    // 여러개 DAO 클래스들에게 JDBC 연동 인스턴스 상속

    // 1. 연동 정보
    private String url = "jdbc:mysql://127.0.0.1:3306/mydb260826_test";
    private String user = "root";
    private String password = "991231";

    // 2. 연동 인터페이스 , protected: 상속관계이면 다른패키지도 접근허용
    protected Connection conn; 

    // 3. 연동 메소드
    private void connect( ){
        try{    // 3-1 : mysql Driver 클래스 로드 한다.
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 3-2 : 데이터베이스 서버와 연동 후 성공하면 conn(인터페이스) 대입
                conn = DriverManager.getConnection(url, user, password);

        }catch( Exception e ){ System.out.println("DB연동실패" + e);}
    }

    // 4. 기본생성자에 연동메소드 실행 , BaseDao 클래스로부터 상속받은 DAO들은 자동 connect 
    protected BaseDao(){ connect(); }

//  protected BaseDao(){
//      System.out.println("BaseDao 생성");
//      connect();
//  
//      System.out.println("conn = " + conn);
//  }    



}
