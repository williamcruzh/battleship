package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.*;

import org.junit.jupiter.api.Test;

import view.BattleshipConsole;
import view.Graphics;

public class TestAutomation {
	
	@Test
	public void automatedTest() {
		//Leer los datos de entrada (Barcos, tablero, disparos)
		int[][] board= initBoardFromFile("input.txt", "board",0);
		Ship[] ships = readShipsFromFile("input.txt", "ships",0);
		Coord[] shoots = getShootsFromFile("input.txt", "manyshoot",0);
		int countCases = 0;
		//Cargar barcos y tableros de los jugadores
		List<Ship> shipList = Arrays.asList(ships);
		Player player;
		player=new MockPlayerTest(board, shipList);
		player.setOpponent(new MockPlayerTest(board, shipList));
		
		//Disparar a los barcos del oponente
		for(int i = 0; i < shoots.length; i++) {
			Coord coordShoot = shoots[i];
			player.shoot(coordShoot.j, coordShoot.i);
		}
		
		String resultasGame = "lose;";
		//Partidas
		String [] nGames = getResultFromFile("input.txt", "nGames");
		for (int i = 0; i < Integer.parseInt(nGames[0]); i++) {
			System.out.print("Game:"+i+"\n");
			String res = "lose;";
			int[][] boardGame = initBoardFromFile("input.txt", "bGame",i);
			Ship [] shipsGame = readShipsFromFile("input.txt", "spGame",i);
			Coord[] shootsGame = getShootsFromFile("input.txt", "stGame",i);
			List<Ship> shipListGame = Arrays.asList(shipsGame);
			MockGameTest game = new MockGameTest(boardGame, shipListGame, 1);
			
			//Disparos
			for(int j = 0; j< shootsGame.length; j++) {
				Coord coordShoot = shootsGame[j];
				game.player1.shoot(coordShoot.j, coordShoot.i);
			}
			if(game.player1.isWinner()){
				res = "wine;";
			}
			resultasGame = resultasGame+res;
			System.out.print("RESULT:"+resultasGame+"\n");
		}
		
		//WriteResults
		writeResultsOutput(player, "output.txt", resultasGame);
		
		int coincidencesShips = 0;
		String text = "\nTest Ships Position Results:\nState 	  Actual	 Expected\n";
		String [] actualShips = getResultFromFile("input.txt", "resultShips");
		String [] expectedShips = getResultFromFile("output.txt", "resultShips");
		for(int i = 0; i< actualShips.length; i++) {
			if(expectedShips[i] != null && actualShips[i] != null) {
				//assertEquals(expectedShips[i], actualShips[i]);
				if(expectedShips[i].equals(actualShips[i])) {
					coincidencesShips +=1;
					text = text +"OK"+" "+actualShips[i]+ " == "+expectedShips[i]+"\n";
				}else {
					text = text +"FAIL"+" "+actualShips[i]+ " != "+expectedShips[i]+"\n";
				}
				countCases +=1;
			}
		}
		text = text +"Coincidences: "+coincidencesShips+" of "+ countCases +" | Percentage: "+coincidencesShips*100/actualShips.length+"%\n";
		text = text+"\nTest Shoots Results:\nState 	Actual 	 Expected\n";
		int coincidencesShoots = 0;
		countCases = 0;
		String [] actualShoots = getResultFromFile("input.txt", "resultManyShoots");
		String [] expectedShoots = getResultFromFile("output.txt", "resultManyShoots");
		for(int i = 0; i< actualShoots.length; i++) {
			if(expectedShoots[i] != null && actualShoots[i] != null) {
				//assertEquals(expectedShoots[i], actualShoots[i]);
				if(expectedShoots[i].equals(actualShoots[i])) {
					coincidencesShoots +=1;
					text = text +"OK"+" "+actualShoots[i]+ " == "+expectedShoots[i]+"\n";
				}else {
					text = text +"FAIL"+" "+actualShoots[i]+ " != "+expectedShoots[i]+"\n";
				}
				countCases +=1;
			}
		}
		text = text +"Coincidences: "+coincidencesShoots+" of "+ countCases +" | Percentage: "+coincidencesShoots*100/actualShoots.length+"%\n";
		text = text+"\nTest Games Results:\nGame State 	Actual 	 Expected\n";
		int coincidenceGames = 0;
		countCases = 0;
		int countGames = 0;
		String [] actualGamesResults = getResultFromFile("input.txt", "rtGame");
		String [] expectedGamesResults = getResultFromFile("output.txt", "rtGame");
		for(int i = 0; i< actualGamesResults.length; i++) {
			if(expectedGamesResults[i] != null && actualGamesResults[i] != null) {
				countGames +=1;
				if(expectedGamesResults[i].equals(actualGamesResults[i])) {
					coincidenceGames +=1;
					text = text +countGames+" OK"+" "+actualGamesResults[i]+ " == "+expectedGamesResults[i]+"\n";
				}else {
					text = text +countGames+" FAIL"+" "+actualGamesResults[i]+ " != "+expectedGamesResults[i]+"\n";
				}
				countCases +=1;
			}
		}
		text = text +"Coincidences: "+coincidenceGames+" of "+ countCases +" | Percentage: "+coincidenceGames*100/actualGamesResults.length+"%\n";
		writeTestResults(text, "test_results.txt");
		System.out.println(text);
		System.out.println("\nTest completed");
	}
	
	/*
	 * Leer barcos de un archivo de entrada.
	 * @Paramas: String input
	 * @Return: Ship[] ships
	 */
	public Ship[] readShipsFromFile(String input, String type, int nGame) {
		Ship[] ships = null;

		try {
		      FileReader fr = new FileReader(input);
		      BufferedReader br = new BufferedReader(fr);
		      String line;
		      
		      while((line = br.readLine()) != null) {
		      	if (line.contains(type)) {
		      		System.out.println(line);
		      		String[] parts = line.split(":");
					String initParts[] = parts[1].split(" ");
					if(initParts[nGame] != null && initParts[nGame] != "") {
						String[] shipsValue= initParts[nGame].split(";");
						ships = new Ship[shipsValue.length];
						for(int i = 0; i<shipsValue.length; i++) {
			      			String[] shipValue = shipsValue[i].split(",");
			      			int shipSize = Integer.parseInt(shipValue[0]);
			      			MockKeyBoard mockKey = new MockKeyBoard(shipValue[1]);
			      			Coord coord = mockKey.getCoordinates();
			      			Ship ship = new Ship(shipSize);
				      		ship.setOrientation(Orientation.VERTICAL);
				      		if(shipValue[2].contains("horizontal")) {
				      			ship.setOrientation(Orientation.HORIZONTAL);
				      		}
				      		//MoveShip
				      		ship.setPosition(coord.j, coord.i);
				      		ship.updateCoordinates();
				      		ships[i] = ship;
		      		}
		      		}
		      	}
		       //fr.close();
		    } 
		     
		}catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+  input+ ": " + e);
		}
		return ships;
	}
	
	/*
	 * Obtenir los resultados de los archivos.
	 * @Params: String file, String type
	 * @Return: String[] results
	 */
	public String[] getResultFromFile(String file, String type) {
		String[] result= null;
	    try {
		      FileReader fRInput = new FileReader(file);
		      BufferedReader bRInput = new BufferedReader(fRInput);
		      String lineInput;
		      
		      while((lineInput = bRInput.readLine()) != null) {
		    	  if(lineInput.contains(type)) {
		    		  String parts[] = lineInput.split(":");
		    		  result = parts[1].split(";");
		    	  }
		      }
		      fRInput.close();
		}catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+  file+ ": " + e);
		}
		return result;
	}
	
	/*
	 * Escribir resultados en un archivo de salida.
	 * @Params: String text, String file
	 */
	public void writeTestResults(String text, String file) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(file);
			myWriter.append(text);
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Método para escribir resultados en la salida.
	 * @Params: String output, Player player
	 */
	public void writeResultsOutput(Player player, String output, String resultsGames) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(output);
			String lineS = "";
			String lineShip = "";
			String lineShoot = "";
			String shipState = "";
			String resultGame = "";
			for(Ship ship: player.opposingPlayer.ships) {
				if(ship.isSunk()) {
					shipState ="sunk";
				}
				Coord[] coord = ship.getCoordinates();
				lineShip = "";
				for (Coord c : coord) {
					lineShip = lineShip +"("+c.i+","+c.j +")";
					if(ship.isHit(c.i, c.j) && !ship.isSunk()){
						shipState = "hit";
					}
				}
				lineS = lineS+lineShip+";";
				lineShoot = lineShoot+shipState+";";
			}
			
			myWriter.write("resultShips:"+lineS+"\n"+"resultManyShoots:"+lineShoot+"\nrtGame:"+resultsGames+"\n");
			myWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Método para leer el tablero del archivo e inicializar el tablero.
	 * @Params: String file
	 * @Return int[][] board
	 */
	public int[][] initBoardFromFile(String file, String type, int nGame) {
		int[][] board = null;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				if (line.contains(type)) {
					System.out.println(line);
					String parts[] = line.split(":");
					String initParts[] = parts[1].split(" ");
					if(initParts[nGame] != null && initParts[nGame] != "") {
						String [] Board = initParts[nGame].split(",");
						board = new int[Integer.parseInt(Board[0])][Integer.parseInt(Board[1])];
					}
				}
			}
			fr.close(); 
		}catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+  file+ ": " + e);
		}
		return board;
	}
	
	/*
	 * Método para leer los disparos del archivo
	 * @Params: String file, String type
	 * @Return Coord[] coordenadas.
	 */
	public Coord[] getShootsFromFile(String file, String type, int nGame) {
		Coord[] coord = null;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				if (line.contains(type)) {
					System.out.println(line);
					String parts[] = line.split(":");
					String initParts[] = parts[1].split(" ");
					if(initParts[nGame] != null && initParts[nGame] != "") {
						String [] shoots = initParts[nGame].split(";");
						coord = new Coord[shoots.length];
						for (int i=0; i< shoots.length; i++) {
							MockKeyBoard mockKey = new MockKeyBoard(shoots[i]);
			      			coord[i] = mockKey.getCoordinates();
						}
					}
				}
			}
			//fr.close(); 
		}catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+  file+ ": " + e);
		}
		return coord;
	}
}
