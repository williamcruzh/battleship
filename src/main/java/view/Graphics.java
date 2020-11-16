package view;

import controller.Coord;
import controller.Orientation;

public interface Graphics {
	void drawOutOfBoard();
	void drawSquareAlreadySelected();
	void drawPlayerBoards(int[][] board, int[][] opposingBoard);
	void drawCreationBoardsScreen(int[][] board);
	void drawLose();
	void drawWin();
	void drawTie();
	Coord drawMoveShip(int shipSize);
	Orientation drawRotateShip();
	Coord drawShoot();
}
