package mvc;

import model.*;

/**
 * The purpose of the Controller class is to control the interactions between
 * our model and view.
 * 
 * @author BaileyGranam
 * 
 * @due 04/05/2017
 *
 */
public class Controller
{
    ///////////////////
    // Properties //
    ///////////////////

    private View       myView;
    private PokerModel myModel;
    private Player     myPlayer, 
                       myPlayerUp;
    private int        myNumberOfPlays, 
                       myNumberOfSelectedCards;

    ///////////////////
    // Methods //
    ///////////////////

    /**
     * Controller constructor; view must be passed in since controller has
     * responsibility to notify view when some event takes place.
     */
    public Controller()
    {
        /**
         * First the view is established. This is important so that we may ask
         * the player for a name. We then use this name to create a new player
         * which will be stored as myPlayer.
         */
        myView   = new View(this);
        myPlayer = new Player(myView.newPlayer());

        /**
         * Next we will create the model and pass through myPlayer. Now that all
         * the required classes and objects have been created we can start the
         * game by automatically calling startGame(); within the controller.
         */
        myModel = new PokerModel(myPlayer);

        /**
         * This method sets values and information for the game to function. See
         * {@link #startGame()} for more information.
         */
        startGame();

        /**
         * Once the information has been set in the view and cards have been
         * dealt in the model we can load the game. What this does is that it
         * launches the actual game window and displays the information we set
         * such as player names, scores, cards, etc.
         */
        myView.loadGame();
    }

    /**
     * The purpose of this method is to start the game.
     */
    public void startGame()
    {
        /**
         * The myNumberOfSelectedCards variable is used in keeping track of the
         * number of selected cards. I.e you can not have more than three cards
         * to discard.
         */
        myNumberOfSelectedCards = 0;

        /**
         * The myNumberOfPlays variable is used to keep track of how many times
         * the player has discarded. For the purposes of this assignment, once
         * the player has discarded twice the game will automatically decide a
         * winner.
         */
        myNumberOfPlays = 0;

        /**
         * This method is called to disperse cards to each player.
         */
        myModel.dealCards();

        /**
         * This command is called when we want to refresh the information in the
         * view.
         */
        updateGameInformation();

        /**
         * The setGameMessage() method is used in updating the game message in
         * the view.
         */
        myView.setGameMessage("Welcome to Poker! To begin select any card(s) you want to remove.");

    }

    /**
     * The purpose of this method is to quickly update the information in the
     * view when needed.
     * 
     * When this method is called it will set/update the following information
     * fields,
     * 
     * - Player Name - Computer Name - Player Score - Computer Score - Player
     * Cards - Computer Cards
     */

    public void updateGameInformation()
    {
        myView.setPlayerName(myModel.getPlayer(0).getName());
        myView.setComputerName(myModel.getPlayer(1).getName());
        myView.setPlayerScore(String.valueOf(myModel.getPlayer(0).getNumberWins()));
        myView.setComputerScore(String.valueOf(myModel.getPlayer(1).getNumberWins()));
        myModel.getPlayer(0).getHand().orderCards();
        myView.setPlayerCards(myModel.getPlayer(0).getHand().getCards());
        myView.setComputerCards();
    }

    /**
     * The purpose of this method is to discard any cards the player selected.
     */
    public void discard()
    {
        /**
         * When we discard cards from the player's hand we want that to reflect
         * in the view. This for loop de-selects any selected cards in the view.
         * I.e it removes the border that shows it is selected.
         */
        for (int i = 0; i < 5; i++)
        {
            if (myModel.getPlayer(0).getHand().getCards().get(i).isSelected())
            {
                myView.toggleCardSelected(i);
            }
        }

        /**
         * This loop is necessary as it loops through each player in the game to
         * discard their cards.
         */
        for (int i = 0; i < myModel.getNumberOfPlayers() - 1; i++)
        {
            /**
             * We must first get the player that is currently up.
             */
            myPlayerUp = myModel.getPlayerUp();

            /**
             * If the player that is currently up is the AI/Computer then we
             * will call its method to determine which cards it wants to
             * discard.
             */
            if (myPlayerUp.getAmAI())
            {
                ComputerPlayer myPlayerUp = (ComputerPlayer) myModel.getPlayerUp();
                myPlayerUp.selectCardsToDiscard();
            }

            /**
             * Next we can discard the cards in that player's hand.
             */
            myPlayerUp.getHand().discard();

            /**
             * We then switch turns so that the next player may discard.
             */
            myModel.switchTurns();
        }

        /**
         * After discarding the cards from the players hands we must deal new
         * cards so that each hand again has five cards.
         */
        myModel.dealCards();

        /**
         * After discarding cards and dealing new ones we call this method to
         * reflect/update the changes in the view.
         */
        updateGameInformation();

        /**
         * We set the number of selected cards back to 0 as we have discarded
         * the previously selected cards.
         */
        myNumberOfSelectedCards = 0;

        /**
         * We then increment the number of plays by one as we have just
         * completed this play of discards.
         */
        myNumberOfPlays++;

        /**
         * If we have reached two plays we will now determine the winner of the
         * game. You can change the default number of plays by modifying
         * myNumberOfPlays == {@Value}. Where {@Value} is the number of plays
         * you want the game to go through.
         */
        if (myNumberOfPlays == 2)
        {
            determineWinnerOfRound();
        }
    }

    /**
     * The purpose of this method is to be the action performed when a player
     * selects a card.
     * 
     * @param indexValueOfCard
     */
    public void select(Integer indexValueOfCard)
    {
        /**
         * First we will check to see if the card that the user clicked on is
         * already selected. If it is not selected we will continue into the
         * next layer.
         */
        if (myModel.getPlayer(0).getHand().getCards().get(indexValueOfCard).isSelected() == false)
        {
            /**
             * If the card the user clicked on is not already selected and the
             * user has less than three cards selected to discard then we can
             * add another card to discard.
             */
            if (myNumberOfSelectedCards < 3)
            {
                /**
                 * First we perform the toggleSelected() method on the specific
                 * card in the player's hand so that it is now marked as
                 * selected.
                 */
                myModel.getPlayer(0).getHand().getCards().get(indexValueOfCard).toggleSelected();

                /**
                 * Next we toggle the selected card in the view so that it has a
                 * border around it.
                 */
                myView.toggleCardSelected(indexValueOfCard);

                /**
                 * Finally we increment the number of cards that the player had
                 * selected. (We do this to prevent the player from selecting
                 * more than 3 cards to discard).
                 */
                myNumberOfSelectedCards++;
            }
        }

        /**
         * If the card that the user clicked on is already selected in the hand
         * then we will process this line of commands.
         */
        else if (myModel.getPlayer(0).getHand().getCards().get(indexValueOfCard).isSelected())
        {
            /**
             * First we perform the toggleSelected() method on the specific card
             * in the player's hand so that it is un-selected.
             */
            myModel.getPlayer(0).getHand().getCards().get(indexValueOfCard).toggleSelected();

            /**
             * Next we toggle the selected card in the view so that it has a
             * border around it.
             */
            myView.toggleCardSelected(indexValueOfCard);

            /**
             * Finally we decrement the number of cards that the player had
             * selected.
             */
            myNumberOfSelectedCards--;
        }

        /**
         * Now that the player has selected/un-selected cards we will update the
         * game message accordingly.
         */
        if (myNumberOfSelectedCards > 0)
        {
            myView.setGameMessage("Click discard to remove the selected cards");
        } else
        {
            myView.setGameMessage(
                    "Welcome to Poker! To begin select any card(s) you want to remove.");
        }
    }

    /**
     * Method called when a user decides to click the restart game button.
     */
    public void restart()
    {
        /**
         * First we call the reset method in the model. See
         * {@link PokerModel#resetGame()} for more information.
         */
        myModel.resetGame();

        /**
         * This method is necessary as it re-enables the discard button and
         * disables the restart button.
         * 
         * (If you are starting a new game you should be able to discard a card,
         * but not restart because it is not over).
         */
        myView.toggleButtons();

        /**
         * Next we will start the new game.
         */
        startGame();
    }

    /**
     * This method determines who has won the round of the game. It updates the
     * game message, and increments the player score.
     */
    public void determineWinnerOfRound()
    {
        String myStats = "You had: " + (myModel.getPlayer(0).getHand().determineRanking()
                + " | Computer had: " + (myModel.getPlayer(1).getHand().determineRanking()));

        if (myModel.getPlayer(0).getHand().compareTo(myModel.getPlayer(1).getHand()) == 1)
        {
            myView.setGameMessage("Congradulations, you won the game! " + myStats);
            myModel.getPlayer(0).incrementNumberWins();
        } else if (myModel.getPlayer(0).getHand().compareTo(myModel.getPlayer(1).getHand()) == -1)
        {
            myView.setGameMessage("Sorry.. the computer beat you! " + myStats);
            myModel.getPlayer(1).incrementNumberWins();
        } else
        {
            myView.setGameMessage("It was a tie! The computer wins! " + myStats);
            myModel.getPlayer(1).incrementNumberWins();
        }

        /**
         * After displaying the message as to who won the game with which hand
         * we will show the computer cards as proof.
         */
        myView.showComputerCards(myModel.getPlayer(1).getHand().getCards());

        /**
         * Finally we will toggle the buttons so that the discard button is
         * disabled and the restart button is enabled.
         */
        myView.toggleButtons();
    }
}