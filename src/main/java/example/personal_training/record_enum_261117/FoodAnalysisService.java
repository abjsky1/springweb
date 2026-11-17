package example.personal_training.record_enum_261117;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j // 1. 이 어노테이션 덕분에 클래스 안에서 'log' 변수를 바로 쓸 수 있습니다.
public class FoodAnalysisService {

    // 중첩 레코드 정의 (AI 응답 구조)
    public record TypedEstimates(List<TypedEstimate> items) {
        public record TypedEstimate(String name, double kcalPer100g) {}
    }

    public void analyzeFoods(List<String> userTypedFoods) {
        // [INFO] 주요 비즈니스 흐름 시작 기록
        log.info("음식 분석 시작 - 요청 음식 목록: {}", userTypedFoods);

        // [DEBUG] 개발 중 상세 데이터 확인용 (기본 설정에서는 콘솔에 안 보일 수 있음)
        log.debug("요청된 음식 개수 상세 확인: {}개", userTypedFoods.size());

        try {
            // 가상의 AI 호출 결과 받아오기
            TypedEstimates response = callFakeAi(userTypedFoods);

            // [WARN] 에러는 아니지만, 사용자가 2개를 적었는데 AI가 1개만 응답한 특이 상황!
            if (response.items().size() != userTypedFoods.size()) {
                log.warn("AI 응답 개수가 일치하지 않습니다! 요청: {}개, 실제 응답: {}개",
                        userTypedFoods.size(), response.items().size());
            }

            // 결과 꺼내서 출력 (레코드의 자동 생성 Getter 활용)
            for (TypedEstimates.TypedEstimate item : response.items()) {
                log.info("분석 완료된 음식: {} ({} kcal)", item.name(), item.kcalPer100g());
            }

        } catch (Exception e) {
            // [ERROR] 예외가 발생해서 기능을 정상 처리할 수 없는 심각한 상황
            log.error("AI 분석 중 오류가 발생했습니다! 입력값: {}", userTypedFoods, e);
        }
    }

    // 테스트를 위한 가짜 AI 응답 생성 메서드
    private TypedEstimates callFakeAi(List<String> foods) {
        // 일부러 에러 상황을 테스트하고 싶을 때
        if (foods.isEmpty()) {
            throw new IllegalArgumentException("분석할 음식이 하나도 없습니다.");
        }

        // 정상적으로는 foods 개수만큼 줘야 하지만, WARN 로그 테스트를 위해 1개만 반환
        var estimate = new TypedEstimates.TypedEstimate(foods.get(0), 250.0);
        return new TypedEstimates(List.of(estimate));
    }
}