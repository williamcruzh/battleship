package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
	protected final int MAX_BOARD_HEIGHT = 1000;
	protected final int MAX_BOARD_WIDTH = 1000;
	protected Player player1;
	protected Player player2;
	protected Random random;
	
	public Game() {
		
	}
	public Game(int height, int width, int[] shipsSizes) throws Exception {
		if(height < 2 || width < 2 || height > MAX_BOARD_HEIGHT ||
		   width > MAX_BOARD_WIDTH ) {
			throw new Exception("The program doesn't support a board of this size");
		}
		Ship[] ships = new Ship[shipsSizes.length];
		if(shipsSizes.length == 0) {
			throw new Exception("Ship size inconsitent with board size");
		}
		for(int i = 0; i < shipsSizes.length; i++) {
			if(shipsSizes[i] < 1 || shipsSizes[i] > height || shipsSizes[i] > width) {
				throw new Exception("Ship size inconsitent with board size");
			}
			ships[i] = new Ship(shipsSizes[i]);
		}
	}
	public void play() {
		boolean lastPlayer = random.nextBoolean();
		while(true) {
			if(!lastPlayer) {
				player1.play();
				if(player1.isWinner()) {
					player1.drawIsWinner();
					player2.drawLoses();
				    break;
				}
				else if(player2.isWinner()) {
				   player1.drawLoses();
				   player2.drawIsWinner();
				   break;
				}
				else if(player1.tie()) {
				   player1.drawTies();
				   player2.drawTies();
				   break;
				}
				player2.play();
			}
			else {
				player2.play();
				if(player1.isWinner()) {
					player1.drawIsWinner();
					player2.drawLoses();
				    break;
				}
				else if(player2.isWinner()) {
				   player1.drawLoses();
				   player2.drawIsWinner();
				   break;
				}
				else if(player1.tie()) {
				   player1.drawTies();
				   player2.drawTies();
				   break;
				}
				player1.play();
			}
		}
	}
}
