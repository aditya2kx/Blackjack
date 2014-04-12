/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0
 * 
 * Play controls the game play between the human players and the dealer.
 * It manages each round of play and wining/losing of chips for the 
 * human players.	
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class Play 
{
	private HumanPlayer blHumanPlayer;
	private Dealer blDealer;
	private Rules rules;
	
	public Play(HumanPlayer blHumanPlayer, Dealer blDealer) throws InterruptedException
	{
		this.blHumanPlayer = blHumanPlayer;
		this.blDealer = blDealer;
		rules = new Rules(100, 1, 1);

		String blPlayerInputChoice = "";
		System.out.println("--------------------Welcome to the Blackjack game !!-------------------------\n");
		Thread.sleep(1000);
		System.out.println("Anytime you want to exit the game - type 'exit'\n");
		Thread.sleep(1000);
		System.out.print("What's your name, Player ?");
		blPlayerInputChoice = getPlayerChoice("");
		blHumanPlayer.setPlayerName(blPlayerInputChoice);
		System.out.println(blHumanPlayer.getPlayerName()+", great to see you here !\n");
		Thread.sleep(1000);
		System.out.println("Let me explain the rules at this table.\n");
		Thread.sleep(1000);
		rules.displayRules(blHumanPlayer.getPlayerName());
		System.out.print("That's it, champ ! You game ?(yes/no)");
		blPlayerInputChoice = getPlayerChoice("no");
		System.out.println("Great ! So let the games begin ! And may the odds be ever in your favour !!!\n");
		blHumanPlayer.setChips(rules.getInitialChips());
		blDealer.collectCards(rules.getNumberOfDecks());
		blDealer.shuffleCards();
	}

	public String getPlayerChoice(String negativeChoice)
	{
		BufferedReader blPlayerInputReader = null;
		String blInputChoice = "";
		try
		{
			blPlayerInputReader = new BufferedReader(new InputStreamReader(System.in));
			blInputChoice = blPlayerInputReader.readLine();
			System.out.println("");
			if(blInputChoice.equalsIgnoreCase("exit") || blInputChoice.equalsIgnoreCase(negativeChoice))
			{
				System.out.println("Bye, Player!\n");
				System.exit(0);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return blInputChoice;
	}
	
	public void setBets()
	{
		double currentBet = 0;
		System.out.println(blHumanPlayer.getPlayerName()+" you currently have "+blHumanPlayer.getChips()+" chip(s).");
		System.out.println("Please place your bet for the round. Note that the minimum bet is "+rules.getMinimumBet()+" chip(s).");
		try
		{
			currentBet = Double.parseDouble(getPlayerChoice(""));
		}
		catch(NumberFormatException e)
		{
			setBets();
		}
		if(currentBet < rules.getMinimumBet() || currentBet > blHumanPlayer.getChips())
		{
			setBets();
		}
		blHumanPlayer.setCurrentBet(currentBet);
		blHumanPlayer.setChips(blHumanPlayer.getChips() - blHumanPlayer.getCurrentBet());
	}
	
	public void dealInitialCards()
	{
		blHumanPlayer.addCard(blDealer.dealCard());
		blDealer.addCard(blDealer.dealCard());
		blHumanPlayer.addCard(blDealer.dealCard());
		blDealer.addCard(blDealer.dealCard());
		
		System.out.println("-------------------Dealer's Cards--------------------");
		System.out.println(blDealer.getMyCard(0));
		System.out.println("Card Face Down");
		
		System.out.println("\n-------------------"+blHumanPlayer.getPlayerName()+"'s Cards--------------------");
		blHumanPlayer.displayCards();
	}
	
	public void startGame() throws InterruptedException
	{
		while(blHumanPlayer.getChips() >= rules.getMinimumBet())
		{
			startRound();
			if(blHumanPlayer.busted())
			{
				System.out.println("You lost "+blHumanPlayer.getCurrentBet()+" chips.");
			}
			else
			{
				int playerScore = Collections.max(blHumanPlayer.getScore());
				System.out.println("-------------------Dealer's Cards--------------------");
				blDealer.displayCards();
				Thread.sleep(1000);
				while(!blDealer.checkGreaterThanScore(17))
				{
					blDealer.addCard(blDealer.dealCard());
					System.out.println("-------------------Dealer's Cards--------------------");
					blDealer.displayCards();
					blDealer.calcScores();
					Thread.sleep(1000);
				}
				
				if(blDealer.busted())
				{
					System.out.println("Dealer is busted !!");
					if(blHumanPlayer.checkBlackjack())
					{
						System.out.println("You won "+blHumanPlayer.getCurrentBet()*1.5+" chips.");
						blHumanPlayer.setChips(blHumanPlayer.getChips() + blHumanPlayer.getCurrentBet() + blHumanPlayer.getCurrentBet()*1.5);
					}
					else
					{
						System.out.println("You won "+blHumanPlayer.getCurrentBet()+" chips.");
						blHumanPlayer.setChips(blHumanPlayer.getChips() + 2*blHumanPlayer.getCurrentBet());
					}
				}
				else
				{
					int dealerScore = Collections.max(blDealer.getScore());
					System.out.println(blHumanPlayer.getPlayerName()+",your score is "+playerScore);
					System.out.println("Dealer's score is "+dealerScore);
					if(playerScore == dealerScore)
					{
						if(blHumanPlayer.checkBlackjack() && !blDealer.checkBlackjack())
						{
							System.out.println("You won "+blHumanPlayer.getCurrentBet()*1.5+" chips.");
							blHumanPlayer.setChips(blHumanPlayer.getChips() + blHumanPlayer.getCurrentBet() + blHumanPlayer.getCurrentBet()*1.5);
						}
						else if(!blHumanPlayer.checkBlackjack() && blDealer.checkBlackjack())
						{
							System.out.println("You lost "+blHumanPlayer.getCurrentBet()+" chips.");
						}
						else	
						{
							System.out.println("Equal Scores. It's a Push. You get back your bet chips.");
							blHumanPlayer.setChips(blHumanPlayer.getChips() + blHumanPlayer.getCurrentBet());
						}
					}
					else if(playerScore > dealerScore)
					{
						System.out.println("You won "+blHumanPlayer.getCurrentBet()+" chips.");
						blHumanPlayer.setChips(blHumanPlayer.getChips() + 2*blHumanPlayer.getCurrentBet());
					}
					else
					{
						System.out.println("You lost "+blHumanPlayer.getCurrentBet()+" chips.");
					}
				}
			}
			System.out.print("Want to play the next round ?(yes/no)");
			getPlayerChoice("no");
		}
		System.out.println(blHumanPlayer.getPlayerName()+" you are out of chips !! Bye !!");
	}
	
	public void startRound()
	{
		boolean roundEnd = false;
		blHumanPlayer.clearCards();
		blDealer.clearCards();
		blHumanPlayer.setBlackjack(false);
		blDealer.setBlackjack(false);
		setBets();
		dealInitialCards();
		blHumanPlayer.calcScores();
		blDealer.calcScores();
		System.out.println("Your score is "+Collections.max(blHumanPlayer.getScore()));
		if(blHumanPlayer.checkScore(21))
		{
			blHumanPlayer.setBlackjack(true);
			System.out.println(blHumanPlayer.getPlayerName()+" you got Blackjack !!");
			roundEnd = true;
		}
		if(blDealer.checkScore(21))
		{
			blDealer.setBlackjack(true);
		}
		while(roundEnd==false)
		{
			if(blHumanPlayer.checkScore(21))
			{
				System.out.println(blHumanPlayer.getPlayerName()+" you got 21 !!");
				roundEnd = true;
			}
			else
			{
				System.out.println("Options:\n1) Hit (Press h)\n2) Stand (Press s)");
				String playerChoice = getPlayerChoice("");
				if(playerChoice.equalsIgnoreCase("h"))
				{
					blHumanPlayer.addCard(blDealer.dealCard());
					blHumanPlayer.calcScores();
					System.out.println("\n-------------------"+blHumanPlayer.getPlayerName()+"'s Cards--------------------");
					blHumanPlayer.displayCards();
					if(!blHumanPlayer.busted())
					{
						System.out.println("Your score is "+Collections.max(blHumanPlayer.getScore()));
					}
					else	
					{
						System.out.println(blHumanPlayer.getPlayerName()+" you are busted !!");
						roundEnd = true;
					}
				}
				else if(playerChoice.equalsIgnoreCase("s"))
				{
					roundEnd = true;
				}
			}
		}	
	}
}
