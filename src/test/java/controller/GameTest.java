package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.Test;

public class GameTest {
	@Test
	public void playTest() {
		List<Boolean> expected = List.of(true, false, true);
		List<Boolean> actual = new Vector();
		Game game = new MockGame(expected, actual);
		game.play();
	}
}
