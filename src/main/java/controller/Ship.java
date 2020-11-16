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
	
	public Ship() {
		
	}
	public Ship(int size) {
		this.size = size;
		i = 0; j = 0;
		orientation = Orientation.VERTICAL;
		damagedZones = new boolean[size]; // all false in default
		sunk = false;
		coordinates = new Coord[size];
	}
	public boolean fitsInBoard(final int m, final int n) {
		if(i < 0 || i >= m || j < 0 || j >= n) {
			return false;
		}
		if(orientation == Orientation.VERTICAL) {
			if(i + size <= m) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(j + size <= n) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public Coord[] getCoordinates() {
		return coordinates;
	}
	public int getSize() {
		return size;
	}
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	public void setPosition(int i, int j) {
		this.i = i;
		this.j = j;
	}
	public boolean isHit(int i, int j) {
		if(orientation == Orientation.VERTICAL) {
			if(this.i <= i && i < this.i + this.size && this.j == j) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(this.j <= j && j < this.j + this.size && this.i == i) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public boolean isSunk() {
		return sunk;
	}
	public void shoot(int i, int j) {
		int k;
		if(orientation == Orientation.VERTICAL) {
			k = i - this.i;
		}
		else {
			k = j - this.j;
		}
		damagedZones[k] = true;
		boolean sunk = true;
		for(boolean damagedZone: damagedZones) {
			if(!damagedZone) {
				sunk = false;
				break;
			}
		}
		if(sunk) {
			this.sunk = true;
		}
	}
	}
}
