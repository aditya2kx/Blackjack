/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0
 * 
 * Dealer is a Player who collects, shuffles and deals the cards.	
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dealer extends Player
{
	private ArrayList<ArrayList<Card>> deckCards;
	private ArrayList<ArrayList<Card>> playedCards;
	int numberDecks;
	int cardsInDeck;
	Random rand;
	
	public void collectCards(int numberDecks)
	{
		this.numberDecks = numberDecks;
		cardsInDeck = 0;
		deckCards = new ArrayList<ArrayList<Card>>();
		playedCards = new ArrayList<ArrayList<Card>>();
		rand = new Random();
		
		Card.possibleCardNumber cardNumber[] = Card.possibleCardNumber.values();
		Card.possibleCardType cardTypes[] = Card.possibleCardType.values();
		
		for(int deckIter = 0; deckIter < numberDecks; deckIter++)
		{
			ArrayList<Card> currentDeckCards = new ArrayList<Card>();
			ArrayList<Card> playedDeckCards = new ArrayList<Card>();
			for(int cardIter = 0; cardIter < cardNumber.length; cardIter++)
			{
				for(int cardTypeIter = 0; cardTypeIter < cardTypes.length; cardTypeIter++)
				{
					currentDeckCards.add(new Card(cardNumber[cardIter], cardTypes[cardTypeIter]));
					cardsInDeck++;
				}
			}
			deckCards.add(currentDeckCards);
			playedCards.add(playedDeckCards);
		}
	}
	
	public void shuffleCards()
	{
		for(int deckIter = 0; deckIter < numberDecks; deckIter++)
		{
			ArrayList<Card> currentDeckCards = deckCards.get(deckIter);
			Collections.shuffle(currentDeckCards);
		}
	}
	
	public boolean deckAlmostEmpty()
	{
		int totalPossibleCards = numberDecks*Card.numberOfPossibleCards*Card.numberOfPossibleTypes;
		if( (totalPossibleCards - cardsInDeck)/totalPossibleCards < 0.5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void recollectAllCards()
	{
		for(int deckIter = 0; deckIter < numberDecks; deckIter++)
		{
			ArrayList<Card> currentDeckCards = deckCards.get(deckIter);
			currentDeckCards.addAll(playedCards.get(deckIter));
			cardsInDeck += playedCards.get(deckIter).size();
			playedCards.get(deckIter).clear();
		}
	}
	
	public Card dealCard()
	{
		Card dealtCard;
		if(deckAlmostEmpty())
		{
			recollectAllCards();
			shuffleCards();
		}
		int deckNumber = rand.nextInt(numberDecks);
		int cardNumber = rand.nextInt(deckCards.get(deckNumber).size());
		dealtCard = deckCards.get(deckNumber).get(cardNumber);
		deckCards.get(deckNumber).remove(cardNumber);
		cardsInDeck--;
		playedCards.get(deckNumber).add(dealtCard);
		return dealtCard;
	}
}
