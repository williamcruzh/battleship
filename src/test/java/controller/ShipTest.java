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
	@Test
	public void TestisSunk() {
		
		Ship ship;
		
		//case n = 0
		ship = new MockShip(0,false);
		assertFalse(ship.isSunk());
		// case n = 1 false
		ship = new MockShip(0,true);
		assertFalse(ship.isSunk());
		// case n = 1 true
		ship = new MockShip(1,false);
		assertFalse(ship.isSunk());
		
	}
}
