package domain;

public class Round {
    private final int round;

    public Round(int round) {
        checkRound();
        this.round = round;
    }

    private void checkRound() {
        throw new IllegalArgumentException("잘못된 라운드를 입력하였습니다..");
    }

    public int getRound() {
        return this.round;
    }
}
