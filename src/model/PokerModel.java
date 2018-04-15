package model;
/**
 * PokerModel class sets up the entire poker game using all the other classes,
 * setting up the players, hands, and the mechanics of a poker game. 
 * Input needed is the human player's information, passed through a parameter
 * in the constructor.
 * 
 * @author Bailey Granam
 * 
 */
public class PokerModel
{
    private Player[] myPlayer;
    private int myIndexPlayerUp;
    private final int myMaxRounds = 5;
    private int myRound;
    private Deck myDeck;

    /**
     * PokerModel constructor initializes the Player array with the human
     * and computer player. Resets all rounds to the first round and 
     * starts the human player first with a new deck of cards.
     * 
     * @param player human player's information
     */
    public PokerModel(Player player)
    {
        myPlayer = new Player[2];
        myPlayer[0] = player;
        myPlayer[1] = new ComputerPlayer("Deadpool");
        myIndexPlayerUp=0;
        myRound=1;
        myDeck=new Deck();
    }

    /**
     * Method switchTurns deals with the mechanics of 
     * switching the index of which player's turn it is.
     * 
     * @return myIndexPlayerUp index of Player array 
     * with which player's turn it is.
     *
     */
    public int switchTurns()
    {
        myIndexPlayerUp=(myIndexPlayerUp+1)%2;
        return myIndexPlayerUp;
    }

    /**
     * Method dealCards() fills each player's hands
     *  with the correct amount of cards from the deck 
     *  as specified by maxNumberCards.
     */
    public void dealCards()
    {
        for(int i=0;i<myPlayer.length;i++)
        {
            while(myPlayer[i].myHand.myCards.size()<myPlayer[i].getHand().getMaxNumberCards())
            {
                myPlayer[i].myHand.add(myDeck.draw());
            }
        }
    }

    /**
     * Method determineWinner() considers either both player's hands or
     * the amount of round wins each contain and determines the winner of the round or game.
     * 
     * @return myPlayer[i] player who won the game/round, i depends on which player wins
     */
    public Player determineWinner()
    {
        int myWinner = myPlayer[0].getHand().compareTo(myPlayer[1].getHand());
        
        if(myWinner==0 || myWinner==-1)
        {
            myPlayer[1].incrementNumberWins();
        }
        else
        {
            myPlayer[0].incrementNumberWins();
        }
        
        if(myRound==myMaxRounds)
        {
            if(myPlayer[0].getNumberWins()<=myPlayer[1].getNumberWins())
            {
                return myPlayer[1];
            }
            else
            {
                return myPlayer[0];
            }
        }
        return null;
    }

    /**
     * Method resetGame() clears the information from the prior round 
     * and creates a new Deck.
     * 
     * @return true with the game properly resetting.
     */
    public boolean resetGame()
    {
        if(myPlayer[0].getHand().getCards().size()!= 0)
        {
            myPlayer[0].getHand().getCards().clear();
            myPlayer[1].getHand().getCards().clear();
        }
        myIndexPlayerUp=0;
        myRound++;
        myDeck=new Deck();
        return true;
    }

    /**
     * Method returns which player's turn it currently is.
     * 
     * @return Player whose turn it currently is.
     */
    public Player getPlayerUp()
    {
        return myPlayer[myIndexPlayerUp];
    }

    /**
     * Method returns which Player is being specified by the index.
     * 
     * @param index integer value to determine which player is required.
     * @return Player whose index is specified.
     */
    public Player getPlayer(int index)
    {
        return myPlayer[index];
    }

    /**
     * Method returns index value of player who is currently up.
     * 
     * @return myIndexPlayerUp integer index of which player is currently up..
     * 
     */
    public int getIndexPlayerUp()
    {
        return myIndexPlayerUp;
    }
    
    /**
     * Method to return the number of players in the game. 
     */
    public int getNumberOfPlayers()
    {
        return myPlayer.length;
    }

}
