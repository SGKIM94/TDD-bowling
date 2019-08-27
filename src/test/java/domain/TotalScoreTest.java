package domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalScoreTest {
    TotalScore totalScore = new TotalScore(new Score(5));

    @Test
    public void 총점을_입력받는다() {
        assertThat(totalScore.getTotalScore()).isEqualTo(5);
    }

    @Test
    public void 입력받은_총점을_더한다() {
        totalScore.addInputScore(new Score(10))
                .addInputScore(new Score(9));

        assertThat(totalScore.getTotalScore()).isEqualTo(24);
    }

    @Test
    public void 총점이_0점인가() {
        TotalScore totalScore = new TotalScore(new Score(0));

        assertThat(totalScore.isZeroTotalScore()).isEqualTo(true);
    }
}
