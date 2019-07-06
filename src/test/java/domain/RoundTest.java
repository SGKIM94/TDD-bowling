package domain;

import org.junit.Test;

public class RoundTest {
    @Test(expected = IllegalArgumentException.class)
    public void 값이_10_이상인경우_에러처리한다() {
        new Round(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 값이_0이하인경우_에러처리한다() {
        new Round(-9);
    }
}
