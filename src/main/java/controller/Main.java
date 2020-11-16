package controller;

import view.BattleshipConsole;
import view.Graphics;

public class Main {
	static Graphics graphics;
	
	public static void main(String[] args) throws Exception {
		int[] ships = new int[] {4, 3, 3, 3, 2, 2, 2, 1, 1};
		Main.graphics = new BattleshipConsole(10, 10);
		Game game = new Game(10, 10, ships);
		game.play();
	}
}
