package domain;

import java.util.Arrays;

public enum ScoreGroup {
    STRIKE(1, 10, "X"),
    SPARE(2, 10, "/"),
    GUTTER(2, 0, "-"),
    MISS(2, 0, ""),
    ELSE(0, 0, "|");

    ScoreGroup(int frameCount, int addedScore, String display) {
        this.ballThrowCount = frameCount;
        this.addedScore = addedScore;
        this.display = display;
    }

    private int ballThrowCount;
    private int addedScore;
    private String display;

    public static ScoreGroup findByScore(BallThrowCount ballThrowCount, Score score) {
        return Arrays.stream(ScoreGroup.values())
                .filter(e -> score.equals(e.addedScore))
                .filter(e -> ballThrowCount.getThrowCount() == (e.ballThrowCount))
                .findFirst()
                .orElse(ELSE);
    }

    public String getDisplay() {
        return display;
    }

    public boolean isEqualScoreDisplayWithInputScoreDisplay(String scoreDisplay) {
        return this.display.equals(scoreDisplay);

    }
}
