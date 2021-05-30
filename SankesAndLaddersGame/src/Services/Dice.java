package Services;

import java.util.Random;

public class Dice {
	
	private static int DefaultNoOfDices = 1;
	
	public int rollDice() {
		return rollDice(DefaultNoOfDices);
	}
	public int rollDice(int noOfDices) {
		return new Random().nextInt(6 * noOfDices) + 1;
	}
}
