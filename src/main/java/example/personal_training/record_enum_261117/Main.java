package example.personal_training.record_enum_261117;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. 객체 생성 (자동으로 만들어진 전체 생성자 사용)
        DtoVo coffee1 = new DtoVo("아메리카노", 4500);
        DtoVo coffee2 = new DtoVo("아메리카노", 4500);

        // 2. 값 꺼내기 (getName()이 아니라 필드명 그대로 호출!)
        System.out.println("메뉴 이름: " + coffee1.name());   // 출력: 아메리카노
        System.out.println("메뉴 가격: " + coffee1.price());  // 출력: 4500

        // 3. 직접 만든 메서드 호출
        System.out.println("3잔 가격: " + coffee1.calculateTotal(3)); // 출력: 13500

        // 4. 자동 toString() 확인 (객체 내용을 예쁘게 출력해 줌)
        System.out.println(coffee1); // 출력: DtoVo[name=아메리카노, price=4500]

        // 5. 자동 equals() 확인 (값이 같으면 같은 객체로 취급)
        System.out.println("같은 메뉴인가요? " + coffee1.equals(coffee2)); // 출력: true

        // 6. 불변성 확인 (값을 바꾸려고 하면 컴파일 에러 발생!)
        // coffee1.price = 5000;      // 에러! 필드가 final이라 수정 불가
        // coffee1.setPrice(5000);    // 에러! Setter 메서드가 아예 없음

        


        FoodAnalysisService service = new FoodAnalysisService();

        System.out.println("=== 1. 정상 흐름 (INFO 로그) ===");
        service.analyzeFoods(List.of("김치찌개"));

        System.out.println("\n=== 2. 개수 불일치 상황 (INFO + WARN 로그) ===");
        // 2개를 보냈지만 가짜 AI는 1개만 돌려주므로 WARN 로그가 찍힙니다.
        service.analyzeFoods(List.of("김치찌개", "계란말이"));

        System.out.println("\n=== 3. 예외 발생 상황 (INFO + ERROR 로그) ===");
        // 빈 리스트를 보내 강제로 예외를 발생시켜 ERROR 로그를 확인합니다.
        service.analyzeFoods(List.of());

    }

}
