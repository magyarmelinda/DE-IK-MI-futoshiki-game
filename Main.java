import java.util.Scanner;

/**
 *
 * @author Magyar Melinda Barbara
 */

public class Main {

    public static void main(String[] args) {

        // Starting grid, which can be manually set for testing.
        int[][] board = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
        };

        Futoshiki futoshiki = new Futoshiki(board);
        System.out.println(futoshiki.toString());

        // Input formats:
        // x1 y1 < x2 y2
        // x2 y2 < x1 y1
        // Example input (without the '<' symbol):
        // 0 0 0 1 1 0 1 1
        Scanner sc = new Scanner(System.in);
        System.out.println("'>' clue coordinates: ");
        String[] token = sc.nextLine().split(" ");

        if (token.length < 3)
            System.out.println("There are not enough coordinates.");
        else
            for (int i = 0; i < token.length; i++)
                Futoshiki.getSigns().add(Integer.parseInt(token[i]));

        // Saved clue signs.
        // System.out.println(Unequal.getSigns());

        if (futoshiki.solveFutoshiki())
            System.out.println(futoshiki.toString());
        else
            System.out.println("Unsolvable.");
    }
}
