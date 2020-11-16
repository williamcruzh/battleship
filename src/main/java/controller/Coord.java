package controller;

import java.util.Objects;

public class Coord {
	public int i;
	public int j;
	
	public Coord() {
		this.i = 0; 
		this.j = 0;
	}
	public Coord(int i, int j) {
		this.i = i; 
		this.j = j;
	}
	/*
	 * TODO: Make it more efficient with a dict which has 
	 *       Coord as key, and it has ship pointer as value.
	 *       Only for coords where there is a ship.
	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}
	*/
}
