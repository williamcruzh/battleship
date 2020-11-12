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
	public void testIsWinner() {
		
		int[][] board;
		MockPlayer player;
		List<Ship> ShipList = null;
		
		//case n=0
		player = new MockPlayer();
		board = new int[][] {{0}};
		ShipList = null;
		player.setOpponent(new MockPlayer(board,ShipList));
		assertFalse(player.IsWinner());	
		
		//case n = 1 true
		player = new MockPlayer();
		board = new int[][] {{0}};
		ShipList.add(new MockShip(3, false));
		player.setOpponent(new MockPlayer(board,ShipList));
		assertTrue(player.IsWinner());
		
		//case n = 1 false
		ShipList.clear();
		player = new MockPlayer();
		board = new int[][] {{0}};
		ShipList.add(new MockShip(2, true));
		player.setOpponent(new MockPlayer(board,ShipList));
		assertFalse(player.IsWinner());
		
		//case n = 2 true
		ShipList.clear();
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board,ShipList));
		player.makeShipList(2,false);
		assertTrue(player.IsWinner());
		
		//case n = 2 false
		ShipList.clear();
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board,ShipList));
		player.makeShipList(1,true);
		assertFalse(player.IsWinner());
		
		//case n = 4 true
		ShipList.clear();
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board,ShipList));
		player.makeShipList(4,false);
		assertTrue(player.IsWinner());
		
		//case n = 4 false
		ShipList.clear();
		player = new MockPlayer();
		board = new int[][] {{0}};
		player.setOpponent(new MockPlayer(board,ShipList));
		player.makeShipList(3,true);
		assertFalse(player.IsWinner());
		
	}
}
