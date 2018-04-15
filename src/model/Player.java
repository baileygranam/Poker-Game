package model;

/**
 * This class is used to create a player and keep track of
 * name, number of wins, the player's hand, and if the 
 * player is an AI. You can get the player name, validate
 * the player name, get the name and hand of the player,
 * and increment the number of wins.
 * 
 * @author BaileyGranam
 * 
 * @due 03/22/2017
 *
 */

public class Player
{
	protected static final String DEFAULT_NAME = "John Cena";
	protected String myName;
	protected int myNumberWins;
	protected boolean myAmAI;
	protected PokerHand myHand;

	public Player(String name)
	{
		myName = name;
		myHand = new PokerHand(5);
		myNumberWins = 0;
		myAmAI = false;
	}

	/**
	 * The purpose of the validateName() class is to ensure
	 * the user entered name is valid. I.e names may only 
	 * contain characters a-z, A-Z, 0-9. No special characters
	 * are allowed.
	 * 
	 * The method returns true if the name is valid and 
	 * false if invalid.
	 * 
	 * @param name
	 * @return true/false
	 */
	
	public boolean validateName(String name)
	{
		if(name.matches("[a-zA-Z0-9]*") && name != null && name != "")
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/**
	 * The purpose of incrementNumberWins() is to add 1 to
	 * the current myNumberWins variable.
	 * 
	 * @return myNumberWins + 1
	 */
	
	public int incrementNumberWins()
	{
		return myNumberWins+=1;
	}

	/**
	 * The purpose of this method is to return the name of the
	 * player and whether or not the object is an AI.
	 */
	
	public String toString()
	{
		return "Player " + getName() + " is an AI: " + getAmAI();
	}

	/**
	 * Purpose of clone() method is to make a duplicate copy 
	 * of the referenced player object.
	 * 
	 */
	
	public Object clone()
	{
		Player myPlayerClone = new Player(myName);
		return myPlayerClone;
	}
	
	/** 
	 * Purpose of the getHand() class is to return
	 * the hand of the player.
	 * 
	 * @return player hand
	 */

	public Hand getHand()
	{
		return myHand;
	}

	/**
	 * Purpose of this method is to return the name
	 * of the specific player.
	 * 
	 * @return name of player
	 */
	public String getName()
	{
		return myName;
	}
	
	/** 
	 * Purpose of getNumberWins() is to return the total 
	 * number of wins for the specific player.
	 * 
	 * @return myNumberWins
	 */

	public int getNumberWins()
	{
		return myNumberWins;
	}

	/** 
	 * Purpose of the method is to return
	 * whether or not the player is an AI.
	 * 
	 * @return myAmAI
	 */
	
	public boolean getAmAI()
	{	
		return myAmAI;
	}

}
