package Drivers;

import java.util.*;


import Models.Snake;
import Services.GameService;
import Models.Ladder;
import Models.Player;

public class Driver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("No. of snakes: ");
		int noOfSnakes = scanner.nextInt();
		List<Snake> snakes = new ArrayList<Snake>();
		for(int i = 0; i < noOfSnakes; i++) {
			System.out.print("Start and end pos for snake " + (i + 1) + " : ");
			snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
		}
		
		System.out.print("No. of ladders: ");
		int noOfLadders = scanner.nextInt();
		List<Ladder> ladders = new ArrayList<Ladder>();
		for(int i = 0; i < noOfLadders; i++) {
			System.out.print("Start and end pos for ladder " + (i + 1) + " : ");
			ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
		}
		
		System.out.print("No. of Players: ");
		int noOfPlayers = scanner.nextInt();
		List<Player> players = new ArrayList<Player>();
		for(int i = 0; i < noOfPlayers; i++) {
			System.out.print("Enter name for player " + (i + 1) + " : ");
			players.add(new Player(scanner.next()));
		}
		
		GameService game = new GameService();
		game.setSnakes(snakes);
		game.setLadders(ladders);
		game.setPlayers(players);
		
		game.startGame();
	}
}