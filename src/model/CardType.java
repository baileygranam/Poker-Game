package model;

/**
 * Enumeration of possible values for cards. 
 * Can return numerical equivalent.
 * @author Tierney Irwin
 * @author Christopher Finkle
 */

public enum CardType
{
	TWO (2),
	THREE (3),
	FOUR (4),
	FIVE (5),
	SIX (6),
	SEVEN (7),
	EIGHT (8),
	NINE (9),
	TEN (10),
	JACK (11),
	QUEEN (12),
	KING (13),
	ACE (14);
	
	private final int myType;
	
	/**
	 * Constructor sets myType to the integer value type given.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 */
	private CardType (int type)
	{
		myType = type;
	}
	
	/**
	 * Method returns the myType value.
	 * 
	 * @return myType integer value of CardType
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 */
	public int getType()
	{
		return myType;
	}

}

