package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {
    final Score score = new Score(10);

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
}
