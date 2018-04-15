package model;

import java.util.Vector;

/**
 * The purpose of the ComputerPlayer class is so that a player may play with a
 * simple AI. Specific cards are discarded based on the hand of the computer.
 * 
 * @author BaileyGranam
 * 
 * @due 03/22/2017
 *
 */
public class ComputerPlayer extends Player
{
	public ComputerPlayer(String name)
	{
		super(name);
		myAmAI = true;
	}
	
	/**
	 * The purpose of this large method is to determine what cards the AI should discard
	 * depending on the status of the poker hand and the position of the cards. The cards
	 * are removed and we return the vector of discarded cards.
	 * 
	 * @return myDiscards
	 */
	public Vector<Integer> selectCardsToDiscard()
	{
		Vector<Integer> myDiscards = new Vector<Integer>();

		if (myHand.isRoyalFlush() || myHand.isStraightFlush() || myHand.isFourOfKind() || myHand.isFullHouse()
				|| myHand.isFlush() || myHand.isStraight())
		{
			return myDiscards;
		}

		if(myHand.isThreeOfKind())
		{
			if(myHand.getCards().get(0).getType() == myHand.getCards().get(1).getType()
			&& myHand.getCards().get(1).getType() == myHand.getCards().get(2).getType())
			{
				myDiscards.add(3);
				myDiscards.add(4);

				myHand.discard(myDiscards);

			}
			else if(myHand.getCards().get(1).getType() == myHand.getCards().get(2).getType()
			&& myHand.getCards().get(2).getType() == myHand.getCards().get(3).getType())
			{
				myDiscards.add(0);
				myDiscards.add(4);

				myHand.discard(myDiscards);
			}
			else
			{
				myDiscards.add(0);
				myDiscards.add(1);
				
				myHand.discard(myDiscards);
			}
			return myDiscards;
		}

		if(myHand.isTwoPair())
		{
			if(myHand.getCards().get(0).getType() == myHand.getCards().get(1).getType()
					&& myHand.getCards().get(2).getType() == myHand.getCards().get(3).getType())
			{
				myDiscards.add(4);
				myHand.discard(myDiscards);

			}
			else
			{
				myDiscards.add(0);

				myHand.discard(myDiscards);
			}

			return myDiscards;
		}

		if(myHand.isPair())
		{
			if(myHand.getCards().get(0).getType() == myHand.getCards().get(1).getType())
			{
				myDiscards.add(2);
				myDiscards.add(3);
				myDiscards.add(4);

				myHand.discard(myDiscards);

			}
			else if(myHand.getCards().get(1).getType() == myHand.getCards().get(2).getType())
			{
				myDiscards.add(0);
				myDiscards.add(3);
				myDiscards.add(4);

				myHand.discard(myDiscards);
			}
			else
			{
				myDiscards.add(1);
				myDiscards.add(2);
				myDiscards.add(3);
				myHand.discard(myDiscards);
			}
			return myDiscards;
		}

		if(myHand.isHighCard())
		{
			myDiscards.add(0);
			myDiscards.add(1);
			myDiscards.add(2);

			myHand.discard(myDiscards);

			return myDiscards;
		}
		return myDiscards;

	}
	
}
