package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	private Player player;
	/*
	 * Here we are doing decision coverage. 
	 */
	@Test
	public void shootTest() {
		int[][] board;
		
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
		
		player = new MockPlayer();
		board = new int[][] {{1}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertThrows(Exception.class, () -> player.shoot(0, 0));
		
		player = new MockPlayer();
		board = new int[][] {{4}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertThrows(Exception.class, () -> player.shoot(0, 0));
	}
}
