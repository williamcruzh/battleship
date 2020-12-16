package controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.Coord;
import controller.Orientation;
import controller.Player;
import controller.Ship;

public class TestCases {
	
	//Caso de test para posicionar barcos.
	@Test
	public void moveShipTest() {
		//Posicionar barco de 1 posición
		Ship ship1 = new Ship(2);
		MockKeyBoard mockKeyBoard = new MockKeyBoard("e2");
		Coord coord = mockKeyBoard.getCoordinates();
		ship1.setPosition(coord.j, coord.i);
		ship1.updateCoordinates();
		Coord coordActual1[] = new Coord[1];
		coordActual1[0] = new Coord(4,1);
		Coord coordExpected1[] = ship1.getCoordinates();
		assertEquals(coordActual1[0].i,coordExpected1[0].i);
		assertEquals(coordActual1[0].j,coordExpected1[0].j);
		//Posicionar barco de 2 posiciones
		Ship ship2 = new Ship(2);
		ship2.setPosition(1,1);
		ship2.updateCoordinates();
		Coord coordActual[] = new Coord[2];
		coordActual[0] = new Coord(1,1);
		coordActual[1] = new Coord(2,1);
		Coord coordExpected[] = ship2.getCoordinates();
		for(int i = 0; i<2;i++) {
			assertEquals(coordActual[i].i,coordExpected[i].i);
			assertEquals(coordActual[i].j,coordExpected[i].j);
		}
		//Posicionar barco de 3 posiciones
		Ship ship3 = new Ship(3);
		MockKeyBoard mockKeyBoard1 = new MockKeyBoard("d4");
		Coord coord1 = mockKeyBoard1.getCoordinates();
		ship3.setPosition(coord1.j,coord1.i);
		ship3.updateCoordinates();
		Coord coordActual3[] = new Coord[3];
		coordActual3[0] = new Coord(3,3);
		coordActual3[1] = new Coord(4,3);
		coordActual3[2] = new Coord(5,3);
		Coord coordExpected3[] = ship3.getCoordinates();
		for(int i = 0; i<2;i++) {
			assertEquals(coordActual3[i].i,coordExpected3[i].i);
			assertEquals(coordActual3[i].j,coordExpected3[i].j);
		}
		//Posicionar barco de 4posiciones
		Ship ship4 = new Ship(3);
		MockKeyBoard mockKeyBoard2 = new MockKeyBoard("d4");
		Coord coord2 = mockKeyBoard2.getCoordinates();
		ship3.setPosition(coord2.j,coord2.i);
		ship4.setPosition(1,2);
		ship4.updateCoordinates();
		Coord coordActual4[] = new Coord[4];
		coordActual4[0] = new Coord(1,2);
		coordActual4[1] = new Coord(2,2);
		coordActual4[2] = new Coord(3,2);
		coordActual4[3] = new Coord(4,2);
		Coord coordExpected4[] = ship4.getCoordinates();
		for(int i = 0; i<3;i++) {
			assertEquals(coordActual4[i].i,coordExpected4[i].i);
			assertEquals(coordActual4[i].j,coordExpected4[i].j);
		}
	}
	
	//Caso de test para cambiar orientación del barco
	@Test
	public void shipOrientationTest() {
		//Vertical
		Ship ship = new Ship(2);
		ship.setPosition(0,1);
		ship.setOrientation(Orientation.VERTICAL);
		assertEquals(ship.orientation, Orientation.VERTICAL);
				
		//Horizontal
		ship.setOrientation(Orientation.HORIZONTAL);
		assertEquals(ship.orientation, Orientation.HORIZONTAL);
		
		//Vertical Barco de 4
		Ship ship1 = new Ship(4);
		ship1.setPosition(3,3);
		ship1.setOrientation(Orientation.VERTICAL);
		assertEquals(ship1.orientation, Orientation.VERTICAL);
		
		//Horizontal Barco de 4
		ship1.setOrientation(Orientation.HORIZONTAL);
		assertEquals(ship1.orientation, Orientation.HORIZONTAL);
	}
	
	//Caso de test para disparar a una posición
	@Test
	public void shootTest() {
		int[][] board = new int[][] {{0, 0, 0, 0},
				 					{0, 1, 1, 0},
				 					{0, 0, 0, 0}};
		Ship ship = new MockShipTest(2, 1, 0, Orientation.HORIZONTAL);
		List<Ship> ships = Arrays.asList(new Ship[] {ship});
		
		Player player = new MockPlayerTest(board, ships);
		Ship ship1 = new MockShipTest(2, 1, 1, Orientation.HORIZONTAL);
		List<Ship> ships1 = Arrays.asList(new Ship[] {ship1});
		player.setOpponent(new MockPlayerTest(board, ships1));  
		
		MockKeyBoard mockKeyBoard = new MockKeyBoard("a1");
		Coord coordShoot = mockKeyBoard.getCoordinates();
		player.shoot(0, 0);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 0, 0, 0},
			   						           	            		{0, 1, 1, 0},
			   						           	            		{0, 0, 0, 0}});
		MockKeyBoard mockKeyBoard1 = new MockKeyBoard("b2");
		Coord coordShoot1 = mockKeyBoard1.getCoordinates();
		player.shoot(1, 1);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 0, 0, 0},
			   						           	            		{0, 3, 1, 0},
			   						           	            		{0, 0, 0, 0}});
		MockKeyBoard mockKeyBoard2 = new MockKeyBoard("b3");
		Coord coordShoot2 = mockKeyBoard2.getCoordinates();
		player.shoot(1, 2);
		assertArrayEquals(player.opposingPlayer.board, new int[][] {{2, 2, 2, 2},
			   						           	            		{2, 4, 4, 2},
			   						           	            		{2, 2, 2, 2}});		
	}
}
