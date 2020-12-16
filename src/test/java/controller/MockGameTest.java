package controller;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MockGameTest extends Game{
	MockPlayerTest player1 ,player2;
	public MockGameTest(int[][] board, List<Ship> ships, int starter) {
		super();
		player1 = new MockPlayerTest(board, ships);
		player2 = new MockPlayerTest(board, ships);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		random = new MockRandom(starter);
	}

	public MockGameTest(int h, int w, int[]ships, List<Integer> actual, int starter, int ender, int nLastPlayByPlayer, 
	        int wayOfEnd) throws Exception {
		super(h,w,ships);
		MockPlayer player1 = new MockPlayer(actual, 0, ender, nLastPlayByPlayer, wayOfEnd);
		MockPlayer player2 = new MockPlayer(actual, 1, ender, nLastPlayByPlayer, wayOfEnd);
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		random = new MockRandom(starter);
	}
}