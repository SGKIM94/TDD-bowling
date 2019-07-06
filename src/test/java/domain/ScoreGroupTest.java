package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreGroupTest {

    @Test
    public void ScoreGroup_정상값_가져오는지() {
        Score score = new Score(10);
        int frameCount = 1;

        ScoreGroup scoreGroup = ScoreGroup.findByScore(new BallThrowCount(frameCount), score);
        assertThat(scoreGroup).isEqualTo(ScoreGroup.STRIKE);
    }

    @Test
    public void ScoreGroup_STRIKE_display_정상_리턴() {
        assertThat(ScoreGroup.STRIKE.getDisplay()).isEqualTo("X");
    }
}
