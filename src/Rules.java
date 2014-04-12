/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0	
 * 
 * Rules class is defined to define the rules at the table. 
 * Different Tables can have different rules. The rules currently
 * incorporates initial chips, minimum bet and number of decks for
 * playing cards. It can be extended to include other features like
 * insurance, et. al.
 */

public class Rules 
{
	private double initialChips;
	private double minimumBet;
	private int numberDecks;
	
	public Rules(int initialChips, int minimumBet, int numberDecks)
	{
		this.initialChips = initialChips;
		this.minimumBet = minimumBet;
		this.numberDecks = numberDecks;
	}
	
	public double getInitialChips()
	{
		return initialChips;
	}
	
	public double getMinimumBet()
	{
		return  minimumBet;
	}
	
	public void displayRules(String PlayerName)
	{
		System.out.println("--> This is a standard Blackjack Game. \n--> Dealer stands on all 17s. \n--> Wins are paid 1:1 chips, except for blackjacks, which are paid at 3:2 chips.");
		System.out.println("--> There's no insurance at this table. \n--> "+PlayerName+", the game features available to you are Hit and Stand. Too bad ? Get another table !");
		System.out.println("--> You'll have to earn our respect and some coins to get additional game features !");
		System.out.println("--> Lastly, you'll start with "+initialChips+" chips and the minimum bet is "+minimumBet+" chip(s) for each round.\n");
	}
	
	public int getNumberOfDecks()
	{
		return numberDecks;
	}
}
