package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MockGame extends Game{
	public MockGame(List<Boolean> expected, List<Boolean> actual, boolean ender,
			        int lastTurn, int wayOfEnd) {
		super();
		player1 = new MockPlayer(actual, false, ender, lastTurn, wayOfEnd);
		player2 = new MockPlayer(actual, true, ender, lastTurn, wayOfEnd);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		random = new MockRandom(expected);
	}
}
