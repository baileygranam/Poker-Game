package model;

public enum CardSuit
{
	SPADES ("Spades"),
	DIAMONDS ("Diamonds"),
	HEARTS ("Hearts"),
	CLUBS ("Clubs");
	
	private final String mySuit;
	
	private CardSuit(String name) 
	{
		mySuit = name;
	}
	
	public String getSuit()
	{
		return mySuit;
	}
	

}
