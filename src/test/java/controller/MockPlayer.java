package controller;

import java.util.List;

public class MockPlayer extends Player {
	
	
	public MockPlayer() {
		
	}
	
	
	public MockPlayer(int[][] board, List<Ship> ships) {
		this.board = board;
		this.ships = ships;
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
