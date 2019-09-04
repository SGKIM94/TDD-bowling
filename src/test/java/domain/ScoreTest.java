package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreTest {
    @Test(expected = IllegalArgumentException.class)
    public void 잘못된_점수입력시_오류리턴() {
        new Score("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 십점_이상이나_영점_이하의_점수_입력시_오류리턴() {
        new Score("-10");
    }

    @Test
    public void  점수가_스트라이크인가() {
        int strikeScore = 10;

        Score score = new Score(strikeScore);

        score.isStrike();
    }

    @Test
    public void score_equals_가_정상적으로_동작하는가() {
        Score score = new Score(10);

        assertThat(score.equals(10)).isEqualTo(true);
    }

    @Test
    public void 점수를_입력하여_STRIKE_점수모양을_가져온다() {
        Score score = new Score(10);
        int ballThrowCount = 1;

        assertThat(score.getDisplayScore(new BallThrowCount(ballThrowCount))).isEqualTo("X");
    }

    @Test
    public void 점수를_입력하여_SPARE_점수모양을_가져온다() {
        Score score = new Score(10);
        int ballThrowCount = 2;

        assertThat(score.getDisplayScore(new BallThrowCount(ballThrowCount))).isEqualTo("/");
    }

    @Test
    public void 두자리_숫자_출력될_경우_사이즈가_몇인지_테스트() {
        Score score = new Score(10);

        assertThat(score.toStringSize()).isEqualTo(2);
    }

    @Test
    public void 한자리_숫자_출력될_경우_사이즈가_몇인지_테스트() {
        Score score = new Score(9);

        assertThat(score.toStringSize()).isEqualTo(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 올바르지_않은_점수입력시_예외처리를_하는가() {
        Score score = new Score(10);
        score.checkScoreIsWrong(20);
    }
}
