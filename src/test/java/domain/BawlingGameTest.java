package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BawlingGameTest {
    @Test
    public void 이전의_점수와_새로운_점수를_더해_총_쩜수를_구한다() {
        BawlingGame bawlingGame = new BawlingGame(new Score(10));

        assertThat(bawlingGame.getTotalScore(new Score(9))).isEqualTo(19);
    }
}
