package controller;
import java.util.List;
import java.util.Vector;

public class MockPlayerTest extends Player {
	
	private boolean overrideShoot;
	private boolean overridePlay;
	private boolean overrideIsWinner;
	private boolean overrideTie;
	public MockPlayerTest(int[][] board, List<Ship> ships) {
		super(1, 1, ships);
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

	@Override
	public void play() {
		if(!overridePlay) {
			super.play();
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
	@Override
	public Coord aim() {
		return null;
	}
	@Override
	public void drawLoses() {}
	@Override
	public void drawIsWinner() {}
	@Override
	public void drawTies() {}
	@Override
	protected void positionShip(Ship ship) {}
}