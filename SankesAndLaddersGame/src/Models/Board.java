package Models;

import java.util.*;

public class Board {
	
	private int size;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Map<String, Integer> playerItems;
	
	public Board(int size){
		this.size = size;
		snakes = new ArrayList<Snake>();
		ladders = new ArrayList<Ladder>();
		playerItems = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getPlayerItems() {
		return playerItems;
	}

	public void setPlayerItems(Map<String, Integer> playerItems) {
		this.playerItems = playerItems;
	}

	public List<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public int getSize() {
		return size;
	}
	
	
}
