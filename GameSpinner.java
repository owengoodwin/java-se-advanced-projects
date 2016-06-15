package finalReview;

import java.util.Random;

/*
 * Write a complete thread class called GameSpinner.
    The class takes in two parameters: the number of sections on the spinner and the number of spins.
    When the thread is run, it randomly selects a space on the spinner the specified number of times. (It can just print the output.)
    Write a program to create and start multiple spinner threads.
 */

public class GameSpinner implements Runnable {
	
	private Integer numberOfSections, numberOfSpins;
	private static Random generator;
	
	GameSpinner(Integer numberOfSections, Integer numberOfSpins){
		this.numberOfSections = numberOfSections;
		this.numberOfSpins = numberOfSpins;
		generator = new Random();
	}

	@Override
	public void run() {
		spinAll();
	}
	
	private void spinAll(){
		for(int i = 0; i < numberOfSpins; i++){
			int spin = generator.nextInt(numberOfSections);
			System.out.println("spin " + (i + 1) + " is " + spin);
		}
	}
	
	public static void main(String[] args) {
		generator = new Random();
		int numberOfGames = generator.nextInt(10 - 1) + 1;
		System.out.println("NUMBER OF GAMES: " + numberOfGames);
		
		for (int i = 0; i < numberOfGames; i++) {
			System.out.print("GAME: " + (i + 1));
			
			int numOfSections = generator.nextInt(6 - 1) + 1;
			int numOfSpins = generator.nextInt(8 - 1) + 1;
			System.out.print(" | NUMBER OF SECTIONS: " + numOfSections);
			System.out.println(" | NUMBER OF SPINS: " + numOfSpins);
			
			GameSpinner gameSpinner = new GameSpinner(numOfSections, numOfSpins);
			Thread gameSpinnerThread = new Thread(gameSpinner);
			gameSpinnerThread.start();
		}
	}

}
