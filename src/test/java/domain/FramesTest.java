package domain;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FramesTest {
    private static final int SCORE = 10;
    final Frames frames = new Frames();

    @Test
    public void add_정상적으로_이뤄지는가() {
        assertThat(frames.add(new Frame(new Score(10), new BallThrowCount(1)))).isEqualTo(true);
    }

    @Test
    public void get_정상적으로_이뤄지는가() {
        Frame frame = new Frame(new Score(10), new BallThrowCount(1));
        frames.add(frame);

        assertThat(frames.get(0)).isEqualTo(frame);
    }


    @Test
    public void 이전의_프레임을_가져오는지_테스트() {
        final BawlingGame bawlingGame = new BawlingGame();
        Frames frames = new Frames();

        Scores scores = new Scores(Collections.singletonList(new Score(SCORE)));
        Frame frame = bawlingGame.setFramesScore(scores);
        frames.add(frame);

        assertThat(frames.getBeforeFrame()).isEqualTo(frame);
    }
}
