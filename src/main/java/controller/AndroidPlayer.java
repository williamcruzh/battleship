package controller;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import controller.Orientation;

public class AndroidPlayer extends Player {
	private Random random;
	
	public AndroidPlayer(int m, int n, List<Ship> ships) {
		super(m, n, ships);
		random = new Random();
		positionShips(ships.size());
	}
	public void drawLoses() {
		
	}
	public void drawIsWinner() {
		
	}
	public void drawTies() {
		
	}
	protected void positionShip(Ship ship) {
		ship.setPosition(random.nextInt(board.length), random.nextInt(board[0].length));
		if(random.nextBoolean()) {
			ship.setOrientation(Orientation.VERTICAL);
		}
		else {
			ship.setOrientation(Orientation.HORIZONTAL);
		}
	}
	private List<Coord> getFrontAndBack(int i, int j, Orientation orientation, int m, int n) {
		List<Coord> limits = new Vector<>();
		Coord inferiorLimit = new Coord(i, j);
		Coord superiorLimit = new Coord(i, j);
		boolean inferiorLimitInside = true, superiorLimitInside = true;
		if(orientation == Orientation.VERTICAL) {
			do {
				inferiorLimit.i--;
				if(inferiorLimit.i < 0) {
					inferiorLimitInside = false;
				}
			} while(inferiorLimitInside && opposingPlayer.board[inferiorLimit.i][inferiorLimit.j] == 3);
			do {
				superiorLimit.i++;
				if(superiorLimit.i >= m) {
					superiorLimitInside = false;
				}
			}
			while(superiorLimitInside && opposingPlayer.board[superiorLimit.i][superiorLimit.j] == 3);
		}
		if(inferiorLimitInside) {
			limits.add(inferiorLimit);
		}
		if(superiorLimitInside) {
			limits.add(superiorLimit);
		}
		return limits;
	}
	public Coord aim() {
		int i, j = 0;
		boolean thereIsHittedShip = false;
		int m = opposingPlayer.board.length;
		int n = opposingPlayer.board[0].length;
		for(i = 0; i < m; ++i) {
			for(j = 0; j < n; ++j) {
				if(opposingPlayer.board[i][j] == 3) {
					thereIsHittedShip = true;
					break;
				}
			}
			if(thereIsHittedShip) break;
		}
		if(thereIsHittedShip) {
			Orientation orientation = null;
			boolean onlyOneZoneDamaged = true;
			List<Coord> coordinates = new Vector();
			if(i - 1 >= 0 && opposingPlayer.board[i - 1][j] == 3) {
				orientation = Orientation.VERTICAL;
				onlyOneZoneDamaged = false;
			}
			else if (i - 1 >= 0 && (opposingPlayer.board[i - 1][j] == 0 ||
					opposingPlayer.board[i - 1][j] == 1)) {
				coordinates.add(new Coord(i - 1, j));
			}
			if(j + 1 < n && opposingPlayer.board[i][j + 1] == 3) {
				orientation = Orientation.HORIZONTAL;
				onlyOneZoneDamaged = false;
			}
			else if (j + 1 < n && (opposingPlayer.board[i][j + 1] == 0 ||
					 opposingPlayer.board[i][j + 1] == 1)) {
				coordinates.add(new Coord(i, j + 1));
			}
			if(i + 1 < m && opposingPlayer.board[i + 1][j] == 3) {
				orientation = Orientation.VERTICAL;
				onlyOneZoneDamaged = false;
			}
			else if (i + 1 < m && (opposingPlayer.board[i + 1][j] == 0 ||
					 opposingPlayer.board[i + 1][j] == 1)) {
				coordinates.add(new Coord(i + 1, j));
			}
			if(j - 1 >= 0 && opposingPlayer.board[i][j - 1] == 3) {
				orientation = Orientation.HORIZONTAL;
				onlyOneZoneDamaged = false;
			}
			else if(j - 1 >= 0 && (opposingPlayer.board[i][j - 1] == 0 ||
					 opposingPlayer.board[i][j - 1] == 1)) {
				coordinates.add(new Coord(i, j - 1));
			}
			Coord coordinate;
			if(onlyOneZoneDamaged) {
				coordinate = coordinates.get(random.nextInt(coordinates.size()));
			}
			else {	
				List<Coord> limits = getFrontAndBack(i, j, orientation, m, n);
				coordinate = limits.get(random.nextInt(limits.size()));
			}
			i = coordinate.i; j = coordinate.j;
		}
		else {
			do {
				i = random.nextInt(m);
				j = random.nextInt(n);
			} while(opposingPlayer.board[i][j] != 0 && 
					opposingPlayer.board[i][j] != 1);
		}
		return new Coord(i, j);
	}
}
