package model;

import java.awt.Image;

/**
 * The purpose of the card class is used to simulate a 
 * real poker card. It contains a suit, type, and image. 
 * The card can be flipped/cloned/selected.
 * 
 * @author BaileyGranam
 * 
 * @due 03/22/2017
 *
 */

public class Card
{
	private Image myImage;
	private CardSuit mySuit;
	private CardType myType;
	private boolean myIsFaceUp;
	private boolean myIsSelected;

	/**
	 * The purpose of the default card method is to 
	 * create a new card with a specific suit, type, 
	 * and image. myIsFaceUp is automatically set
	 * to false as default.
	 * 
	 * @param suit
	 * @param type
	 * @param image
	 */
	
	public Card(CardSuit suit, CardType type, Image image)
	{
		myIsFaceUp = false;
		myIsSelected = false;
		mySuit = suit;
		myType = type;
		myImage = image;
		
	}
	
	/**
	 * The purpose of the isFaceUp() class is to return
	 * whether or not the card is faced up.
	 * 
	 * @return myIsFaceUp
	 */

	public boolean isFaceUp()
	{
		return myIsFaceUp;
	}

	/** 
	 * The purpose of the flip() class is to change the status 
	 * of the card/flip it. I.e if it is down then it is changed
	 * to face up and vice versa.
	 */
	
	public void flip()
	{
		myIsFaceUp = !myIsFaceUp;
	}

	/**
	 * The purpose of the isSelected() class is to return
	 * whether or not the card is selected.
	 * 
	 * @return myIsSelected
	 */
	
	public boolean isSelected()
	{
		return myIsSelected;
	}

	/** 
	 * The purpose of the toggleSelected() class is to change the 
	 * selection status of the card. I.e if it is selected then 
	 * unselect it and vice versa. 
	 */
	
	public void toggleSelected() 
	{
		myIsSelected = !myIsSelected;
	}
	
	/**
	 * The purpose of this class it to see which card has the
	 * greatest value out of the two. 
	 * 
	 * @param card
	 * @return value of card comparison.
	 */

	public int compareTo(Card card) 
	{
		if(getType() > card.getType())
		{
			return 1;
		}
		else if(getType() == card.getType())
		{
			return 0;
		}
		else
		{
			return -1;
		}
	
	}	

	/**
	 * Getter for the CardType of the card.
	 * 
	 * @return myType
	 */
	
	public int getType()
	{
		return myType.getType();
	}
	
	/**
	 * Getter for the CardSuit of the card.
	 * 
	 * @return mySuit
	 */
	
	public String getSuit()
	{
		return mySuit.getSuit();
	}
	
	/**
	 * Getter for the Image of the card.
	 * 
	 * @return myImage
	 */

	public Image getImage()
	{
		return myImage;
	}
	
	/**
	 * Method to return the type/suit of card.
	 * 
	 * @return Card type of card suit
	 */

	public String toString()
	{
		return "Card is a " + getType() + " of " + getSuit();
	}
	
	/**
	 * Method to duplicate/clone the card for future use.
	 * 
	 * @return clone of card
	 */

	public Object clone() throws CloneNotSupportedException
	{
		Card myCardClone = new Card(mySuit, myType, myImage);
		return myCardClone;
	}

}
