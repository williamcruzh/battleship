 package controller;

import java.util.List;
import java.util.Vector;

public class Ship {
	/*private*/ int i;
	/*private*/ int j;
	/*private*/ int size;
	/*private*/ Orientation orientation;
	/*private*/ boolean[] damagedZones;
	/*private*/ boolean sunk;
	/*private*/ Coord[] coordinates;
	
	public Ship(int size) {
		this.size = size;
		i = 0; j = 0;
		orientation = Orientation.HORIZONTAL;
		damagedZones = new boolean[size];
		sunk = false;
		coordinates = new Coord[size + 1];
	}
	public Coord[] getCoordinates() {
		if(orientation == Orientation.VERTICAL) {
			int i;
			for(int k = 0; k < size; ++k) {
				i = this.i + k;
				coordinates[k] = new Coord(i, this.j);
			}
		}
		else {
			int j;
			for(int k = 0; k < size; ++k) {
				j = this.j + k;
				coordinates[k] = new Coord(this.i, j);
			}
		}
		return coordinates;
	}
	public void setOrientation(Orientation orientation) {
		// Needs some checks
		this.orientation = orientation;
	}
	public void setPosition(int i, int j) {
		this.i = i; this.j = j;
	}
	public boolean isSunk() {
		for(boolean damagedZone: damagedZones) {
			if(!damagedZone) {
				return false;
			}
		}
		return true;
	}
}
