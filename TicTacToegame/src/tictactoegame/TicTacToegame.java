import java.awt.Color;                       
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.*;

// The main class representing the Tic Tac Toe game as a JFrame
public class TicTacToegame extends JFrame {
    // Declaration of instance variables
    private final JButton[][] buttons; // 2D array of JButtons representing the game grid
    private final int[][] board; // 2D array to track the state of the game board
    private int currentPlayer; // Current player (1 for X, 2 for O)
    private int player1Wins; // Number of wins for Player 1 (X)
    private int player2Wins; // Number of wins for Player 2 (O)
    private final LinkedList moves; // Linked list to store the moves made in the game

    private int currentRound; // Track the current round in the tournament
    private int totalRounds; // Total number of rounds in the tournament
    private boolean isTournamentMode; // Flag to indicate whether the game is in tournament mode
    private List<Integer> playedPlayers; // Track played players in the current round
    //Constructor for tournament mode
    public TicTacToegame(int size, int rounds, boolean isTournamentMode) {
        buttons = new JButton[size][size];//Ye esa array hai jo dynamamically array ka size change karega(Jis ki wajah se hum unlimited size of grids de pa rahe hain)
        board = new int[size][size];//Ye esa array hai jo dynamamically array ka size change karega(Jis ki wajah se hum unlimited size of grids de pa rahe hain)
        currentPlayer = 1;
        player1Wins = 0;
        player2Wins = 0;
        moves = new LinkedList();//Object created which name is moves(Jo ke track record rakhega players ka tournament me)
        currentRound = 1;
        totalRounds = rounds;
        this.isTournamentMode = isTournamentMode;

        initializeUI(size);
        if (isTournamentMode) {
            startTournament();
        }
    }
     // Method to start the tournament
     private void startTournament() {
        if (currentRound <= totalRounds) {
            resetGame(); // Reset the game for the next round
            JOptionPane.showMessageDialog(null, "Tournament Round " + currentRound);
        } else {
            endTournament();
        }
    }
     
    //Method to end the Tournament
    private void endTournament() {
    if (isTournamentMode) {
        List<String> winners = new ArrayList<>();//winners store krwane ke liye array list
        if (player1Wins > player2Wins) {
            JOptionPane.showMessageDialog(null, "Tournament Over. Player 1 wins!");
            winners.add("Player 1");
        } else if (player1Wins < player2Wins) {
            JOptionPane.showMessageDialog(null, "Tournament Over. Player 2 wins!");
            winners.add("Player 2");
        } else {
            JOptionPane.showMessageDialog(null, "Tournament Over. It's a tie!");
            winners.add("It's a tie!");
        }
        String winnerMessage = "Tournament Over. Winners: " + String.join(", ", winners);
        JOptionPane.showMessageDialog(null, winnerMessage);
        //For Tournament Mode
        // Ask for replay or exit
        int replayChoice = JOptionPane.showOptionDialog(null, "Now you can exit?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[] {"Exit" }, "Exit");

        if (replayChoice == JOptionPane.YES_OPTION) {
            // Exit the application
            System.exit(0);
        }
    } else {
        // Individual mode, display individual game over message
        if (player1Wins > player2Wins) {
            JOptionPane.showMessageDialog(null, "Game Over. Player 1 wins!");
            updategame();
        } else if (player1Wins < player2Wins) {
            JOptionPane.showMessageDialog(null, "Game Over. Player 2 wins!");
            updategame();
        } else {
            JOptionPane.showMessageDialog(null, "Game Over. It's a tie!");
            updategame();
        }
       

        // Ask for replay or exit
        int replayChoice1 = JOptionPane.showOptionDialog(null, "Do you want to replay or exit?", "Replay or Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[] { "Replay", "Exit" }, "Replay");

        if (replayChoice1 == JOptionPane.YES_OPTION) {
            updategame();
            resetGame(); // Replay the game
        } else {
            // Exit the application
            System.exit(0);
        }
    }
    
}
    
    public void updategame(){
        currentPlayer = 0;
        player1Wins = 0;
        player2Wins = 0;
    }

    // Constructor for initializing the game with a given size( Starting game for Individual Mode)
    public TicTacToegame(int si) {
        buttons = new JButton[si][si];
        board = new int[si][si];
        currentPlayer = 1;
        player1Wins = 0;
        player2Wins = 0;
        moves = new LinkedList(); // Initialize the linked list for moves

        initializeUI(si); // Set up the graphical user interface
    }

    // Method to initialize the graphical user interface
    private void initializeUI(int si) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dynamic Tic Tac Toe");
        setLayout(new GridLayout(si, si));
        // Create buttons and add them to the JFrame
        for (int i = 0; i < si; i++) {
            for (int j = 0; j < si; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.BLACK);//Setting color of backgroung
                buttons[i][j].setFont(new Font("Arial", Font.HANGING_BASELINE, 20));//Setting the size,font
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }

        setSize(500, 500);//setting the windows size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ResetTournament() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // ActionListener for handling button clicks
    private class ButtonClickListener implements ActionListener {
        private final int row; // Row index of the clicked button
        private final int col; // Column index of the clicked button

        // Constructor for ButtonClickListener
        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // Method called when a button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if the selected cell is empty
            if (board[row][col] == 0) {//Intializing the board for the game play
                board[row][col] = currentPlayer; // Set the current player's mark on the board
                buttons[row][col].setText(currentPlayer == 1 ? "X" : "O"); // Set button text as X or O
                setTextColor(buttons[row][col], currentPlayer); // Set text color based on the current player

                // Store the move in the linked list
                moves.addNode(new Move(row, col));

                // Check if the current player has won
                if (checkWinner()) {
                    JOptionPane.showMessageDialog(null,
                            "Player " + currentPlayer + " wins!\nPlayer 1: " + player1Wins + "\nPlayer 2: " +
                                    player2Wins);
                    resetGame(); // Reset the game after a win
                }
                // Check if the board is full (tie)
                else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null,
                            "It's a tie!\nPlayer 1: " + player1Wins + "\nPlayer 2: " + player2Wins);
                    resetGame(); // Reset the game after a tie
                }
                // Switch to the next player's turn
                else {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;//Ternary operator (Which works like if and else and in the code it is used to switch player turns)
                }
            }
            if (checkRoundEnd()) {
                currentRound++;
                startTournament();
            }
        }
        /**
 * Checks if the current round has ended by comparing the sum of player1Wins and player2Wins to the current round number.
 * This method returns true if the condition is satisfied, indicating the end of the round.
 */
        private boolean checkRoundEnd() {
            return (player1Wins + player2Wins) == currentRound;//Ye condition kabhi false nahi hogi
        }
    }

        // Method to set text color based on the current player
        private void setTextColor(JButton button, int player) {
            if (player == 1) {
                button.setForeground(Color.GREEN); // Set color for Player 1 (X)
            } else {
                button.setForeground(Color.BLUE); // Set color for Player 2 (O)
            }
        }

        // Method to check if the current player has won
        private boolean checkWinner() {
            // Check rows, columns, and diagonals for a win
            for (int i = 0; i < board.length; i++) {
                if (checkLine(board[i]) || checkLine(getColumn(i)) || checkDiagonal()) {
                    if (currentPlayer == 1) {
                        player1Wins++; // Increment Player 1's win count
                    } else {
                        player2Wins++; // Increment Player 2's win count
                    }
                    return true; // The current player has won
                }
            }
            return false; // No winner yet
        }

        // Method to check if all values in a line are the same
        private boolean checkLine(int[] line) {
            int count = 0;
            for (int value : line) {
                if (value == currentPlayer) {
                    count++;
                }
            }
            return count == line.length; // True if all values in the line(or raw) are the same as the current player(hum ise raw line checking bhi keh sakte hain ke ke agr ek line me "x" ya "o" pore hogae hain)
        }

        // Method to get a column from the game board
        private int[] getColumn(int col) {
            int[] column = new int[board.length];
            for (int i = 0; i < board.length; i++) {
                column[i] = board[i][col];
            }
            return column;
        }

        // Method to check if there is a win on either diagonal
        private boolean checkDiagonal() {
            int count1 = 0;
            int count2 = 0;

            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == currentPlayer) {
                    count1++;
                }
                /**
 * Checks if the current player's mark in the diagonal from top-right to bottom-left matches the currentPlayer.
 * This condition is part of the check for a win on one of the diagonals in the Tic Tac Toe board.
 */
                if (board[i][board.length - 1 - i] == currentPlayer) {
                    count2++;
                }
            }

            return count1 == board.length || count2 == board.length; // True if either diagonal has all values of the current player
        }

        // Method to check if the board is full (a tie)
        private boolean isBoardFull() {
            for (int[] row : board) {
                for (int cell : row) {
                    if (cell == 0) {
                        return false; // There is an empty cell, the board is not full
                    }
                }
            }
            return true; // All cells are filled, the board is full (tie)
        }
    

    // Method to reset the game to its initial state(upr code ise call kiya hwa hai magr humne method yahan banaya hai)
    private void resetGame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0; // Reset each cell in the board
                buttons[i][j].setText(""); // Clear the text on each button
            }
        }
        currentPlayer = 1; // Reset the current player to Player 1
    }

    // Inner class representing a move (row, column)
    private class Move {
        private final int row;
        private final int col;

        // Constructor for Move
        public Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // Inner class representing a linked list
    private class LinkedList {
        private Node head;

        // Constructor for LinkedList
        public LinkedList() {
            this.head = null;
        }

        // Method to add a node to the linked list
        public void addNode(Move move) {
            Node newNode = new Node(move);
            if (head == null) {//Check kare if linkedlist empty hai 
                head = newNode;//agr empty hai to newnode banegi or usme save hojaiga
            } else {
                Node temp = head;//Check null wali node ko 
                while (temp.next != null) {//agr node not equal to null hai(yani linkedlist empty nahi hai 
                    temp = temp.next;//traversing =line by line check karega
                }
                temp.next = newNode;//jb koi node null ho jae checking ke bad tb ye execute hojaiga
            }
        }
    }

    // Inner class representing a node in the linked list
    private class Node {
        private final Move data;
        private Node next;

        // Constructor for Node
        public Node(Move data) {
            this.data = data;
            this.next = null;//pehel jo next pointer hai wo null ko represent karega or phir wo age gradually new nodes ko point out karega jese linklist me hota tha
        }
    }
    // Main method to start the Tic Tac Toe game
    public static void main(String[] args) {
        int choice = JOptionPane.showOptionDialog(null, "Choose Game Mode", "Tic Tac Toe",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
        new Object[] { "Individual", "Tournament" }, "Individual");

if (choice == JOptionPane.YES_OPTION) {
    // Individual game
    int size = Integer.parseInt(JOptionPane.showInputDialog("Enter the size of the Tic Tac Toe grid:"));
    SwingUtilities.invokeLater(() -> new TicTacToegame(size, 0, false));// Launch the individual game with the specified grid size
} else if (choice == JOptionPane.NO_OPTION) {
    // Tournament
    int size = Integer
            .parseInt(JOptionPane.showInputDialog("Enter the size of the Tic Tac Toe grid:"));
    int rounds = Integer
            .parseInt(JOptionPane.showInputDialog("Enter the number of rounds for the tournament:"));
    SwingUtilities.invokeLater(() -> new TicTacToegame(size, rounds, true));// Launch the tournament game with the specified grid size and number of rounds
}
}
}

