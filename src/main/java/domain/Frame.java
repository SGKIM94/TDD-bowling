package domain;

public class Frame {
    public static final int MAX_BALL_THROW_COUNT = 2;
    public static final int MINIMUM_BALL_THROW_COUNT = 0;

    //TODO : 프레임에 점수가 여러개 가지고 있어야 하는가?
    // 아니면 한개의 점수로 더 해진 값을 가지고 있어야 하는가?
    // 2번째 볼을 던진 경우는 어떻게 처리해야 될까?
    // 2번째 볼에 대해서는 더해주는 형태로 처리해서 display를 정해주면 딜듯
    // 한 Frame 을 기준으로 2번 볼을 던졌을 때
    // score 를 sum 을 해줌으로써 하나밖에 저장이 되어있지 않으면
    // 출력할 때 이전의 점수를 출력할 수 없음
    // 2개를 가지고 있어야 1번째와 2번재의 점수를 출력할 수 있음.
    // 2개를 가지고 있는 frame 과 1개이면서 점수가 10 점인 것과 그렇지 않은 frame 을
    // 구별해서 출력이나 점수를 계산하면 될 듯
    // Display 가 어디에 있는게 맞을지 고민해 볼 필요가 있을 듯

    private Score score;
    private BallThrowCount ballThrowCount;

    public Frame(Score score, BallThrowCount ballThrowCount) {
        this.score = score;
        this.ballThrowCount = ballThrowCount;
    }

    //TODO : 넘어갈 수 있는지 없는지 체크해서 넘어 간다면 다음 프레임에 점수를 저장하고
    // 넘어갈 수 없다면 한 프레임에 저장한다
    public boolean canSkipThisFrame() {
        ScoreGroup scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.score);

        return !ScoreGroup.ELSE.equals(scoreGroup);
    }

    public void checkBallThrowCount() {
        if (this.ballThrowCount.isBigger(MAX_BALL_THROW_COUNT) || this.ballThrowCount.isSmaller(MINIMUM_BALL_THROW_COUNT)) {
            throw new IllegalArgumentException("볼 던진 횟수의 값이 올바르지 않습니다.");
        }
    }

    public BallThrowCount getBallThrowCount() {
        return this.ballThrowCount;
    }

    public int getNextBallCount() {
        return this.ballThrowCount.getNextBallCount();
    }

    public Score getScore() {
        return this.score;
    }
}
