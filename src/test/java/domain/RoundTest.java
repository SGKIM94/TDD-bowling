package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RoundTest {
    @Test(expected = IllegalArgumentException.class)
    public void 값이_10_이상인경우_에러처리한다() {
        new Round(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 값이_0이하인경우_에러처리한다() {
        new Round(-9);
    }

    @Test
    public void 첫번째_라운드_인가() {
        assertThat(new Round(0).isFirstRound()).isEqualTo(true);
    }

    @Test
    public void add_round() {
        assertThat(new Round(1).addRound()).isEqualTo(2);
    }
}
