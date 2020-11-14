package controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class GameTest {
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
