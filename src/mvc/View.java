package mvc;

import java.awt.*;
import java.lang.reflect.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Card;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class View
{
    //////////////////////
    // Properties //
    //////////////////////

    private JButton          myButtonRestart, 
                             myButtonDiscard;

    private ButtonListener   myRestartListener, 
                             myDiscardListener;

    private ButtonListener[] myCardListener;

    private JLabel           myGameMessage, 
                             myPlayerName, 
                             myPlayerScore, 
                             myComputerName, 
                             myComputerScore;

    private JLabel[]         myPlayerCards, 
                             myComputerCards;

    private JPanel           myPlayerCardsPanel, 
                             myComputerCardsPanel, 
                             myGameInfoPanel, 
                             myButtonsPanel,
                             myPlayerInfoPanel;

    private JFrame           myFrame;

    private ImageIcon        myPlayerCardGraphic, 
                             myComputerCardGraphic;

    private Controller       myController;

    ///////////////////////
    // Methods //
    ///////////////////////

    /**
     * The purpose of this method is to instantiate the view class.
     * 
     * @param controller
     */
    public View(Controller controller)
    {
        // Controller [See Controller.java]
        myController = controller;

        // Frame
        myFrame = new JFrame("Poker Game");

        /**
         * Panels that will contain various information and pieces of the poker
         * game.
         */

        // Player information such as names and scores
        myPlayerInfoPanel = new JPanel(new GridLayout(2, 2));

        // Player's cards
        myPlayerCardsPanel = new JPanel(new GridLayout(1, 5));

        // Computer's cards
        myComputerCardsPanel = new JPanel(new GridLayout(1, 5));

        // Contains buttons such as restart and discard/hold
        myButtonsPanel = new JPanel();

        // Holds an in game message that notifies the user on what actions to
        // perform
        myGameInfoPanel = new JPanel();

        // Labels
        myPlayerName = new JLabel();
        myPlayerScore = new JLabel();
        myComputerName = new JLabel();
        myComputerScore = new JLabel();
        myGameMessage = new JLabel();

        // Player Cards
        myComputerCards = new JLabel[5];
        myPlayerCards = new JLabel[5];

        // Assign new JLabel for each index in JLabel[].
        for (int i = 0; i < 5; i++)
        {
            myPlayerCards[i] = new JLabel();
            myComputerCards[i] = new JLabel();
        }

        // Card Listener
        myCardListener = new ButtonListener[5];

        // Buttons
        myButtonRestart = new JButton("Restart");
        myButtonDiscard = new JButton("Discard");

        // Associate listeners
        this.associateListeners(myController);
    }

    /**
     * When this method is called in the controller the game will load. This
     * includes all the graphics as well as the window so that the user may play
     * immediately after entering their player name.
     */
    public void loadGame()
    {
        // Frame Properties
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1000, 600);
        myFrame.setLayout(new GridLayout(5, 1));
        

        // Position Labels
        myPlayerName.setHorizontalAlignment(JLabel.LEFT);
        myPlayerScore.setHorizontalAlignment(JLabel.LEFT);
        myComputerName.setHorizontalAlignment(JLabel.RIGHT);
        myComputerScore.setHorizontalAlignment(JLabel.RIGHT);

        // Add Labels
        myPlayerInfoPanel.add(myPlayerName);
        myPlayerInfoPanel.add(myComputerName);
        myPlayerInfoPanel.add(myPlayerScore);
        myPlayerInfoPanel.add(myComputerScore);
        myGameInfoPanel.add(myGameMessage);

        // Add Buttons
        myButtonRestart.setEnabled(false);
        myButtonDiscard.setEnabled(true);
        myButtonsPanel.add(myButtonRestart);
        myButtonsPanel.add(myButtonDiscard);

        // Add Player Cards/Set Alignment
        for (int i = 0; i < 5; i++)
        {
            myPlayerCards[i].setHorizontalAlignment(JLabel.CENTER);
            myComputerCards[i].setHorizontalAlignment(JLabel.CENTER);
            myPlayerCardsPanel.add(myPlayerCards[i]);
            myComputerCardsPanel.add(myComputerCards[i]);
        }

        // Add Listeners
        myButtonRestart.addMouseListener(myRestartListener);
        myButtonDiscard.addMouseListener(myDiscardListener);

        // Special Panel Styling
        myPlayerInfoPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
        myButtonsPanel.setBorder(new EmptyBorder(50, 0, 0, 0));

        // Add Panels to Frame
        myFrame.add(myPlayerInfoPanel);
        myFrame.add(myComputerCardsPanel);
        myFrame.add(myPlayerCardsPanel);
        myFrame.add(myButtonsPanel);
        myFrame.add(myGameInfoPanel);
       
        // Extra Frame Properties
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    /**
     * Method used to update/set the name of the player when game is started in
     * Controller.startGame();
     * 
     * @param playerName
     */
    public void setPlayerName(String playerName)
    {
        myPlayerName.setText(playerName);
    }

    /**
     * Method used to update/set the name of the computer when game is started
     * in Controller.startGame();
     * 
     * @param computerName
     */
    public void setComputerName(String computerName)
    {
        myComputerName.setText(computerName);
    }

    /**
     * Method used to update/set the score of the player when game is started in
     * Controller.startGame();
     * 
     * @param playerScore
     */
    public void setPlayerScore(String playerScore)
    {
        myPlayerScore.setText(playerScore);
    }

    /**
     * Method used to update/set the score of the computer when game is started
     * in Controller.startGame();
     * 
     * @param computerScore
     */
    public void setComputerScore(String computerScore)
    {
        myComputerScore.setText(computerScore);
    }

    /**
     * Method that assigns the appropriate card image to be displayed.
     * 
     * @param playerCards
     */
    public void setPlayerCards(Vector<Card> playerCards)
    {
        for (int i = 0; i < playerCards.size(); i++)
        {
            myPlayerCardGraphic = new ImageIcon(playerCards.get(i).getImage());
            myPlayerCards[i].setIcon(myPlayerCardGraphic);
        }
    }

    /**
     * Method that assigns the default back-of-card image so that the player is
     * unable to see the cards.
     * 
     * @param playerCards
     */
    public void setComputerCards()
    {
        for (int i = 0; i < 5; i++)
        {
            myComputerCardGraphic = new ImageIcon("src/images/E.gif");
            myComputerCards[i].setIcon(myComputerCardGraphic);
        }
    }

    /**
     * Method that reveals the computer cards when called. Used for showing the
     * computer cards at the end of the game.
     * 
     * @param computerCards
     */
    public void showComputerCards(Vector<Card> computerCards)
    {
        for (int i = 0; i < computerCards.size(); i++)
        {
            myComputerCardGraphic = new ImageIcon(computerCards.get(i).getImage());
            myComputerCards[i].setIcon(myComputerCardGraphic);
        }
    }

    /**
     * The purpose of this method is to set a border around a card that has been
     * selected by the player. If the card is unselected then we will remove
     * that border.
     * 
     * @param cardSelected
     */
    public void toggleCardSelected(int cardSelected)
    {
        if (myPlayerCards[cardSelected].getBorder() == null)
        {
            myPlayerCards[cardSelected].setBorder(BorderFactory.createLineBorder(Color.black));
        } else
        {
            myPlayerCards[cardSelected].setBorder(null);
        }
    }

    /**
     * The purpose of this method is to set the game message to alert the user.
     * 
     * @param gameMessage
     */
    public void setGameMessage(String gameMessage)
    {
        myGameMessage.setText(gameMessage);
    }

    /**
     * The purpose of this toggle is that when the game is over the player has
     * the option to restart/start a new game and they can not discard.
     */
    public void toggleButtons()
    {
        myButtonRestart.setEnabled(!myButtonRestart.isEnabled());
        myButtonDiscard.setEnabled(!myButtonDiscard.isEnabled());

        if (myButtonRestart.isEnabled())
        {
            for (int i = 0; i < 5; i++)
            {
                myPlayerCards[i].removeMouseListener(myCardListener[i]);
            }
        } else
        {
            this.associateListeners(myController);
        }
    }

    /**
     * The purpose of this method is to retrieve the name of the player from the
     * user. This is then passed to the controller to be used in creating a new
     * player.
     * 
     * @return myPlayerName
     */
    public String newPlayer()
    {
        JFrame frame = new JFrame();

        String myPlayerName = JOptionPane.showInputDialog(frame, "Please enter the Player's Name",
                "Player Name", JOptionPane.PLAIN_MESSAGE);

        return myPlayerName;
    }

    /**
     * Associates each component's listener with the controller and the correct
     * method to invoke when triggered.
     *
     * <pre>
     * pre:  the controller class has to be instantiated
     * post: all listeners have been associated to the controller
     *       and the method it must invoke
     * </pre>
     */
    public void associateListeners(Controller controller)
    {
        Class<? extends Controller> controllerClass;
        Method restartMethod, discardMethod, selectMethod;
        Class<?>[] classArgs;

        controllerClass = controller.getClass();

        restartMethod = null;
        discardMethod = null;
        selectMethod = null;

        classArgs = new Class[1];

        try
        {
            classArgs[0] = Class.forName("java.lang.Integer");
        } catch (ClassNotFoundException e)
        {
            String error;
            error = e.toString();
            System.out.println(error);
        }

        try
        {
            restartMethod = controllerClass.getMethod("restart", (Class<?>[]) null);
            discardMethod = controllerClass.getMethod("discard", (Class<?>[]) null);
            selectMethod = controllerClass.getMethod("select", classArgs);
        } catch (NoSuchMethodException exception)
        {
            String error;

            error = exception.toString();
            System.out.println(error);
        } catch (SecurityException exception)
        {
            String error;

            error = exception.toString();
            System.out.println(error);
        }

        myRestartListener = new ButtonListener(controller, restartMethod, null);
        myDiscardListener = new ButtonListener(controller, discardMethod, null);

        int i;
        Integer[] args;

        for (i = 0; i < 5; i++)
        {
            args = new Integer[1];
            args[0] = new Integer(i);
            myCardListener[i] = new ButtonListener(myController, selectMethod, args);

            myPlayerCards[i].addMouseListener(myCardListener[i]);

        }
    }
}