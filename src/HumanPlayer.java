/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0
 * 
 * Human Player is a Player who can set Bets and win/lose chips
 * while playing a round.	
 */


public class HumanPlayer extends Player
{
	private double blChips;
	private double currentBet;

	public double getChips()
	{
		return blChips;
	}
	
	public double getCurrentBet()
	{
		return currentBet;
	}
	
	public void setChips(double chips)
	{
		blChips = chips;
	}
	
	public void setCurrentBet(double currentBet)
	{
		this.currentBet = currentBet;
	}

}
