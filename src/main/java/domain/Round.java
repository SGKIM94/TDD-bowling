package domain;

public class Round {
    public static final int MINIMUM_ROUND = 0;
    public static final int MAX_ROUND = 10;
    public static final int FIRST_ROUND = 1;
    public static final int NEXT_ROUND = 1;
    private final int round;

    public Round(int round) {
        checkRound(round);
        this.round = round;
    }

    private void checkRound(int round) {
        if (isSmallerThanMinimumRound(round) || isBiggerThanMaxRound(round)) {
            throw new IllegalArgumentException("잘못된 라운드를 입력하였습니다..");
        }
    }

    private boolean isSmallerThanMinimumRound(int round) {
        return round < MINIMUM_ROUND;
    }

    private boolean isBiggerThanMaxRound(int round) {
        return round > MAX_ROUND;
    }

    public int getRound() {
        return this.round;
    }

    public boolean isFirstRound() {
        return this.round == FIRST_ROUND;
    }

    public int addRound() {
        return this.round + NEXT_ROUND;
    }
}

