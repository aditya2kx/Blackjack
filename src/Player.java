/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0
 * 
 * Player is a person who can play at the table by taking the cards
 * dealt by the dealer.	
 */

import java.util.ArrayList;
import java.util.Collections;

public abstract class Player 
{
	protected String blName;
	protected ArrayList<Card> myCards;
	protected ArrayList<Integer> scores;
	protected boolean blackjack;
	
	public void setPlayerName(String playerName) 
	{
		blName = playerName;
	}
	
	public String getPlayerName() 
	{
		return blName;
	}
	
	public void clearCards()
	{
		myCards = new ArrayList<Card>();
		scores = new ArrayList<Integer>();
		scores.add(0);
	}
	
	public void addCard(Card dealtCard)
	{
		myCards.add(dealtCard);
		if(scores.size()==0)
		{
			scores.add(0);
		}
	}
	
	public void calcScores()
	{
		scores.clear();
		scores.add(0);
		for(int cardIter=0; cardIter<myCards.size();cardIter++)
		{
			if(myCards.get(cardIter).getCardNumber() == Card.possibleCardNumber.ACE)
			{
				int prevScoreSize = scores.size();
				for(int scoreIter=0; scoreIter<prevScoreSize; scoreIter++)
				{
					int currentScore = scores.get(scoreIter);
					if(currentScore + 1 <= 21)
					{
						scores.set(scoreIter, currentScore + 1);
					}
					else
					{
						scores.remove(scoreIter);
					}
					if(currentScore + 11 <= 21)
					{
						scores.add(currentScore + 11);
					}
				}
			}
			else
			{
				for(int scoreIter=0; scoreIter<scores.size(); scoreIter++)
				{
					int currentScore = scores.get(scoreIter);
					
					currentScore += (myCards.get(cardIter).getCardNumber().ordinal() + 1 < 10 ?
								myCards.get(cardIter).getCardNumber().ordinal() + 1:
								10 );
					if(currentScore <= 21)
					{
						scores.set(scoreIter, currentScore);
					}
					else
					{
						scores.remove(scoreIter);
					}
				}
			}
		}
	}
	
	public Card getMyCard(int index)
	{
		return myCards.get(index);
	}
	
	public void displayCards()
	{
		for(int cardIter=0; cardIter<myCards.size();cardIter++)
		{
			System.out.println(myCards.get(cardIter));
		}

	}
	
	public void setBlackjack(boolean status)
	{
		blackjack = status;
	}
	
	public boolean checkBlackjack()
	{
		return blackjack;
	}
	
	public boolean checkScore(int chScore)
	{
		if(!busted() && Collections.max(scores) == chScore)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkGreaterThanScore(int chScore)
	{
		if(busted() || Collections.max(scores) >= chScore)
		{
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> getScore()
	{
		return scores;
	}
	
	public boolean busted()
	{
		return scores.size() == 0;
	}
}
