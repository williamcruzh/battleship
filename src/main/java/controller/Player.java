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
		List<Ship> newList = Arrays.asList(new Ship[ships.size()]);
		for(int i = 0; i < ships.size(); i++) {
			newList.set(i, new Ship(ships.get(i).getSize()));
		}
		this.ships = newList;
		canGraceShot = false;
	}
	public abstract Coord aim();
	public abstract void drawLoses();
	public abstract void drawIsWinner();
	public abstract void drawTies();
	public void play() {
		Coord coord = aim();
		shoot(coord.i, coord.j);
	}
	public void setOpponent(Player opposingPlayer) {
		this.opposingPlayer = opposingPlayer;
	}
	private int[][] copy2DArray(int[][] source) {
		int[][] destination = new int[source.length][source[0].length];
		for(int i = 0; i < source.length; ++i) {
			for(int j = 0; j < source[0].length; ++j) {
				destination[i][j] = source[i][j];
			}
		}
		return destination;
	}
	protected void positionShips() {
		int m = board.length; int n = board[0].length;
		int[][] mockBoard = copy2DArray(board);
		boolean fitsInBoard;
		boolean thereIsColision;
		for(Ship ship: ships) {
			while(true) {
				positionShip(ship);
				ship.updateCoordinates();
				fitsInBoard = ship.fitsInBoard(m, n);
				thereIsColision = false;
				if(fitsInBoard) {
					for(Coord coordinate: ship.getCoordinates()) {
						if(mockBoard[coordinate.i][coordinate.j] == 1) {
							thereIsColision = true;
							break;
						}
					}
				}
				if(fitsInBoard && !thereIsColision) {
					setShip(mockBoard, ship, 1);
					setShipZone(mockBoard, ship, 1, 1);
					setShip(board, ship, 1);
					break;
				}
			}
		}
	}
	protected abstract void positionShip(Ship ship);
	protected void setShip(int[][] board, Ship ship, int paddingValue) {
		for(Coord coordinate: ship.getCoordinates()) {
			board[coordinate.i][coordinate.j] = paddingValue;
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
			// 4
			if(i + 1 < m &&  j + 1 < n && board[i + 1][j + 1] != shipPaddingValue) {
				board[i + 1][j + 1] = shipZonePaddingValue;
			}
			// 5
			if(i + 1 < m && board[i + 1][j] != shipPaddingValue) {
				board[i + 1][j] = shipZonePaddingValue;
			}
			// 6
			if(i + 1 < m &&  j - 1 >= 0 && board[i + 1][j - 1] != shipPaddingValue) {
				board[i + 1][j - 1] = shipZonePaddingValue;
			}
			// 7
			if(j - 1 >= 0 && board[i][j - 1] != shipPaddingValue) {
				board[i][j - 1] = shipZonePaddingValue;
			}
			// 8
			if(i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] != shipPaddingValue) {
				board[i - 1][j - 1] = shipZonePaddingValue;
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
		if(isWater) {
			opposingPlayer.board[i][j] = 2;
			if(canGraceShot) {
				graceShotHit = false;
			}
		}
		else {
			if(canGraceShot) {
				graceShotHit = true;
			}
			opposingPlayer.board[i][j] = 3;
			hittedShip.shoot(i, j);
			if(hittedShip.isSunk()){
				setShip(opposingPlayer.board, hittedShip, 4);
				setShipZone(opposingPlayer.board, hittedShip, 4, 2);
			}
		}
	}
	public boolean isWinner() { 
		List<Ship> shipList =  opposingPlayer.ships;
		for(int i = 0; i < shipList.size();i++) {
			if(!shipList.get(i).isSunk()) {
				return false;
			}
		}
		if((this.opposingPlayer.nhits+1 == this.nhits)&& !opposingPlayer.canGraceShot) {
				opposingPlayer.canGraceShot = true;
				return false;				
			}
		if(opposingPlayer.graceShotHit) {
			return false;
		}
		
		return true;
	}
	public boolean tie() {
		boolean tie = true;
	
		for(int i = 0; i < this.ships.size();i++) {
			if(!this.ships.get(i).isSunk()) {
				tie = false;
				break;
			}
		}
		if(tie) {
			for(int i = 0; i < this.opposingPlayer.ships.size();i++) {
				if(!this.opposingPlayer.ships.get(i).isSunk()) {
					tie = false;
					break;
				}
			}
		}
		return tie;
	}
}
