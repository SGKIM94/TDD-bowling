package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreDisplaysTest {
    final Score strikeScore = new Score(10);
    final Score spareScore = new Score(10);
    final Score missScore = new Score(5);

    final ScoreGroup STRIKE = ScoreGroup.findByScore(new BallThrowCount(1),  strikeScore);
    final ScoreGroup MISS = ScoreGroup.findByScore(new BallThrowCount(2),  missScore);
    final ScoreGroup SPARE = ScoreGroup.findByScore(new BallThrowCount(2),  spareScore);

    @Test
    public void 점수와_던진횟수로_얻은_display_를_저장_후_사이즈를_가져온다() {
        List<String> displays = Arrays.asList(STRIKE.getDisplay(), MISS.getDisplay(), SPARE.getDisplay());

        ScoreDisplays scoreDisplays = new ScoreDisplays(displays);

        assertThat(scoreDisplays.displaysSize()).isEqualTo(3);
    }
}

