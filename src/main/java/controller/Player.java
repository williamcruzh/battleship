package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public abstract class Player {
	protected int[][] board;
	protected List<Ship> ships;
	protected Player opposingPlayer;
	protected int nhits;
	protected boolean canGraceShot;
	protected boolean graceShotHit;
	
	public Player(int height, int width, List<Ship> ships) {
		board = new int[height][width];
		for(int i = 0; i < height; ++i) {
			for(int j = 0; j < width; ++j) {
				board[i][j] = 0;
			}
		}
	}
	public void setOpponent(Player opposingPlayer) {
		opposingPlayer = opposingPlayer;
	}
	private int[][] copy2DArray(int[][] source) {
		int[][] destination = new int[source.length][source[0].length];
		for(int i = 0; i < source.length; ++i) {
			for(int j = 0; j < source[0].length; ++j) {
				source[i][j] == destination[i][j];
			}
		}
		return destination;
	}
	protected abstract void positionShip(Ship ship);
	protected void setShip(int[][] board, Ship ship, int paddingValue) {
		for(Coord coordinates: ship.getCoordinates()) {
			board[coordinates.i][coordinates.j] = paddingValue;
		}
	}
	protected void setShipZone(int[][] board, Ship ship, int shipPaddingValue, int shipZonePaddingValue) {
		int i,j, m, n;
		m = board.length;
		n = board[0].length;
		for(Coord coordinate : ship.getCoordinates()) {
			i = coordinate.i; j = coordinate.j;
			// 1
			if(i - 1 >= 0 && board[i - 1][j] != shipPaddingValue) {
				board[i - 1][j] = shipZonePaddingValue;
			}
			// 2
			if(i - 1 >= 0 &&  j + 1 < n && board[i - 1][j + 1] != shipPaddingValue) {
				board[i - 1][j + 1] = shipZonePaddingValue;
			}
			// 3
			if(j + 1 < n && board[i][j + 1] != shipPaddingValue) {
				board[i][j + 1] = shipZonePaddingValue;
			}
		}
	}
	public void shoot(int i, int j) {
		boolean isWater = true;
		Ship hittedShip = null;
		for (Ship ship: opposingPlayer.ships) {
			if (ship.isHit(i, j)) {
				isWater = false;
				hittedShip = ship;
				nhits++;
				break;
			}
		}
	}
}
