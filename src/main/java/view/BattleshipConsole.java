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
		charsetEncoder = Charset.forName("US-ASCII").newEncoder();
		letters = "abcdefghijklmnopqrstuvwxyz";
		digits = "0123456789";
		
		scanner = new Scanner(System.in);
	}
	public void drawLose() {
		System.out.println("There have been a losing person");
	}
	public void drawWin() {
		System.out.println("There have been a winnig person");
	}
	public void drawTie() {
		System.out.println("a tie");
	}
	public Orientation drawRotateShip() {
		Orientation orientation = Orientation.VERTICAL;
		String line; boolean isNo = false;
		do {
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
				System.out.println("Invalid format.");
			}
		} while (!isNo);
		return orientation;
	}
}
