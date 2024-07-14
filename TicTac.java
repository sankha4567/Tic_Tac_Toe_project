import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Enter board size: ");
    int boardSize = scanner.nextInt();
    if (boardSize < 3) {
      System.out.println("Board size must be at least 3.");
      return;
    }

    char[][] board = new char[boardSize][boardSize];
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col] = ' ';
      }
    }

    char player = 'X';
    boolean gameOver = false;

    while (!gameOver) {
      printBoard(board);
      System.out.print("Player " + player + " enter row and column: ");
      int row = scanner.nextInt();
      int col = scanner.nextInt();
      System.out.println();

      if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
        System.out.println("Invalid move. Please enter a number between 0 and " + (boardSize - 1) + ".");
        continue;
      }

      if (board[row][col] == ' ') {
        board[row][col] = player; // place the element
        gameOver = haveWon(board, player);
        if (gameOver) {
          printBoard(board);
          System.out.println("Player " + player + " has won!");
        } else if (isBoardFull(board)) {
          printBoard(board);
          System.out.println("The game is a draw!");
          gameOver = true;
        } else {
          player = (player == 'X') ? 'O' : 'X';
        }
      } else {
        System.out.println("Invalid move. Try again!");
      }
    }
  }

  public static boolean haveWon(char[][] board, char player) {
    int size = board.length;

    // check the rows
    for (int row = 0; row < size; row++) {
      boolean win = true;
      for (int col = 0; col < size; col++) {
        if (board[row][col] != player) {
          win = false;
          break;
        }
      }
      if (win) return true;
    }

    // check the columns
    for (int col = 0; col < size; col++) {
      boolean win = true;
      for (int row = 0; row < size; row++) {
        if (board[row][col] != player) {
          win = false;
          break;
        }
      }
      if (win) return true;
    }

    // check the diagonals
    boolean win = true;
    for (int i = 0; i < size; i++) {
      if (board[i][i] != player) {
        win = false;
        break;
      }
    }
    if (win) return true;

    win = true;
    for (int i = 0; i < size; i++) {
      if (board[i][size - 1 - i] != player) {
        win = false;
        break;
      }
    }
    return win;
  }

  public static boolean isBoardFull(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public static void printBoard(char[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        System.out.print(board[row][col] + " | ");
      }
      System.out.println();
    }
  }
}
