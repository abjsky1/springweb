package example.day036_260826_spring_talendTester.model.dto;

public class WaitingDto {

    // DTO: 데이터 이동객체??, 자바는 저장소가 아니다. 즉] 데이터베이스(저장소)가 저장소 

    // 1. 데이터베이스 표에서 (CRUD) 사용할 자료들을 private 멤버변수로 구성
    private int no;
    private String phoneNumber;
    private int people;
    

    // 2. 기본생성자, 전체매개변수생성자
    public WaitingDto(){}

    public WaitingDto( String phoneNumber, int people) {
        this.phoneNumber = phoneNumber;
        this.people = people;
    }


    // 3. setter and getter , toString 
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getPeople() {
        return people;
    }
    public void setPeople(int people) {
        this.people = people;
    }


    @Override
    public String toString() {
        return "WaitingDto [no=" + no + ", phoneNumber=" + phoneNumber + ", people=" + people + "]";
    }

    

}
