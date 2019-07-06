package domain;

import java.util.ArrayList;
import java.util.List;

public class BawlingGame {
    private static final int START_ROUND = 0;
    private static final int FIRST_BALL_THROW_COUNT = 1;
    private static final int BEFORE_FIRST_BALL_THROWING = 0;
    private static final int BEFORE_FRAME = 1;

    //TODO : 일급 콜렉션으로 Frames 만들어주는 것 필요
    private final List<Frame> frames;
    private final Round round;

    public BawlingGame() {
        this.frames = new ArrayList<>();
        this.round = new Round(START_ROUND);
    }

    public boolean isEqualMaxValue(int score, int maxScore) {
        return score == maxScore;
    }

    public Frame setFramesScore(Score score) {
        if (isFirstThrowing()) {
            Frame frame = new Frame(score, new BallThrowCount(FIRST_BALL_THROW_COUNT));
            this.frames.add(frame);

            return frame;
        }

        Frame frame = new Frame(score, getBeforeFrame().getBallThrowCount());
        this.frames.add(frame);

        return frame;
    }

    public Frame getBeforeFrame() {
        return this.frames.get(this.frames.size() - BEFORE_FRAME);
    }

    private boolean isFirstThrowing() {
        return this.frames.size() == BEFORE_FIRST_BALL_THROWING;
    }

    public boolean checkFrameStepOverNext() {
        return this.frames.get(this.round.getRound()).canSkipThisFrame();
    }
}
