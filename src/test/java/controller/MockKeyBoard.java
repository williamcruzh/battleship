package controller;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class MockKeyBoard {
	String inputText="";
	final CharsetEncoder charsetEncoder;
	final String letters;
	final String digits;
	
	public MockKeyBoard(String newText) {
		this.inputText = newText;
		charsetEncoder = Charset.forName("US-ASCII").newEncoder();
		letters = "abcdefghijklmnopqrstuvwxyz";
		digits = "0123456789";
	}
	
	/*
	 * Este método lo utilizan los desarooladores para pasar un caracter a un número.
	 * "No hacia falta"
	 */
	Coord getCoordinates() {
		Coord coord = null;
		if(!charsetEncoder.canEncode(this.inputText)) {	
		}
		else if(this.inputText.length() == 2) {
			int j;
			if((j = letters.indexOf(this.inputText.charAt(0))) != -1 && 
				digits.contains(this.inputText.substring(1))) {
				int i = Integer.parseInt(this.inputText.substring(1)) - 1;
				if(i < 26) {
					coord = new Coord(i, j);
				}
			}
		}
		else if(this.inputText.length() == 3) {
			int j;
			if((j = letters.indexOf(this.inputText.charAt(0))) != -1 && 
				digits.contains(this.inputText.substring(1, 2)) && 
				digits.contains(this.inputText.substring(2))) {
				int i = Integer.parseInt(this.inputText.substring(1, 3)) - 1;
				if(i < 26) {
					coord = new Coord(i, j);
				}
			}
		}
		return coord;
	}
}
