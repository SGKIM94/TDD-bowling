package domain;

import java.util.ArrayList;
import java.util.List;

public class BawlingGame {
    public static final int START_ROUND = 0;

    private final List<Frame> frames;
    private final Round round;

    public BawlingGame() {
        this.frames = new ArrayList<>();
        this.round = new Round(START_ROUND);
    }

    public boolean isEqualMaxValue(int score, int maxScore) {
        return score == maxScore;
    }

    // 점수가 입력 되었을 때 그 점수가 프레임에서 처음 던진 경우에는 정상적으로 점수를 입력받는다.
    public int setFramesScore(Score score) {
        Frame beforeFrame = this.frames.get(this.frames.size() - 1);
        this.frames.add(new Frame(score, beforeFrame.getBallThrowCount()));

        return this.frames.size();
    }

    public Round checkFrameStepOverNext() {
        Frame currentFrame = this.frames.get(this.round.getRound());

        if (currentFrame.canSkipThisFrame()) {
            return new Round(this.round.getRound());
        }

        return this.round;
    }
}
