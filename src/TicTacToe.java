import java.util.Scanner;

public class TicTacToe {
    // Define the game board as a 2D array
    private final char[][] board = new char[3][3];
    private final int PlayerOne = 1;
    private int currentPlayer;
    private boolean gameEnded = false;

    // Initialize the game board with empty spaces
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print the game board to the console
    public void printBoard() {
        System.out.println("  1 2 3");
        System.out.println(" -------");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
            System.out.println(" -------");
        }
    }

    // Handle the players turn
    public void playersTurn() {
        Scanner scanner = new Scanner(System.in);
        int playerTwo = 2;
        if (currentPlayer != PlayerOne) {
            System.out.println("Player " + PlayerOne + "'s turn.");
        } else {
            System.out.println("Player " + playerTwo + "'s turn.");
        }
        int row, col;

        do {
            System.out.println("Enter row number (1-3): ");
            row = scanner.nextInt() - 1;
            System.out.println("Enter column number (1-3): ");
            col = scanner.nextInt() - 1;
        } while (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ');

        if (currentPlayer == PlayerOne) {
            board[row][col] = 'X';
            currentPlayer = playerTwo;
        } else {
            board[row][col] = 'O';
            currentPlayer = PlayerOne;
        }

    }

    // check if the game has ended (either someone won or it's a tie)
    public char checkGameEnded() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                gameEnded = true;
                return currentPlayer == PlayerOne ? 'O' : 'X';
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                gameEnded = true;
                return currentPlayer == PlayerOne ? 'O' : 'X';
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            gameEnded = true;
            return currentPlayer == PlayerOne ? 'O' : 'X';
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            gameEnded = true;
            return currentPlayer == PlayerOne ? 'O' : 'X';
        }

        // Check for a tie
        boolean emptySpaceExists = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    emptySpaceExists = true;
                    break;
                }
            }
        }
        if (!emptySpaceExists) {
            gameEnded = true;
            return 'T';
        }

        // Game has not ended
        return ' ';
    }


    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();

            gameEnded = false;

            while (!gameEnded) {
                printBoard();
                playersTurn();
                char result = checkGameEnded();

                if (result == 'O') {
                    printBoard();
                    System.out.println("Player 2 wins!");
                    gameEnded = true;
                } else if (result == 'X') {
                    printBoard();
                    System.out.println("Player 1 wins!");
                    gameEnded = true;
                } else if (result == 'T') {
                    printBoard();
                    System.out.println("It's a tie!");
                    gameEnded = true;
                }
            }

            System.out.println("Do you want to play again? (yes/no)");
            String answer = scanner.next();
            playAgain = answer.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
    }

}


