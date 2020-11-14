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
	public void GameTest() {
		Main.graphics = null;
		Game game,
		
		assertThrows(Exception.class, () -> {Game game = 
				new Game(Integer.MIN_VALUE, Integer.MIN_VALUE, new int[] {5});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(-150, -150, new int[] {5});});
		assertThrows(Exception.class, () -> {Game game = 
				new Game(0, 0, new int[] {5});});
		// Limit value
		assertThrows(Exception.class, () -> {Game game = 
				new Game(1, 1, new int[] {5});});
		// Border value
		game = new Game(2, 2, new int[] {1});});
		Main.graphics = new MockGraphics(new Coord[]{new Coord(1, 1)}, new Integer[]{0});
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
