package controller;

import java.util.List;

public class MockPlayer extends Player {
	public MockPlayer() {
		
	}
	public MockPlayer(int[][] board, List<Ship> ships) {
		this.board = board;
		this.ships = ships;
	}

}
