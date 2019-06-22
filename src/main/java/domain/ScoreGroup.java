package domain;

import java.util.Arrays;

public enum ScoreGroup {
//    스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
//    스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
//    미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
//    거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
//각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    STRIKE(1, 10, "X"),
    SPARE(2, 10, " /"),
    GUTTER(2, 0, "-"),
    MISS(2, 0, ""),
    ELSE(0, 0, "");

    ScoreGroup(int frameCount, int addedScore, String display) {
        this.frameCount = frameCount;
        this.addedScore = addedScore;
        this.display = display;
    }

    private int frameCount;
    private int addedScore;
    private String display;

    public static ScoreGroup findByScore(int ballThrowCount, Score score) {
        return Arrays.stream(ScoreGroup.values())
                .filter(e -> score.equals(e.addedScore))
                .filter(e -> e.frameCount == ballThrowCount)
                .findFirst()
                .orElse(ELSE);
    }


    public int getFrameCount() {
        return frameCount;
    }

    public int getAddedScore() {
        return addedScore;
    }

    public String getDisplay() {
        return display;
    }
}
