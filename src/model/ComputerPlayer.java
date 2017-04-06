package model;

import java.util.Vector;

/**
 * AI opponent for a poker game.
 * @author Christopher Finkle
 * @author Tierney Irwin
 */

public class ComputerPlayer extends Player
{
	public ComputerPlayer()
	{
		super();
		myAmAI = true;
	}
	public ComputerPlayer(String name)
	{
		super(name);
		myAmAI = true;
	}

	/**
	 * If has hand using all 5 cards, keeps it. If has hand using fewer
	 * than all 5 cards, discards as many cards as possible, starting 
	 * with the lowest-valued that are not part of the poker hand in question.
	 * @return vector containing indices of cards to be discarded.
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public Vector<Integer> selectCardsToDiscard()
	{
		Vector<Integer> discards = new Vector<Integer>(0);
		if(myHand.getRanking()<5)
		{
			for(int i=0; i<5; i++)
			{
				if(!myHand.getCards().get(i).isRelevant() && discards.size()<3)
				{
					discards.add(i);
				}
			}
		}
		return discards;
	}
	
	
}
