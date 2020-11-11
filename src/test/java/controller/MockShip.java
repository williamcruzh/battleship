package controller;
import java.util.List;

public class MockShip extends Ship {
	public MockShip(int size) {
		super(size);
	}
	public MockShip(int numTrue, boolean numF) { 
		
		for(int i = 0; i < numTrue;i++) {
			this.zone.add(true);
		}
		
		if (numF) {
			this.zone.add(false);
		}
	}
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	public int getSize() {
		return size;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public boolean[] getDamagedZones() {
		return damagedZones;
	}
	public boolean getSunkAttribute() {
		return sunk;
	}
}
