package controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShipTest {
	/*
	 * Statement Coverage
	 */
	@Test
	public void ShipTest() {
		Ship ship = new Ship(3);	
		assertEquals(ship.i, 0);
		assertEquals(ship.j, 0);
		assertEquals(ship.orientation, Orientation.VERTICAL);
		assertNotEquals(ship.damagedZones, null);
		assertArrayEquals(ship.damagedZones, new boolean[] {false, false, false});
		assertTrue(!ship.sunk);
		assertNotEquals(ship.coordinates, null);
	}
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
		
		// 0 1 1
		ship.isHit(0, 0);
		
		// 1 0 0
		ship.isHit(6, 1);
		
		// 1 0 1
		ship.isHit(6, 0);
		
		// 1 1 0
		ship.isHit(5, 1);
		
		// 1 1 1
		ship.isHit(5, 0);
		
		m = 2; n = 7;
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(0, 1);

		// 0 0 0
		// Impossible 
		
		// 0 0 1
		// Impossible 
		
		// 0 1 0
		ship.isHit(1, 0);
		
		// 0 1 1
		ship.isHit(0, 0);
		
		// 1 0 0
		ship.isHit(1, 6);
		
		// 1 0 1
		ship.isHit(0, 6);
		
		// 1 1 0
		ship.isHit(1, 5);
		
		// 1 1 1
		ship.isHit(0, 5);
	}
	@Test
	public void setOrientationTest() {
		MockShip ship = new MockShip(5);
		ship.setOrientation(Orientation.VERTICAL);
		assertEquals(ship.orientation, Orientation.VERTICAL);
		ship.setOrientation(Orientation.HORIZONTAL);
		assertEquals(ship.orientation, Orientation.HORIZONTAL);
	}
	@Test
	public void isSunkTest() {
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
		
		//case ship vertical sunk = true
		ship = new Ship(3);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(2,4);
		ship.shoot(2,4);
		ship.shoot(2,5);
		ship.shoot(2,6);
		assertTrue(ship.isSunk());
		
		//case ship vertical sunk = false
		ship = new Ship(3);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(2,4);
		ship.shoot(2,6);
		ship.shoot(2,5);
		assertFalse(ship.isSunk());
		
	}
	@Test
	public void shootTest() {
		Ship ship;
		
		//case ship size = 3. Vertical. hit zone 2. not sunk
		ship = new Ship(3);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(3,4);
		assertTrue(ship.isHit(3, 4));
		assertFalse(ship.isSunk());
			
		//case ship size = 5. Vertical. hit zone 5. not sunk
		ship = new Ship(5);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,4);
		ship.shoot(6,4);
		assertTrue(ship.isHit(6, 4));
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
		
		
		//case ship size = 2. Horizontal. hit zone 1. not sunk
		ship = new Ship(2);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(4,1);
		ship.shoot(4,1);
		assertTrue(ship.isHit(4, 1));
		assertFalse(ship.isSunk());
		
		//case ship size = 4. Horizontal. hit zone 2. not sunk
		ship = new Ship(4);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(4,1);
		ship.shoot(4,2);
		assertTrue(ship.isHit(4, 2));
		assertFalse(ship.isSunk());
		
		//case ship size 2 sunk. Horizontal
		ship = new Ship(2);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(4,1);
		ship.shoot(4,1);
		ship.shoot(4,2);
		assertTrue(ship.isSunk());
		
		//case ship size 4 sunk. Horizontal
		ship = new Ship(4);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(4,1);
		ship.shoot(4,1);
		ship.shoot(4,2);
		ship.shoot(4,3);
		ship.shoot(4,4);
		assertTrue(ship.isSunk());
		
	}
	@Test
	public void updateCoordinatesTest() {
		Ship ship;
		
		
		
		//ship size = 1
		ship = new Ship(1);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,3);
		ship.updateCoordinates();
		Coord coordinate[] = new Coord[1];
		coordinate[0] = new Coord(2,3);; 
		assertTrue(coordinate==ship.getCoordinates());
		
		//ship = 2 Vertical
		ship = new Ship(2);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(3,5);
		ship.updateCoordinates();
		Coord coordinate2[] = new Coord[2];
		coordinate2[0] = new Coord(3,5);
		coordinate2[1] = new Coord(4,5);
		assertTrue(coordinate==ship.getCoordinates());
		
		//ship = 2 Horizontal
		ship = new Ship(2);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(1,1);
		ship.updateCoordinates();
		coordinate2[0] = new Coord(1,1);
		coordinate2[1] = new Coord(1,2);
		assertTrue(coordinate==ship.getCoordinates());
		
		//ship = 4 Vertical
		ship = new Ship(4);
		ship.setOrientation(Orientation.VERTICAL);
		ship.setPosition(2,7);
		ship.updateCoordinates();
		Coord coordinate4[] = new Coord[4];
		coordinate4[0] = new Coord(2,7);
		coordinate4[1] = new Coord(3,7);
		coordinate4[2] = new Coord(4,7);
		coordinate4[3] = new Coord(5,7);
		assertTrue(coordinate==ship.getCoordinates());
		
		//ship = 4 Horizontal
		ship = new Ship(2);
		ship.setOrientation(Orientation.HORIZONTAL);
		ship.setPosition(6,3);
		ship.updateCoordinates();
		coordinate4[0] = new Coord(6,3);
		coordinate4[1] = new Coord(6,4);
		coordinate4[2] = new Coord(6,5);
		coordinate4[3] = new Coord(6,6);
		assertTrue(coordinate==ship.getCoordinates());
	}
}
