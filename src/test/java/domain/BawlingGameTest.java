package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BawlingGameTest {
    private static final int SCORE = 10;
    private static final int MAX_SCORE = 10;
    final BawlingGame bawlingGame = new BawlingGame();
    final Scores scores = new Scores(new Score(SCORE));

    @Test
    public void 한_프레임의_최대점수와_점수가_일치하는지_테스트() {
        assertThat(bawlingGame.isEqualMaxValue(SCORE, MAX_SCORE)).isEqualTo(true);
    }

    @Test
    public void 점수를_입력하여_Frame_을_셋팅한다() {
        Frame frame = bawlingGame.setFramesScore(scores);

        assertThat(frame.getDisplayScore()).isNotNull();
    }

    @Test
    public void 라운드를_추가한다() {
        assertThat(bawlingGame.addRound()).isEqualTo(1);
    }
}
