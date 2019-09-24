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
    public void 첫_점수가_4이고_다음_숫자가_2인경우_42_형태를만든다() {
        Score firstScore = new Score(4);
        Score secondScore = new Score(2);
        ScoreDisplays scoreDisplays = new ScoreDisplays();

        frames.makeScoreDisplayAndAddFrame(firstScore, scoreDisplays);

        scoreDisplays = frames.makeScoreDisplayAndAddFrame(secondScore, scoreDisplays);

        assertThat(scoreDisplays.displaysSize()).isEqualTo(1);
        assertThat(scoreDisplays.get(0)).isEqualTo("4|2");
    }

    @Test
    public void 첫_점수가_4_이고_다음_숫자가_6_인경우_스페어_형태를_만든다() {
        Score firstScore = new Score(4);
        Score secondScore = new Score(6);
        ScoreDisplays scoreDisplays = new ScoreDisplays();

        frames.makeScoreDisplayAndAddFrame(firstScore, scoreDisplays);

        scoreDisplays = frames.makeScoreDisplayAndAddFrame(secondScore, scoreDisplays);

        assertThat(scoreDisplays.displaysSize()).isEqualTo(1);
        assertThat(scoreDisplays.get(0)).isEqualTo("4|/");
    }

    @Test
    public void 마지막_인덱스의_Frame을_가져오는_테스트() {
        frames.add(new Frame(new Score(10), new BallThrowCount(1)))
              .add(new Frame(new Score(9), new BallThrowCount(2)));

        Frame frame = frames.getLastFrame();
        assertThat(frame.getBallThrowCount()).isEqualTo(2);
    }

    @Test
    public void 라운드가_2번_실행되었을때_totalScore_가_이전_라운드와_더해져서_저장되는가() {
        Frames frames = new Frames();
        frames.add(new Frame(new Score(10), new BallThrowCount(1)));
        frames.add(new Frame(new Score(5), new BallThrowCount(1)));
        frames.add(new Frame(new Score(3), new BallThrowCount(2)));

        assertThat(frames.get(2).getTotalScore().getTotalScore()).isEqualTo(18);
    }

    @Test
    public void bowl_을_3번실행했을때_총점이_더해져서_리턴하는가() {
        ScoreDisplays scoreDisplays = new ScoreDisplays();
        Frames frames = new Frames();

        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);
        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(3), scoreDisplays);
        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(10), scoreDisplays);
        frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);

        assertThat(frames.getLsatIndexTotalScore().getTotalScore()).isEqualTo(23);
    }

    @Test
    public void 점수가_스페어일때_총합에_다음점수첫점이_더해지는가() {
        ScoreDisplays scoreDisplays = new ScoreDisplays();
        Frames frames = new Frames();

        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);
        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);
        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(3), scoreDisplays);
        frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);

        assertThat(frames.getLsatIndexTotalScore().getTotalScore()).isEqualTo(16);
    }

    @Test
    public void 두번째_공을던진경우_합한점수의_점수표시가_나오는가() {
        ScoreDisplays scoreDisplays = new ScoreDisplays();
        Frames frames = new Frames();

        frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);
        String scoreDisplay = frames.getScoreDisplayWhenSecondBallThrow(new Score(3), frames.getLastFrame());

        assertThat(scoreDisplay).isEqualTo("3|");
    }

    @Test
    public void 스트라이크가_아닌경우_두번째_scoreDisplay_를_가져오는가() {
        //given
        Frames frames = new Frames();

        //when
        String scoreDisplay = "5|/";

        //then
        assertThat(frames.getSecondScoreDisplayWhenNotStrike(scoreDisplay)).isEqualTo("/");
    }
}
