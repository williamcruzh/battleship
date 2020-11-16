package view;

import java.io.Console;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Scanner;

import controller.Coord;
import controller.Orientation;

public class BattleshipConsole implements Graphics {
	/*private*/ Scanner scanner;
	/*private*/ final CharsetEncoder charsetEncoder;
	/*private*/ final String letters;
	/*private*/ final String digits;
	
	public BattleshipConsole(int height, int width) throws Exception {
		if(height > 26 || width > 26) {
			throw new Exception("Interface only supports boards smaller than 26x26");
		}
		charsetEncoder = Charset.forName("US-ASCII").newEncoder();
		letters = "abcdefghijklmnopqrstuvwxyz";
		digits = "0123456789";
		
		scanner = new Scanner(System.in);
		System.out.println("Welcome to battleship");
		System.out.println("Coordinates format examples: A1, Z26.");
	}
	/*private*/ Coord getCoordinates() {
		while (true) {
			System.out.println("Enter coordinate: ");
			String line = scanner.nextLine();
			if(!charsetEncoder.canEncode(line)) {
				
			}
			else if(line.length() == 2) {
				int j;
				if((j = letters.indexOf(line.charAt(0))) != -1 && 
					digits.contains(line.substring(1))) {
					int i = Integer.parseInt(line.substring(1)) - 1;
					if(i < 26) {
						return new Coord(i, j);
					}
				}
			}
			else if(line.length() == 3) {
				int j;
				if((j = letters.indexOf(line.charAt(0))) != -1 && 
					digits.contains(line.substring(1, 2)) && 
					digits.contains(line.substring(2))) {
					int i = Integer.parseInt(line.substring(1, 3)) - 1;
					if(i < 26) {
						return new Coord(i, j);
					}
				}
			}
			System.out.println("Invalid coordinate format");
			System.out.println("Remember, interface only supports boards smaller than 26x26");
		}
	}
	public void drawOutOfBoard() {
		System.out.println("Coordinates selected are out of board");
	}
	public void drawSquareAlreadySelected() {
		System.out.println("Square already selected");
	}
	private void drawBoard(int[][] board, boolean cleanWater, boolean cleanShipZone, 
			               boolean shootedWater, boolean shootedShipZone,
			               boolean sunkedShipZone) {
		int m = board.length;
		int n = board[0].length;
		String padding = "  ";
		
		System.out.print(padding);
		System.out.print("   ");
		// Letter row
		for(int j = 0; j < n; j++) {
			System.out.print(letters.charAt(j) + " ");
		}
		System.out.println();
		// Board
		for(int i = 0; i < m; i++) {
			System.out.print(padding);
			String number = Integer.toString(i + 1);
			if(number.length() == 1) {
				System.out.print(" " + number);
			}
			else {
				System.out.print(number);
			}
			System.out.print(" ");
			for(int j = 0; j < n; j++) {
				if(cleanWater && board[i][j] == 0) {
					System.out.print(" ");
				}
				else if(cleanShipZone && board[i][j] == 1) {
					System.out.print("o");
				}
				else if(shootedWater && board[i][j] == 2) {
					System.out.print("·");
				}
				else if(shootedShipZone && board[i][j] == 3) {
					System.out.print("x");
				}
				else if(sunkedShipZone && board[i][j] == 4) {
					System.out.print("#");
				}
				else {
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public void drawPlayerBoards(int[][] board, int[][] opposingBoard) {
		drawBoard(board, true, true, true, true, true);
		drawBoard(opposingBoard, true, false, true, true, true);
	}
	public void drawCreationBoardsScreen(int[][] board) {
		drawBoard(board, true, true, false, false, false);
	}
	public void drawLose() {
		System.out.println("You lose");
	}
	public void drawWin() {
		System.out.println("You Win");
	}
	public void drawTie() {
		System.out.println("There is Tie");
	}
	public Coord drawMoveShip(int shipSize) {
		System.out.println("Move a ship of " + shipSize + " squares");
		return getCoordinates();
	}
	public Orientation drawRotateShip() {
		Orientation orientation = Orientation.VERTICAL;
		System.out.println("Rotate a ship");
		String line; boolean isNo = false;
		do {
			if(orientation == Orientation.VERTICAL) {
				System.out.println("Ship orientation: " + "o" + System.lineSeparator() + 
			                        "                  o");
			}
			else {
				System.out.println("Ship orientation: " + "oo");
			}
			System.out.println("Do you want to rotate the ship? [y or n]");
			line = scanner.nextLine();
			if(line.equals("y")) {
				if(orientation == Orientation.VERTICAL) {
					orientation = Orientation.HORIZONTAL;
				}
				else {
					orientation = Orientation.VERTICAL;
				}
			} 
			else if(line.equals("n")) {
				isNo = true;
			}
			else {
				System.out.println("Invalid format");
			}
		} while (!isNo);
		return orientation;
	}
	public Coord drawShoot() {
		System.out.println("Shoot");
		return getCoordinates();
	}
}
