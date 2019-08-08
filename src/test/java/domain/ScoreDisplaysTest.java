package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScoreDisplaysTest {
    private final Score strikeScore = new Score(10);
    private final Score spareScore = new Score(10);
    private final Score missScore = new Score(5);

    private final ScoreDisplays scoreDisplays = new ScoreDisplays();

    private final ScoreGroup STRIKE = ScoreGroup.findByScore(new BallThrowCount(1),  strikeScore);
    private final ScoreGroup MISS = ScoreGroup.findByScore(new BallThrowCount(2),  missScore);
    private final ScoreGroup SPARE = ScoreGroup.findByScore(new BallThrowCount(2),  spareScore);

    @Test
    public void 점수와_던진횟수로_얻은_display_를_저장_후_사이즈를_가져온다() {
        List<String> displays = Arrays.asList(STRIKE.getDisplay(), MISS.getDisplay(), SPARE.getDisplay());

        ScoreDisplays scoreDisplays = new ScoreDisplays(displays);

        assertThat(scoreDisplays.displaysSize()).isEqualTo(3);
    }

    @Test
    public void 이전_ScoreDisplay에_새로운_점수를_더하여_저장한다() {
        scoreDisplays.add("1|");

        scoreDisplays.setBeforeDisplay("2");

        assertThat(scoreDisplays.get(0)).isEqualTo("1|2");
    }

    @Test
    public void addAndRemoveAtAddedIndex_성공_테스트() {
        scoreDisplays.add("1")
                .add("2")
                .add("3");

        scoreDisplays.addAndRemoveAtAddedIndex(2, "5");

        assertThat(scoreDisplays.get(2)).isEqualTo("5");
    }

    @Test
    public void ScoreDisplay가_또는을_상징하는_문자인경우_자르는_테스트() {
        String scoreDisplay = "5|9|";

        assertThat(scoreDisplays.removeLastOrChar(scoreDisplay)).isEqualTo("5|9");
    }

    @Test
    public void removeLastOrChar_정상_테스트() {
        assertThat(scoreDisplays.removeLastOrChar("5|8|")).isEqualTo("5|8");
    }

}

