<<<<<<< HEAD
package battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {

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

=======
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
		Ship ship = new Ship(5);
		int m = 10, n = 10;
		
		// Decision coverage
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
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(5, 0);
		assertTrue(ship.fitsInBoard(m, n));
		
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(6, 0);
		assertFalse(ship.fitsInBoard(m, n));
	}
>>>>>>> ff3e964... Adds test for fitsInBoard()
}
