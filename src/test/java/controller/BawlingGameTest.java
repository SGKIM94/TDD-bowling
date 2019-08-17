package controller;

import org.junit.Test;

public class BawlingGameTest {
    @Test
    public void mainTest() {
        BawlingGame bawlingGame = new BawlingGame("kim");

        bawlingGame.bowlingGameStart();
    }
}
