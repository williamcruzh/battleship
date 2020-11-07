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

}
