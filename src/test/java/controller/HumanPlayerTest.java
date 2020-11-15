
package controller;
import java.util.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HumanPlayerTest {
	
	@Test
	public void restTest() {
		HumanPlayer player;
		List<Ship> shipList= Arrays.asList(new Ship(1),new Ship(1),new Ship(2),new Ship(2),new Ship(3),new Ship(3),new Ship(4));
		player = new HumanPlayer(7,7,shipList);
		player.drawIsWinner();
		player.drawLoses();
		player.drawTies();
	}
	@Test
	public void aimTest() {
		HumanPlayer player;
		Player player2;
		List<Ship> shipList= Arrays.asList(new Ship(1),new Ship(1));
		List<Ship> shipList2= Arrays.asList(new Ship(1),new Ship(3));
		int[][] board2= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		player = new HumanPlayer(4,4,shipList);	
		player2 = new MockPlayer(board2,shipList2);
		player.setOpponent(player2);
		
		player2.ships.get(0).setOrientation(Orientation.VERTICAL);
		player2.ships.get(0).setPosition(1, 0);
		player2.ships.get(0).updateCoordinates();
		player2.setShip(board2, player.ships.get(0), 1);
		
		player2.ships.get(1).setOrientation(Orientation.HORIZONTAL);
		player2.ships.get(1).setPosition(3,1);
		player2.ships.get(1).updateCoordinates();
		player2.setShip(board2, player.ships.get(1), 1);
		
		Coord coord = player.aim();
		player.shoot(coord.i,coord.j);
		coord = player.aim();
		player.shoot(coord.i,coord.j);
		coord = player.aim();
		player.shoot(coord.i,coord.j);
	}
	
}
