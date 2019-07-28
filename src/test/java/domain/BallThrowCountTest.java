package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BallThrowCountTest {
    private static final int MINIMUM_BALL_THROW_COUNT = 0;
    private static final int MAX_BALL_THROW_COUNT = 2;
    final BallThrowCount ballThrowCount = new BallThrowCount(1);

    @Test
    public void 점수를_입력받으면_볼카운트는_자동으로_증가한다() {
    }

    @Test
    public void 볼_던진_횟수의_값을_임의의_값보다_더_큰지_비교한다() {
        assertThat(ballThrowCount.isBigger(MINIMUM_BALL_THROW_COUNT)).isEqualTo(true);
    }

    @Test
    public void 볼_던진_횟수의_값을_임의의_값과_더_작은지_비교한다() {
        assertThat(ballThrowCount.isSmaller(MAX_BALL_THROW_COUNT)).isEqualTo(true);
    }

    @Test
    public void 볼_던짓_횟수가_처음인가() {
        final BallThrowCount ballThrowCount = new BallThrowCount(1);

        assertThat(ballThrowCount.isFirstBallThrowing()).isEqualTo(true);
    }
}
