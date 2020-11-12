package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	private Player player;
	/*
	 * Here we are doing decision coverage. 
	 */
	@Test
	public void setShipZoneTest() {
		MockPlayer player;
		
		int[][] board= new int[3][3];
		Ship ship = new Ship(1);
		List<Ship> shipList = Arrays.asList(ship);
		
		player=new MockPlayer(board,shipList);
		player.ships.get(0).setPosition(1, 1);
		player.ships.get(0).updateCoordinates();
		
		player.setShipZone(board, player.ships.get(0), 2, 1);
		int[][] boardExample = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
		assertArrayEquals(player.board,boardExample);
	}
	@Test
	public void shootTest() {
		int[][] board;
		
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertEquals(board[0][0], 1);
		
		player = new MockPlayer();
		board = new int[][] {{3}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertEquals(board[0][0], 4);
		
		player = new MockPlayer();
		board = new int[][] {{1}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertThrows(Exception.class, () -> player.shoot(0, 0));
		
		player = new MockPlayer();
		board = new int[][] {{4}};
		player.setOpponent(new MockPlayer(board, null));
		player.shoot(0, 0);
		assertThrows(Exception.class, () -> player.shoot(0, 0));
	}
	
	@Test
	public void isWinnerTest() {
		
		int[][] board;
		Player player;
		
		//case n = 2 false
		player = new MockPlayer();
		board = new int[][] {{0}};
		List<Ship> shipList = Arrays.asList(new Ship(1),new Ship(1));
		shipList.get(0).sunk = true;
		shipList.get(1).sunk = false;
		
		player.setOpponent(new MockPlayer(board,shipList));
		player.nhits=4;
		player.opposingPlayer.nhits=2;
		player.opposingPlayer.graceShot = false;
		assertFalse(player.isWinner());
		
		//case n = 2 true
		shipList.get(1).sunk = true;
		
		player.setOpponent(new MockPlayer(board,shipList));
		player.nhits=5;
		player.opposingPlayer.nhits=3;
		player.opposingPlayer.graceShot = false;
		assertTrue(player.isWinner());
		
		//case n = 2 false with graceShot
		player.setOpponent(new MockPlayer(board,shipList));	
		player.opposingPlayer.nhits=4;
		player.opposingPlayer.graceShot = true;
		assertFalse(player.isWinner());	
	}
	@Test
	public void tieTest() {
		
		int[][] board;
		Player player;
		
		//case true
		board = new int[][] {{0}};
		List<Ship> shipList = Arrays.asList(new Ship(1),new Ship(1));
		List<Ship> shipList2 = Arrays.asList(new Ship(1),new Ship(1));
		shipList.get(0).sunk = true;
		shipList.get(1).sunk = true;
		shipList2.get(0).sunk = true;
		shipList2.get(1).sunk = true;
		
		player = new MockPlayer(board,shipList);
		player.setOpponent(new MockPlayer(board,shipList2));
		assertTrue(player.tie());
		
		//case false fail first player
		shipList.get(1).sunk = false;
		
		player = new MockPlayer(board,shipList);
		player.setOpponent(new MockPlayer(board,shipList2));
		assertFalse(player.tie());
		
		//case false fail second player
		shipList.get(1).sunk = true;
		shipList2.get(0).sunk = false;
		
		player = new MockPlayer(board,shipList);
		player.setOpponent(new MockPlayer(board,shipList2));
		assertFalse(player.tie());
	}
}
