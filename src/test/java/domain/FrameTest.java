package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {

    @Test
    public void 볼을_떤진_횟수와_점수를_계산한다() {
        Score score = new Score(10);
        int ballThrowCount = 1;

        Frame frame = new Frame(score, ballThrowCount);

        assertThat(frame.canSkipThisFrame()).isEqualTo(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 볼던지는_횟수가_옳바른_값이_아니면_예외를_처리한다() {
        Score score = new Score(10);

        Frame frame = new Frame(score, 3);

        frame.checkBallThrowCount();
    }
}
