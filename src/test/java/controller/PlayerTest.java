package controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	private Player player;
	/*
	 * Here we are doing decision coverage. 
	 */
	@Test
	public void setShipTest() {
		MockPlayer player;
		
		int[][] board= new int[3][3];
		Ship ship = new Ship(1);
		List<Ship> shipList = Arrays.asList(ship);
		
		player=new MockPlayer(board,shipList);
		player.ships.get(0).setPosition(1, 1);
		player.ships.get(0).updateCoordinates();
		
		player.setShip(board, player.ships.get(0), 1);
		int[][] boardExample = new int[][] {{0,0,0},{0,1,0},{0,0,0}};
		assertTrue(player.board[1][1]==1);
		assertArrayEquals(player.board,boardExample);
	}
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
		// assertArrayEquals(expected, actual), but in 
		// this test assertArrayEquals(actual, expected).
		Player player = new MockPlayer();
		int[][] board = new int[][] {{0, 0, 0, 0},
			   						 {0, 1, 1, 0},
			   						 {0, 0, 0, 0}};
		Ship ship = new MockShip(2, 1, 1, Orientation.HORIZONTAL);
		List<Ship> ships = Arrays.asList(new Ship[] {ship});
		player.setOpponent(new MockPlayer(board, ships));  
		
		player.shoot(0, 0);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 0, 0, 0},
			   						           	            		{0, 1, 1, 0},
			   						           	            		{0, 0, 0, 0}});
		
		player.shoot(1, 1);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 0, 0, 0},
			   						           	            		{0, 3, 1, 0},
			   						           	            		{0, 0, 0, 0}});
		
		player.shoot(1, 2);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 2, 2, 2},
			   						           	            		{2, 4, 4, 2},
			   						           	            		{2, 2, 2, 2}});
		
	}
	@Test 
	public void positionShipsTest() {
		List<Ship> ships = Arrays.asList(new Ship[] {new Ship(2),
				                                     new Ship(2)});
		Coord[] coordinatesOfSteps = {new Coord(2, 0),
				                      new Coord(0, 0),
				                      new Coord(0, 1),
				                      new Coord(0, 2)};
	    Orientation[] orientationsOfSteps = {Orientation.VERTICAL,
	    		                             Orientation.VERTICAL,
	    		                             Orientation.VERTICAL,
	    		                             Orientation.VERTICAL};
		MockPlayer player = new MockPlayer(3, 3, ships, coordinatesOfSteps,
				                           orientationsOfSteps);
		player.positionShips();
		List<int[][]> boardsBackup = player.getBoardsBackup();
		assertArrayEquals(boardsBackup.get(1), new int[][] {{0, 0, 0},
														    {0, 0, 0},
	    													{0, 0, 0}});
		int[][] thirdAndFourBoard = new int[][] {{1, 0, 0},
										         {1, 0, 0},
										         {0, 0, 0}};
		assertArrayEquals(boardsBackup.get(2), thirdAndFourBoard);
		assertArrayEquals(boardsBackup.get(3), thirdAndFourBoard);
		assertArrayEquals(player.board, new int[][] {{1, 0, 1},
		                							 {1, 0, 1},
		                							 {0, 0, 0}});
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
