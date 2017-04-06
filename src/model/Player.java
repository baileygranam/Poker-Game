package model;

/**
 * Models the player of a poker game. Can be named.
 * @author Christopher Finkle
 * @author Tierney Irwin
 */

public class Player
{
	private static final String DEFAULT_NAME = "JohnCena";
	private String myName;
	private int myNumberWins;
	protected boolean myAmAI;
	protected PokerHand myHand;

	/**
	 * Constructor for Player class, when called without name,
	 * gives default name.
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public Player()
	{
		myName = DEFAULT_NAME;
	}
	
	/**
	 * Constructor for Player class, called with name,
	 * validates name and applies it if good.
	 * @param name: name to be named
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public Player(String name)
	{
		if(validateName(name))
		{
			myName = name;
		}
		else
		{
			myName = DEFAULT_NAME;
		}
		
		myNumberWins = 0;
		myAmAI = false;
		myHand = new PokerHand(5);
	}

	/**
	 * Checks that name is composed only of letters, returns false if not.
	 * @param name: string to be tested
	 * @return: whether string valid or not.
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public boolean validateName(String name)
	{
		if(name==null)
		{
			return false;
		}
		char[] chars = name.toCharArray();
	    for (char c : chars)
	    {
	        if(!Character.isLetter(c))
	        {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * Method increments number of wins a player has.
	 * 
	 * @return myNumberWins int value of number of wins a player has
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 * 
	 */
	public int incrementNumberWins()
	{
		myNumberWins++;
		return myNumberWins;
	}

	public String toString()
	{
		return "Player "+myName;
	}

	public Object clone()
	{
		Player myClone = new Player(this.getName());
		while(myClone.getNumberWins()!=this.myNumberWins)
		{
			myClone.incrementNumberWins();
		}
		for(int i =0; i<myHand.getMaxNumberCards();i++)
		{
			myClone.myHand.add(myHand.myCards.get(i));
		}
		
		return myClone;
	}

	public PokerHand getHand()
	{
		return myHand;
	}

	public String getName()
	{
		return myName;
	}

	public int getNumberWins()
	{
		return myNumberWins;
	}

	public boolean getAmAI()
	{
		return myAmAI;
	}

}
