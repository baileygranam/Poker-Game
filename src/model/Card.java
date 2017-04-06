package model;

import java.awt.Image;

/**
 * models a standard playing card. Card can be face up or down,
 * selected for discard, and relevant or irrelevant to a hand.
 * 
 * @author Tierney Irwin
 * @author Christopher Finkle
 */

public class Card implements Comparable<Card>
{
  private CardSuit mySuit;
  private CardType myType;
  private Image myImage;
  private boolean myIsSelected;
  private boolean myIsFaceUp;
  private boolean myIsRelevant;

  public Card(CardSuit suit, CardType type, Image image) 
  {
	  mySuit=suit;
	  myType=type;
	  myImage=image;
	  myIsSelected=false;
	  myIsFaceUp=false;
	  myIsRelevant=false;
  }

  /**
   * Flips card over.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public void flip() 
  {
	  myIsFaceUp = !myIsFaceUp;
  }

  /**
   * Method returns boolean myisFaceUp value
   * 
   * @return myisFaceUp true/false.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public boolean isFaceUp() 
  {
	  return myIsFaceUp;
  }

  /**
   * Method returns boolean myisSelected value
   * 
   * @return myisSelected true/false.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public boolean isSelected() 
  {
	  return myIsSelected;
  }
  /**
   * Method toggles myIsSelected.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public boolean toggleSelected() 
  {
	  myIsSelected = !myIsSelected;
	  return myIsSelected;
  }
  /**
   * Method returns boolean myIsRelevant value
   * 
   * @return myIsRelevant true/false.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public boolean isRelevant()
  {
	  return myIsRelevant;
  }
  /**
   * Method sets myIsRelevant to value of boolean b.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public void setRelevance(boolean b)
  {
	  myIsRelevant = b;
  }
  
  /**
   * Compares this card to another using type, returns -1 if smaller,
   * 0 if tied, 1 if this one is larger.
   * Allows use of implements comparable.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public int compareTo(Card card) 
  {
	  if(myType.getType() > card.getType().getType())
	  {
		  return 1;
	  }
	  if(myType.getType() == card.getType().getType())
	  {
		  return 0;
	  }
	  if(myType.getType() < card.getType().getType())
	  {
		  return -1;
	  }
	  return 0;
  }

  /**
   * Creates identical card and returns it as an Object.
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public Object clone() 
  {
	  Card myClone;
	  CardType myCloneType = myType;
	  CardSuit myCloneSuit = mySuit;
	  Image myCloneImage = myImage;
	  myClone = new Card(myCloneSuit,myCloneType,myCloneImage);
	  return myClone;
  }

  public String toString() 
  {
	  return "Card is a "+getType()+" of "+getSuit();
  }

  /**
   * Method returns mySuit value.
   * 
   * @return mySuit CardSuit value of card
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public CardSuit getSuit()
  {
	  return mySuit;
  }
  
  /**
   * Method returns myType value.
   * 
   * @return myType CardType value of card
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public CardType getType()
  {
	  return myType;
  }
  
  /**
   * Method returns image of card.
   * 
   * @return myImage image of card
   * 
   * @author Tierney Irwin
   * @author Christopher Finkle
   */
  public Image getImage()
  {
	  return myImage;
  }
}