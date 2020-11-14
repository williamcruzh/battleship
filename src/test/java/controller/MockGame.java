package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MockGame extends Game{
	public MockGame(List<Integer> actual, int starter, int ender, int nLastPlayByPlayer, 
			        int wayOfEnd) {
		super();
		player1 = new MockPlayer(actual, 0, ender, nLastPlayByPlayer, wayOfEnd);
		player2 = new MockPlayer(actual, 1, ender, nLastPlayByPlayer, wayOfEnd);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		random = new MockRandom(starter);
	}
}
