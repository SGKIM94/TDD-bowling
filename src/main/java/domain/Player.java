package domain;

public class Player {
    private static final int MINIMUM_PLAYER_LENGTH = 3;
    private String player;

    public Player(String player) {
        this.player = player;
    }

    void checkName() {
        checkPlayerIsNull();

        checkPlayerNameLengthLongerThanThree();
    }

    private void checkPlayerNameLengthLongerThanThree() {
        if (this.player.length() > MINIMUM_PLAYER_LENGTH) {
            throw new IllegalArgumentException("참가자 이름이 너무 깁니다.");
        }
    }

    private void checkPlayerIsNull() {
        if (this.player.isEmpty()) {
            throw new IllegalArgumentException("잘못된 참가자 이름입니다.");
        }
    }

    public void printName() {
        System.out.print("| " + this.player + "  |");

    }
}
