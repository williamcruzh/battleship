package controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShipTest {
	/*
	 * Here we are doing decision coverage, at first. And condition coverage
	 * later. 
	 */
	@Test
	public void fitsInBoardTest() {
		// Decision coverage
		Ship ship = new Ship(5);
		int m = 10, n = 10;
		
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(5, 0);
		assertTrue(ship.fitsInBoard(m, n));
		
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(6, 0);
		assertFalse(ship.fitsInBoard(m, n));
		
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 5);
		assertTrue(ship.fitsInBoard(m, n));
		
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 6);
		assertFalse(ship.fitsInBoard(m, n));
		
		// Condition coverage
		ship = new Ship(11);
		m = 20; n = 20;
		
		// 0 0 0
		// Impossible
		
		// 0 0 1
		// Impossible
		
		// 0 1 0
		ship = new Ship(21);
		m = 20; n = 20;
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(-1, 0);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 0 1 1
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(-1, 0);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 0 0
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(20, 0);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 0 1
		// Impossible
		
		// 1 1 0
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(17, 0);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 1 1
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(4, 0);
		assertTrue(ship.fitsInBoard(m, n));

		// 0 0 0
		// Impossible
		
		// 0 0 1
		// Impossible
		
		// 0 1 0
		ship = new Ship(21);
		m = 20; n = 20;
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, -1);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 0 1 1
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, -1);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 0 0
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 20);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 0 1
		// Impossible
		
		// 1 1 0
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 17);
		assertFalse(ship.fitsInBoard(m, n));
		
		// 1 1 1
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 4);
		assertTrue(ship.fitsInBoard(m, n));
		
	}
	/*
	 * Here we are doing decision coverage, at first. And condition coverage
	 * later. 
	 */
	@Test
	public void isHitTest() {
		// Decision Coverage
		int m = 8; int n = 1;
		Ship ship = new Ship(7);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(0, 0);
		
		assertTrue(ship.isHit(6, 0));
		
		assertFalse(ship.isHit(7, 0));
		
		m = 1; n = 8;
		ship.setOrientation(Orientation.HORIZONTAL);
		
		assertTrue(ship.isHit(0, 6));
		
		assertFalse(ship.isHit(0, 7));
		
		// Condition coverage
		m = 7; n = 2;
		ship = new Ship(5);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(1, 0);
		
		// 0 0 0
		// Impossible 
		
		// 0 0 1
		// Impossible 
		
		// 0 1 0
		ship.isHit(0, 1);
		
	}
	@Test
	public void TestisSunk() {
		Ship ship;
			
		//case ship vertical sunk = true
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		ship.shoot(3,4);
		ship.shoot(4,4);
		assertTrue(ship.isSunk());
		
		//case ship vertical sunk = false
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		ship.shoot(4,4);
		assertFalse(ship.isSunk());
	}
	@Test
	public void Testshoot() {
		
		Ship ship;
		
		//case ship size = 3. Vertical. hit zone 2. not sunk
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(3,4);
		assertTrue(ship.isHit(3, 4));
		assertFalse(ship.isSunk());
		
		//case ship size = 3. Vertical. hit zone 3. not sunk
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(4,4);
		assertTrue(ship.isHit(4, 4));
		assertFalse(ship.isSunk());
		
		//case ship size = 5. Vertical. hit zone 1. not sunk
		ship = new Ship(5);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		assertTrue(ship.isHit(2, 4));
		assertFalse(ship.isSunk());
			
		//case ship size = 5. Vertical. hit zone 5. not sunk
		ship = new Ship(5);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(7,4);
		assertTrue(ship.isHit(7, 4));
		assertFalse(ship.isSunk());
		
		//case ship size 3 sunk. Vertical
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		ship.shoot(3,4);
		ship.shoot(4,4);
		assertTrue(ship.isSunk());
		
		
		//case ship size 5 sunk. Vertical
		ship = new Ship(5);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		ship.shoot(3,4);
		ship.shoot(4,4);
		ship.shoot(5,4);
		ship.shoot(6,4);
		assertTrue(ship.isSunk());
	}
}
