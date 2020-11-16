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
	protected void positionShip(Ship ship) {
		ship.setPosition(random.nextInt(board.length), random.nextInt(board[0].length));
		if(random.nextBoolean()) {
			ship.setOrientation(Orientation.VERTICAL);
		}
		else {
			ship.setOrientation(Orientation.HORIZONTAL);
		}
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
