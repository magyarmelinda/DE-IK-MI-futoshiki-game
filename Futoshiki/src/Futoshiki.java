import java.util.ArrayList;

/**
 *
 * @author Magyar Melinda Barbara
 */

public class Futoshiki {
    private static int[][] board; // matrix of the numbers
    
    private static final int N = 4; // the size of the board
    private static final int EMPTY = 0; // empty cell
    
    private static ArrayList<Integer> signs; // list of the clue signs

    // the constructor
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
    
    // function to check whether the possible 
    // number is already used in a specific row
    private boolean usedRow(int row, int number) {
        for (int i = 0; i < N; i++) 
            if (board[row][i] == number)
                return true;
        
        return false;
    }
    
    // function to check whether the possible 
    // number is already used in a specific column
    private boolean usedColumn(int column, int number) {
        for (int i = 0; i < N; i++) 
            if (board[i][column] == number)
                return true;

        return false;
    }
    
    // function to check whether it is ok
    // to assign the number to the specific
    // row,column location in the board
    private boolean isFine(int row, int column, int number) {
        return !usedRow(row, number) && !usedColumn(column, number);
    }
    
    // function to check whether the signs
    // in the board are correspond to the
    // current state of the game
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
    
    // function to solve the problem with the
    // help of a simple backtracking algorithm
    public boolean solveFutoshiki() {
        for (int row = 0; row < N; row++) {  
            for (int column = 0; column < N; column++) {
                // check if there is any empty cell left
                if (board[row][column] == EMPTY) {
                    // if found; try to replace it
                    // with the possible numbers
                    for (int number = 1; number <= N; number++) {
                        // check whether the number is not already used
                        if (isFine(row, column, number)) {
                            board[row][column] = number;
                            
                            // bactracking reqursively
                            if (solveFutoshiki()) 
                                return true;
                            // if there is no solution, change the cell
                            // to zero and continue to solve the game
                            else board[row][column] = EMPTY; 
                        }
                    }
                    // if we tried but didn't find a solution
                    // then there's no solution in the current state
                    return false;
                }
            }
            
            // when it went through all the rows check whether the
            // numbers are corresponding to their signs position
            // if yes, return true, otherwise return false
            if (row >= (N - 1)) 
                return signsAlright();
        }
        
        // the game is solved
        return true;
    }
    
    // function to display the board
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
