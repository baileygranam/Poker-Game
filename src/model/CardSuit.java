package model;

/**
 * Enumeration of the possible suits in a standard deck of cards.
 * @author Tierney Irwin
 * @author Christopher Finkle
 */

public enum CardSuit
{
	SPADES ("Spades"),
	DIAMONDS ("Diamonds"),
	HEARTS ("Hearts"),
	CLUBS ("Clubs");
	
	private final String mySuit;
	
	/**
	 * Constructor sets name to the mySuit value.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 */
	private CardSuit(String name) 
	{
		mySuit = name;
	}
	
	/**
	 * Method returns string of mySuit value
	 * 
	 * @return mySuit string of mySuit value.
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 */
	public String getSuit()
	{
		return mySuit;
	}
	

}
