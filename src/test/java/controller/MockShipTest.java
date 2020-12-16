package controller;

public class MockShipTest extends Ship {
	public MockShipTest(int size) {
		super(size);
	}
	public MockShipTest(int i, int j) {
		setPosition(i, j);
	}
	public MockShipTest(int size, int i, int j, Orientation orientation) {
		super(size);
		setPosition(i, j);
		setOrientation(orientation);
	}
	public MockShipTest(int numTrue, boolean numF) { 
		
		for(int i = 0; i < numTrue;i++) {
			this.damagedZones[i] = true;
		}
		
		if (numF) {
			this.damagedZones[i] = false;
		}
	}
}