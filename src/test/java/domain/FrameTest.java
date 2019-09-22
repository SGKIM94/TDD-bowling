package domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameTest {
    private final Score score = new Score(10);

    @Test
    public void 볼을_던진_횟수와_점수를_계산한다() {
        int ballThrowCount = 1;

        Frame frame = new Frame(score, new BallThrowCount(ballThrowCount));

        assertThat(frame.canSkipThisFrame()).isEqualTo(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 볼던지는_횟수가_옳바른_값이_아니면_예외를_처리한다() {
        Frame frame = new Frame(score, new BallThrowCount(3));

        frame.checkBallThrowCount(new BallThrowCount(3));
    }

    @Test
    public void 볼던진_횟수가_2회인가() {
        Frame frame = new Frame(score, new BallThrowCount(2));

        assertThat(frame.isSecondBallThrowing()).isEqualTo(true);
    }

    @Test
    public void 비어있는_frame_인가() {
        Frame frame = new Frame();

        assertThat(frame.isEmptyFrame()).isEqualTo(true);
    }

    @Test
    public void sumTotalScore_정상구동_테스트() {
        Frame frame = new Frame(new Score(8), new BallThrowCount(1));

        //TODO: equals 오버라이드 필요
        assertThat(frame.sumTotalScore(new Score(2)).getTotalScore()).isEqualTo(10);
    }

    @Test
    public void Frame_을_생성할때_저장되었던_점수들이_총점에_더해져야한다() {
        Frame frame = new Frame(new Score(5), new BallThrowCount(1));
        Frame secondFrame = new Frame(new Score(3), new BallThrowCount(2));
        Frames frames = new Frames();
        frames.add(frame).add(secondFrame);

        assertThat(frame.getPrimaryTotalScore()).isEqualTo(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 볼_던진_횟수가_올바르지_않을때_에외를던지는가() {
        Frame frame = new Frame();

        frame.checkBallThrowCount(new BallThrowCount(3));
    }

    @Test
    public void scoreDisplay_를_더해주는가() {
        //given
        Frame frame = new Frame(new Score(5), new BallThrowCount(1));

        //when
        frame.appendScoreDisplay("3");

        //then
        assertThat(frame.getDisplayScore()).isEqualTo("5|3");
    }

    @Test
    public void 이전_scoreDisplay_가_스패어인경우_첫점수를_한번더_더해주는가() {
        //given
        Frames frames = new Frames();
        ScoreDisplays scoreDisplays;

        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(5), new ScoreDisplays());
        scoreDisplays = frames.makeScoreDisplayAndAddFrame(new Score(5), scoreDisplays);
        frames.makeScoreDisplayAndAddFrame(new Score(3), scoreDisplays);

        //when
        frames.addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare
                        (frames.getBeforeFrame(), new Frame(new Score(3), new BallThrowCount(2)));

        //then
        assertThat(frames.get(0).getPrimaryTotalScore()).isEqualTo(13);
    }

    @Test
    public void 이전_scoreDisplay_가_스트라이크인경우_첫점수를_한번더_더해주는가() {
        //given
        Frames frames = new Frames();
        frames.makeScoreDisplayAndAddFrame(new Score(10), new ScoreDisplays());

        //when
        frames.addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike
                        (frames.getBeforeFrame(), new Frame(new Score(10), new BallThrowCount(2)));

        //then
        assertThat(frames.get(0).getPrimaryTotalScore()).isEqualTo(20);
    }
}
