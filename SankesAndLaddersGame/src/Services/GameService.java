package Services;

import Models.Board;
import Models.Snake;
import Models.Ladder;
import Models.Player;

import java.util.*;

public class GameService {
	private static final int DEFAULT_BOARD_SIZE = 100;
	private Board gameBoard;
	private Queue<Player> players;
	private Dice dice;
	private int intialNoOfPlayers;
	private int boardSize;
	
	public GameService() {
		// TODO Auto-generated constructor stub
		gameBoard = new Board(DEFAULT_BOARD_SIZE);
		intialNoOfPlayers = 0;
		dice = new Dice();
		boardSize = gameBoard.getSize();
	}
	
	/*
	 * ================Initialize Board=======================
	 */
	
	public void setSnakes(List<Snake> snakes) {
		gameBoard.setSnakes(snakes);
	}
	
	public void setLadders(List<Ladder> ladders) {
		gameBoard.setLadders(ladders);
	}
	
	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		intialNoOfPlayers = players.size();
		//playerPieces stores the current position of a player based on his id in a map
		Map<String, Integer> playerPieces = new HashMap<>();
		for(Player player : players) {
			//adds players to the queue
			this.players.add(player);
			playerPieces.put(player.getId(), 0);
		}
		gameBoard.setPlayerItems(playerPieces);
	}
	
	
	
	/*
	 * ==================core business logic===================
	 * */
	
	
	private int getPosAfterSankesAndLadders(int newPosition) {
		int oldPosition;
		
		do {
			oldPosition  = newPosition;
			
			//to check if the position is maybe at start of a snake position
			for(Snake snake : gameBoard.getSnakes()) {
				if(snake.getStartPos() == newPosition) {
					newPosition = snake.getEndPos();
				}
			}
			
			//to check if the position is maybe at start of a ladder position
			for(Ladder ladder : gameBoard.getLadders()) {
				if(ladder.getStartPos() == newPosition) {
					newPosition = ladder.getEndPos();
				}
			}
			
		}
		//if it's not equal that means that a snake or ladder was encountered
		while(newPosition != oldPosition);
		return newPosition;
	}
	
	//to move the player to the new position
	private void movePlayerToPos(Player currentPlayer, int diceRollCount) {
		int currentPos = gameBoard.getPlayerItems().get(currentPlayer.getId());
		int newPosition = currentPos + diceRollCount;
		
		if(newPosition > boardSize) {
			newPosition = currentPos;
		}
		else {
			newPosition = getPosAfterSankesAndLadders(newPosition);
		}
		System.out.println(currentPlayer.getName() + " rolled a " + diceRollCount + " and moved to " + newPosition);
		gameBoard.getPlayerItems().put(currentPlayer.getId(), newPosition);
	}
	
	//checks if a player has already reached the max count in the baord size or not
	private boolean isCompleted() {
		int currentNoOfPlayers = players.size();
		if(currentNoOfPlayers != intialNoOfPlayers)
			return true;
		return false;
	}
	
	//checks if a player has won or not
	private boolean hasWon(Player currentPlayer) {
		if(gameBoard.getPlayerItems().get(currentPlayer.getId()) == boardSize)
			return true;
		return false;
	}
	
	public void startGame() {
		while(!isCompleted()) {
			int diceRollCount = dice.rollDice();
			Player currentPlayer = players.poll();
			movePlayerToPos(currentPlayer, diceRollCount);
			
			if(hasWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " won the game!");
				gameBoard.getPlayerItems().remove(currentPlayer.getId());
			}
			else {
				//if nobody won then add him to the queue
				players.add(currentPlayer);
			}
		}
	}
}
