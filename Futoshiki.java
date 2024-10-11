import java.util.ArrayList;

/**
 *
 * @author Magyar Melinda Barbara
 */

public class Futoshiki {
    private static int[][] board; // Matrix of numbers

    private static final int N = 4; // Size of the board
    private static final int EMPTY = 0; // Represents an empty cell

    private static ArrayList<Integer> signs; // list of the clue signs

    // Constructor
    public Futoshiki(int[][] board) {
        this.signs = new ArrayList<>();
        this.board = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.board[i][j] = board[i][j];
    }

    public static ArrayList<Integer> getSigns() {
        return signs;
    }

    // Function to check if the number is already used in a specific row
    private boolean usedRow(int row, int number) {
        for (int i = 0; i < N; i++)
            if (board[row][i] == number)
                return true;

        return false;
    }

    // Function to check if the number is already used in a specific column
    private boolean usedColumn(int column, int number) {
        for (int i = 0; i < N; i++)
            if (board[i][column] == number)
                return true;

        return false;
    }

    // Function to check if it is okay to assign the number
    // to a specific row and column location on the board
    private boolean isFine(int row, int column, int number) {
        return !usedRow(row, number) && !usedColumn(column, number);
    }

    // Function to check if the signs on the board
    // correspond to the current state of the game
    private boolean signsAlright() {
        for (int i = 0; i < signs.size();) {
            int x1 = signs.get(i++);
            int y1 = signs.get(i++);
            int x2 = signs.get(i++);
            int y2 = signs.get(i++);

            if (board[x1][y1] >= board[x2][y2])
                return false;
        }

        return true;
    }

    // Function to solve the problem using a simple backtracking algorithm
    public boolean solveFutoshiki() {
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                // Check if there are any empty cells left
                if (board[row][column] == EMPTY) {
                    // If found, try replacing it with possible numbers
                    for (int number = 1; number <= N; number++) {
                        // Check if the number is not already used
                        if (isFine(row, column, number)) {
                            board[row][column] = number;

                            // Backtrack recursively
                            if (solveFutoshiki())
                                return true;
                            // If there is no solution, reset the cell
                            else
                                board[row][column] = EMPTY;
                        }
                    }
                    // If we've tried but didn't find a solution,
                    // then there's no solution in the current state
                    return false;
                }
            }

            // After going through all the rows, check if the
            // numbers correspond to their sign positions
            // If yes, return true; otherwise, return false
            if (row >= (N - 1))
                return signsAlright();
        }

        // The game is solved
        return true;
    }

    // Function to display the board
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(board[i][j]).append(" ");

            sb.append("\n");
        }

        sb.append("\n");

        return sb.toString();
    }
}
