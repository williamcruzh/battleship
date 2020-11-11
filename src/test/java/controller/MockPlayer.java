package controller;

import java.util.List;
import java.util.Vector;

public class MockPlayer extends Player {
	private Coord[] coordinatesOfSteps;
    private Orientation[] orientationsOfSteps;
    private int i;
	private List<int[][]> boardsBackup;
	
	public MockPlayer() {
		super(10, 10, null);
	}
	public MockPlayer(int[][] board, List<Ship> ships) {
		super(10, 10, ships);
		this.board = board;
		this.ships = ships;
	}
	public MockPlayer(int m, int n, List<Ship> ships, Coord[] coordinatesOfSteps, 
			          Orientation[] orientationsOfSteps) {
		super(m, n, ships);
		this.coordinatesOfSteps = coordinatesOfSteps;
		this.orientationsOfSteps = orientationsOfSteps;
		i = 0;
		boardsBackup = new Vector();
	}
	@Override
	public void aim(Integer i, Integer j) {
		// TODO Auto-generated method stub
		
	}
	public List<int[][]> getBoardsBackup() {
		return boardsBackup;
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
	public void positionShip(Ship ship) {
		Coord coord = coordinatesOfSteps[i];
		ship.setPosition(coord.i, coord.j);
		ship.setOrientation(orientationsOfSteps[i]);
		i++;
		boardsBackup.add(copy2DArray(this.board));
	}
	public void makeShipList(int nShips, boolean sunkF) {
		
		for(int i = 0; i < nShips; i++ ) {
			this.ships.add(new MockShip(3, false));
			
		}
		
		if(sunkF) {
			this.ships.add(new MockShip(2, true));
		}
	}
}
