package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BawlingGameTest {
    public static final int SCORE = 10;
    public static final int MAX_SCORE = 10;
    final BawlingGame bawlingGame = new BawlingGame();

    @Test
    public void 한_프레임의_최대점수와_점수가_일치하는지_테스트() {
        assertThat(bawlingGame.isEqualMaxValue(SCORE, MAX_SCORE)).isEqualTo(true);
    }

    @Test
    public void 점수를_입력하여_Frame_을_셋팅한다() {
        Frame frame = bawlingGame.setFramesScore(new Score(SCORE));

        assertThat(frame).isNotNull();
    }

    @Test
    public void 프레임이_넘어가도디는지_확인해서_넘어갈_수_있다면_Fame_라운드를_늘린다() {
        Score score = new Score(SCORE);

        bawlingGame.setFramesScore(score);

        assertThat(bawlingGame.checkFrameStepOverNext()).isEqualTo(true);
    }

    @Test
    public void 이전의_프레임을_가져오는지_테스트() {
        Score score = new Score(SCORE);

        Frame frame = bawlingGame.setFramesScore(score);

        assertThat(bawlingGame.getBeforeFrame()).isEqualTo(frame);
    }
}
