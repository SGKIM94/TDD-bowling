public class Player {

    public static final int MINIMUM_PLAYER_LENGTH = 3;

    public static void checkName(String player) {
        checkPlayerIsNull(player);

        checkPlayerNameLengthLongerThanThree(player);
    }

    private static void checkPlayerNameLengthLongerThanThree(String player) {
        if (player.length() > MINIMUM_PLAYER_LENGTH) {
            throw new IllegalArgumentException("참가자 이름이 너무 깁니다.");
        }
    }

    private static void checkPlayerIsNull(String player) {
        if (player.isEmpty()) {
            throw new IllegalArgumentException("잘못된 참가자 이름입니다.");
        }
    }
}
