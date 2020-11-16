package controller;

import java.util.List;

public class HumanPlayer extends Player {
	public HumanPlayer(int m, int n, List<Ship> ships) {
		super(m, n, ships);
		positionShips();
	}
	public Coord aim() {
		int i, j;
		while(true) {
			Main.graphics.drawPlayerBoards(board, opposingPlayer.board);
			Coord coord = Main.graphics.drawShoot();
			i = coord.i; j = coord.j;
			final int M = opposingPlayer.board.length;
			final int N = opposingPlayer.board[0].length;
			// 4 equivalent partitions
			if(i < 0 || j < 0 || i >= M || i >= N) {
				Main.graphics.drawOutOfBoard();
			}
		}
		return new Coord(i, j);
	}
}
