import org.junit.Test;

public class PlayerTest {
    @Test
    public void 참가자_이름을_입력받는다() {
        String name = "kim";

        Player.checkName(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 참가자_이름이_없는_경우_오류_발생() {
        String name = "";

        Player.checkName(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 참가자_이름이_영어_3글자인지_검사() {
        String name = "abcd";

        Player.checkName(name);
    }
}
