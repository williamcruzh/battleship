package controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import view.MockGraphics;

public class GameTest {
	@Test
	public void GameTest() throws Exception {
		// In methods with no assertThrows we test if an Exception
		// is raised.
		Game methodGame;
		
		// Equivalent partition 1
		Main.graphics = null;
		assertThrows(Exception.class, () -> {Game game = 
				new Game(Integer.MIN_VALUE, Integer.MIN_VALUE, new int[] {5});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(-150, -150, new int[] {75});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(0, 0, new int[] {5});});
		// Limit value
		assertThrows(Exception.class, () -> {Game game = 
				new Game(1, 1, new int[] {5});});
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		// Equivalent partition 2
		// Border value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(2, 2, new int[] {1});
		// Limit value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(3, 3, new int[] {1});
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(50, 50, new int[] {25});
		// Limit value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(999, 999, new int[] {499});
		// Border value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(1000, 1000, new int[] {500});
		// Equivalent partition 3
		// Limit value
		Main.graphics = null;
		assertThrows(Exception.class, () -> {Game game = 
				new Game(1001, 1001, new int[] {501});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(4500, 4500, new int[] {2250});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(Integer.MAX_VALUE, Integer.MAX_VALUE, new int[] {5});});
		
		// Equivalent partition 1
		assertThrows(Exception.class, () -> {Game game = new Game(10, 10, new int[] {});});
		// Equivalent partition 2
		assertThrows(Exception.class, () -> {Game game = 
				new Game(10, 10, new int[] {Integer.MIN_VALUE});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(10, 10, new int[] {-100});});
		// Limit value
		assertThrows(Exception.class, () -> {Game game = 
				new Game(10, 10, new int[] {0});});
		// Equivalent partition 3
		// Border value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(10, 10, new int[] {1});
		// Limit value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(10, 10, new int[] {2});
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(10, 10, new int[] {5});
		// Limit value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(10, 10, new int[] {9});
		// Border value
		Main.graphics = new MockGraphics(new Coord[]{new Coord(0, 0)}, new Integer[]{0});
		methodGame = new Game(10, 10, new int[] {10});
		//Partition 4
		// Limit value
		Main.graphics = null;
		assertThrows(Exception.class, () -> {Game game = 
					  new Game(10, 10, new int[] {11});});
		assertThrows(Exception.class, () -> {Game game = 
				  new Game(10, 10, new int[] {50});});
		assertThrows(Exception.class, () -> {Game game = 
				  new Game(10, 10, new int[] {Integer.MAX_VALUE});});
	}
	@Test
	public void playTest() {
		List<Integer> actual = new Vector();
		List<Integer> expected;
		Game game;
		
		expected = List.of(0, 1, 0, 1, 0);
		actual.clear();
		game = new MockGame(actual, 0, 0, 5, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(0, 1, 0, 1, 0);
		actual.clear();
		game = new MockGame(actual, 0, 1, 5, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(0, 1, 0, 1, 0);
		actual.clear();
		game = new MockGame(actual, 0, 0, 5, 1);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(1, 0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 1, 0, 5, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(1, 0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 1, 1, 5, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(1, 0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 1, 0, 5, 1);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 0, 0, 4, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 0, 1, 4, 0);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
		expected = List.of(0, 1, 0, 1);
		actual.clear();
		game = new MockGame(actual, 0, 0, 4, 1);
		game.play();
		// Only equals, if they are in the same order.
		assertEquals(expected, actual);
		
	}
}
