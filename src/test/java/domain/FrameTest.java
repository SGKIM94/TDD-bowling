package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {
    private final Score score = new Score(10);

    @Test
    public void 볼을_떤진_횟수와_점수를_계산한다() {
        int ballThrowCount = 1;

        Frame frame = new Frame(score, new BallThrowCount(ballThrowCount));

        assertThat(frame.canSkipThisFrame()).isEqualTo(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 볼던지는_횟수가_옳바른_값이_아니면_예외를_처리한다() {
        Frame frame = new Frame(score, new BallThrowCount(3));

        frame.checkBallThrowCount();
    }

    @Test
    public void 볼던진_횟수가_2회인가() {
        Frame frame = new Frame(score, new BallThrowCount(2));

        assertThat(frame.isSecondBallThrowing()).isEqualTo(true);
    }

    @Test
    public void 비어있는_frame_인가() {
        Frame frame = new Frame();

        assertThat(frame.isEmptyFrame()).isEqualTo(true);
    }

    @Test
    public void sumTotalScore_정상구동_테스트() {
        Frame frame = new Frame(new Score(8), new BallThrowCount(1));

        assertThat(frame.sumTotalScore(new Score(2))).isEqualTo(10);
    }

    @Test
    public void Frame_을_생성할때_저장되었던_점수들이_총점에_더해져야한다() {
        Frame frame = new Frame(new Score(5), new BallThrowCount(1));
        Frame secondFrame = new Frame(new Score(3), new BallThrowCount(2));
        Frames frames = new Frames();
        frames.add(frame).add(secondFrame);

        assertThat(frame.getTotalScore().getTotalScore()).isEqualTo(8);
    }
}
