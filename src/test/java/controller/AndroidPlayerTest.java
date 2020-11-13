package controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class AndroidPlayerTest {

	@Test
	public void androidPlayerTest() {
		AndroidPlayer player;
		List<Ship> shipList= Arrays.asList(new Ship(1));
		player = new AndroidPlayer(3,3,shipList);
		Ship shipExample[] = {new Ship(1)};
		int[][] boardExample = new int[][] {{0,0,0},{0,0,0},{0,0,0}};
		
		assertFalse(player.canGraceShot);
		assertFalse(player.graceShotHit);
		assertArrayEquals(player.ships.toArray(),shipExample);
		assertArrayEquals(player.board,boardExample);
		
	} 

}
