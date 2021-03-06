/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0	
 */

/*
 * Objective: Insight Data Engineering Fellows Program - Coding Challenge
 * We'd like you to implement a text-based Blackjack program in one of the
 * following programming languages: Java, Clojure, Scala, C, or C++.
 * There should be one player and one dealer. The dealer should hit until
 * his hand value is 17 or greater. You should implement the basic actions
 * of hitting and standing. Implementing the more advanced actions such as
 * splitting is optional. The player should start with 100 chips and must
 * bet at least 1 chip each hand. Any additional game play features are
 * optional, but welcome.
 */

public class Game 
{
	public static void main(String[] args) throws InterruptedException 
	{
		HumanPlayer blHumanPlayer = new HumanPlayer();
		Dealer blDealer = new Dealer();
		Play blGamePlay = new Play(blHumanPlayer, blDealer);
		blGamePlay.startGame();
	}
}
