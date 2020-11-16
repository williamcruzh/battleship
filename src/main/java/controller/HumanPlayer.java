package controller;

import java.util.List;

public class HumanPlayer extends Player {
	public HumanPlayer(int m, int n, List<Ship> ships) {
		super(m, n, ships);
		/* 
		   TODO: if menu say it, print a default board.
		 */
		positionShips();
	}
	public void drawLoses() {
		Main.graphics.drawLose();
	}
	public void drawIsWinner() {
		Main.graphics.drawWin();
	}
	public void drawTies() {
		Main.graphics.drawTie();
	}
	public void positionShip(Ship ship) {
		Main.graphics.drawCreationBoardsScreen(board);
		Coord coord = Main.graphics.drawMoveShip(ship.getSize());
		Orientation orientation = Main.graphics.drawRotateShip();
		ship.setPosition(coord.i, coord.j);
		ship.setOrientation(orientation);
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
			else if(opposingPlayer.board[i][j] == 2 && 
					opposingPlayer.board[i][j] == 3 && 
					opposingPlayer.board[i][j] == 4) {
				Main.graphics.drawSquareAlreadySelected();
			}
			else {
				break;
			}
		}
		return new Coord(i, j);
	}
}
