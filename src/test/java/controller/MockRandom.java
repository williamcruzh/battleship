package controller;

import java.util.Random;

@SuppressWarnings("serial")
public class MockRandom extends Random {
	private boolean firstPlayer;
	
	public MockRandom(int firstPlayer) {
		this.firstPlayer = (firstPlayer == 1);
	}
	@Override
	public boolean nextBoolean() {
		return firstPlayer;
	}
}
