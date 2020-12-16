package controller;

import java.util.List;
import java.util.Vector;

public class MockPlayer extends Player {
	private Coord[] coordinatesOfSteps;
    private Orientation[] orientationsOfSteps;
    private int i;
	private List<int[][]> boardsBackup;
	private boolean overrideShoot;
	private boolean overridePlay;
	private boolean overrideIsWinner;
	private boolean overrideTie;
	private List<Integer> actual;
	private int playerNumber;
	private int ender;
	private int nLastPlayByPlayer;
	private int wayOfEnd;
	
	public MockPlayer() {
		super(1, 1, null);
		overrideShoot = false;
		overridePlay = false;
		overrideIsWinner = false;
		overrideTie = false;
	}
	public MockPlayer(boolean withoutShoot) {
		super(1, 1, null);
		overrideShoot = withoutShoot;
		overridePlay = false;
		overrideIsWinner = false;
		overrideTie = false;
	}
	public MockPlayer(List<Integer> actual, int playerNumber, int ender, 
			          int nLastPlayByPlayer, int wayOfEnd) {
		super(1, 1, null);
		overrideShoot = false;
		overridePlay = true;
		overrideIsWinner = true;
		overrideTie = true;
		this.actual = actual;
		this.playerNumber = playerNumber;
		this.ender = ender;
		this.nLastPlayByPlayer = nLastPlayByPlayer;
		this.wayOfEnd = wayOfEnd;
	}
	public MockPlayer(int[][] board, List<Ship> ships) {
		super(1, 1, null);
		overrideShoot = false;
		overridePlay = false;
		overrideIsWinner = false;
		overrideTie = false;
		this.board = board;
		this.ships = ships;
		for(Ship ship: ships) {
			ship.updateCoordinates();
		}
	}
	public MockPlayer(int m, int n, List<Ship> ships, Coord[] coordinatesOfSteps, 
			          Orientation[] orientationsOfSteps) {
		super(m, n, ships);
		overrideShoot = false;
		overridePlay = false;
		overrideIsWinner = false;
		overrideTie = false;
		this.coordinatesOfSteps = coordinatesOfSteps;
		this.orientationsOfSteps = orientationsOfSteps;
		i = 0;
		boardsBackup = new Vector();
	}
	public void drawLoses() {
		
	}
	public void drawIsWinner() {
		
	}
	public void drawTies() {
		
	}
	@Override
	public void play() {
		if(!overridePlay) {
			super.play();
		}
		else {
			if(actual.size() > nLastPlayByPlayer) {
				System.out.println("Exception: Error in test of play() in Game"); 
				System.exit(1);
			}
			actual.add(playerNumber);
		}
	}
	public void aim(Integer i, Integer j) {
		
	}
	@Override 
	public void shoot(int i, int j) {
		if(!overrideShoot) {
			super.shoot(i, j);
		}
		else {
			
		}
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
	@Override
	public boolean isWinner() {
		if(!overrideIsWinner) {
			return super.isWinner();
		}
		else {
			if(actual.size() == nLastPlayByPlayer && playerNumber == ender
					&& wayOfEnd == 0) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	@Override
	public boolean tie() {
		if(!overrideTie) {
			return super.tie();
		}
		else {
			if(actual.size() == nLastPlayByPlayer && wayOfEnd == 1) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	@Override
	public Coord aim() {
		// TODO Auto-generated method stub
		return null;
	}
}
