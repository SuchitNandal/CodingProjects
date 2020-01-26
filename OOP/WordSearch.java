import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

public class WordSearch {
    static int flag = 0;
    static int found = 0;
    static String input = "";
    static String input1;
    static int[] array1 = new int[2];

    /**
     * This method prints the content of the file which is given as input as user.
     * @return - Returns the number of rows and columns found in the puzzle.
     */
    public static int[] playgame() {

        int allrows = 0;
        int allcolumns = 0;
        int[] array = new int[2];

        try {
            System.out.print("Enter puzzle file name:>");
            //Users/suchitnandal/Desktop/words.txt
            Scanner sc = new Scanner(System.in);
            input = sc.next();
            if (flag == 1 & input.equals("exit")) System.exit(1);
            sc = new Scanner(new File(input));
            //Prints the content of the file.
            while (sc.hasNextLine()) {
                allrows += 1;
                String content = sc.nextLine();
                System.out.println(content);
                allcolumns = content.replaceAll("\\s+", "").length();
            }
            //Putting the values of rows and columns found.
            array[0] = allrows;
            array[1] = allcolumns;
            array1[0] = allrows;
            array1[1] = allcolumns;
            sc.close();
        } catch (Exception e) {
            System.out.println("File not found.");
            flag += 1;
            playgame();
        }
        return array;
    }

    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void horizontal(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;

        for (int i = 0; i < allrows; i++) {
            String text = "";
            for (int j = 0; j < allcolumns; j++) {
                text += maze[i][j];
            }
            String reverse = "";
            for (int k = text.length() - 1; k >= 0; k--) {
                reverse = reverse + text.charAt(k);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = i;
                System.out.println("Found the word on row " + finalrow);
                found =1;
                break;
            }
        }
    }

    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void vertical(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;
        for (int i = 0; i < allcolumns; i++) {
            String text = "";
            for (int j = 0; j < allrows; j++) {
                text += maze[j][i];
            }
            String reverse = "";
            for (int k = text.length() - 1; k >= 0; k--) {
                reverse = reverse + text.charAt(k);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = i;
                System.out.println("Found the word on column " + finalrow);
                found =1;
                break;
            }
        }
    }

    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void topleft(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;

        String text = "";
        for (int k = 0; k <= allrows - 1; k++) {
            int i = k;
            int j = 0;
            while (i >= 0) {
                text = text + maze[i][j];
                i -= 1;
                j += 1;
            }
            String reverse = "";
            for (int l = text.length() - 1; l >= 0; l--) {
                reverse = reverse + text.charAt(l);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = k;
                System.out.println("Found the word on diagonal from top left " + finalrow);
                found =1;
                break;
            }
        }
    }
    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void bottomright(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;
        String text = "";
        for (int k = 0; k <= allcolumns - 1; k++) {
            int i = allrows - 1;
            int j = k;
            while (j <= allcolumns - 1) {
                text = text + maze[i][j];
                i -= 1;
                j += 1;
            }
            String reverse = "";
            for (int l = text.length() - 1; l >= 0; l--) {
                reverse = reverse + text.charAt(l);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = k;
                System.out.println("Found the word on diagonal from bottom right " + (allrows - finalrow-1));
                found =1;
                break;
            }
        }
    }
    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void topright(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;
        String text = "";
        for (int k = allcolumns - 1; k >= 0; k--) {
            int i = 0;
            int j = k;
            while (j <= allcolumns - 1) {
                text = text + maze[i][j];
                i += 1;
                j += 1;
            }
            String reverse = "";
            for (int l = text.length() - 1; l >= 0; l--) {
                reverse = reverse + text.charAt(l);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = k;
                System.out.println("Found the word on diagonal from top right " + finalrow);
                found =1;
                break;
            }
        }
    }

    /**
     * @param x - Number of rows.
     * @param y - Number of columns.
     * @param maze - The 2d array representing the maze.
     */
    public static void bottomleft(int x, int y, char[][] maze) {
        int allrows = x;
        int allcolumns = y;
        String text = "";
        for (int k = 0; k <= allrows - 1; k++) {
            int i = k;
            int j = 0;
            while (i <= allrows - 1) {
                text = text + maze[i][j];
                i += 1;
                j += 1;
            }
            String reverse = "";
            for (int l = text.length() - 1; l >= 0; l--) {
                reverse = reverse + text.charAt(l);
            }
            text += reverse;
            String patternString = ".*" + input1 + ".*";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            boolean matches = matcher.matches();
            if (matches) {
                int finalrow = k;
                System.out.println("Found the word on diagonal from bottom left " + (allrows - finalrow-1));
                found =1;
                break;
            }
        }
    }

    /**
     * This method takes in the array and then prompts the user to find the given word.
     * @param array - The number of rows and columns in the 2d array.
     */
    public static void play(int[] array) {
        int allrows = array[0];
        int allcolumns = array[1];
        try {

            char[][] maze = new char[allrows][allcolumns];
            Scanner sc = new Scanner(new File(input));
            for (int row = 0; sc.hasNextLine() && row < allrows; row++) {
                maze[row] = sc.nextLine().replaceAll("\\s+", "").toCharArray();
            }

            Scanner prompt = new Scanner(System.in);
            System.out.println("\nWhich word would you like to find?");
            input1 = prompt.next();
            if (input1.equals("exit")) System.exit(2);
            System.out.println("Searching for "+input1+"...");

            //All the methods to find the word in the puzzle.
            horizontal(allrows, allcolumns,maze);
            vertical(allrows, allcolumns,maze);
            topleft(allrows, allcolumns,maze);
            bottomright(allrows, allcolumns,maze);
            topright(allrows, allcolumns,maze);
            bottomleft(allrows, allcolumns,maze);

            if (found == 0) System.out.println("Word not found");
            play(array1);

        }
        //Catching the exception.
        catch (Exception e) {}
    }


    public static void main(String args[]) {
        play(playgame());

    }
}

