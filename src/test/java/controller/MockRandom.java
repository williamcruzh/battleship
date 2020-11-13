package controller;

import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class MockRandom extends Random {
	private List<Boolean> turns;
	private int i;
	
	public MockRandom(List<Boolean> turns) {
		this.turns = turns;
		i = 0;
	}
	@Override
	public boolean nextBoolean() {
		boolean turn = turns.get(i);
		i++;
		return turn;
	}
}
