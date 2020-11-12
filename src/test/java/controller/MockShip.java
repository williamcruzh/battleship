package controller;
import java.util.List;

import sun.tools.tree.OrExpression;

public class MockShip extends Ship {
	public MockShip(int size) {
		super(size);
	}
	public MockShip(int size, int i, int j, Orientation orientation) {
		super(size);
		setPosition(i, j);
		setOrientation(orientation);
	}
	public MockShip(int numTrue, boolean numF) { 
		
		for(int i = 0; i < numTrue;i++) {
			this.zone.add(true);
		}
		
		if (numF) {
			this.zone.add(false);
		}
	}
}
