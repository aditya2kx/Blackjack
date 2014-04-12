/*
 * Author : Aditya Parikh
 * Version : 1.0.0.0
 * 
 * Card is a thing defined to be one of the actual playing cards.	
 */
public class Card
{
	private possibleCardNumber cardNumber;
	private possibleCardType cardType;
	public enum possibleCardNumber{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	};
	public enum possibleCardType{
		HEART, SPADE, DIAMOND, CLUB
	};
	public static int numberOfPossibleCards = possibleCardNumber.values().length;
	static int numberOfPossibleTypes =  possibleCardType.values().length;
	
	public Card(possibleCardNumber cardNumber, possibleCardType cardTypes)
	{	
		this.cardNumber = cardNumber;
		this.cardType = cardTypes;
	}
	
	public possibleCardNumber getCardNumber()
	{
		return cardNumber;
	}
	
	public possibleCardType getCardType()
	{
		return cardType;
	}
	
	public String toString()
	{
		return cardNumber+" "+cardType;
	}
}
