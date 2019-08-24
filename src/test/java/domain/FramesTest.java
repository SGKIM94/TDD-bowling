package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FramesTest {
    private final Frames frames = new Frames();

    @Test
    public void add_정상적으로_이뤄지는가() {
        assertThat(frames.add(new Frame(new Score(10), new BallThrowCount(1)))).isEqualTo(frames);
    }

    @Test
    public void get_정상적으로_이뤄지는가() {
        Frame frame = new Frame(new Score(10), new BallThrowCount(1));
        frames.add(frame);

        assertThat(frames.get(0)).isEqualTo(frame);
    }

    @Test
    public void 이전의_프레임을_가져오는지_테스트() {
        Frames frames = new Frames();
        Frame frame = new Frame();
        Frame secondFrame = new Frame();

        frames.add(frame).add(secondFrame);

        assertThat(frames.getBeforeFrame()).isEqualTo(frame);
    }

    @Test
    public void 조건에_따른_프레임들의_출력_형태를_만든다() {
        Score firstScore = new Score(4);
        Score secondScore = new Score(2);
        ScoreDisplays scoreDisplays = new ScoreDisplays();

        frames.makeScoreDisplayForm(firstScore, scoreDisplays);

        scoreDisplays = frames.makeScoreDisplayForm(secondScore, scoreDisplays);

        assertThat(scoreDisplays.displaysSize()).isEqualTo(1);
        assertThat(scoreDisplays.get(0)).isEqualTo("4|6");
    }

    @Test
    public void 마지막_인덱스의_Frame을_가져오는_테스트() {
        frames.add(new Frame(new Score(10), new BallThrowCount(1)))
              .add(new Frame(new Score(9), new BallThrowCount(2)));

        Frame frame = frames.getLastIndex();
        assertThat(frame.getBallThrowCount()).isEqualTo(2);
    }

    @Test
    public void 라운드가_2번_실행되었을때_totalScore_가_이전_라운드와_더해져서_저장되는가() {
        Frames frames = new Frames();
        frames.add(new Frame(new Score(10), new BallThrowCount(1)));
        frames.add(new Frame(new Score(5), new BallThrowCount(1)));
        frames.add(new Frame(new Score(3), new BallThrowCount(2)));

        assertThat(frames.get(2).getTotalScore()).isEqualTo(18);
    }
}
