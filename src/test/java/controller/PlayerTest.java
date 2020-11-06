package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {
	/*
	 * Here we are doing decision coverage. 
	 */
	@Test
	public void shootTest() {
		int[][] board;
		Player player;
		
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertEquals(board[0][0], 1);
		
		player = new MockPlayer();
		board = new int[][] {{3}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertEquals(board[0][0], 4);
	}
}
