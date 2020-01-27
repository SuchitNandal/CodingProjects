import java.util.Scanner;

public class BattleshipLayout {

    static int size;
    Integer [][] grid;

    public BattleshipLayout(int size){
        this.size = size;
    }

    public Integer[][] makegrid(){
        grid = new Integer[size][size];

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                grid[i][j] = 0;
            }
        }
        return grid;
    }

    public Integer[][] placefirstship(int row, int column, String dimension,Integer[][] layout){
        System.out.println("Placing the first ship:");

        if (dimension.equalsIgnoreCase("horizontal")) {
            if (row > size-1 || column + 1 > size-1 || row < 0 || column < 0 ) {
                System.out.println("Index out of bounds. Try again.");
            }
            else if (layout[row][column] == 0 && layout[row][column+1] == 0 ){
                layout[row][column] = 1;
                layout[row][column+1] = 1;
                System.out.println("Placed the ship at "+row+","+column);
            }
            else System.out.println("Try placing ship somewhere else again.");
        }

        if (dimension.equalsIgnoreCase("vertical")) {
            if (row + 1 > size-1 || column > size-1 || row < 0 || column < 0 ) {
                System.out.println("Index out of bounds. Try again.");
            }
            else if (layout[row][column] == 0 && layout[row+1][column] == 0 ){
                layout[row][column] = 1;
                layout[row+1][column] = 1;
                System.out.println("Placed the ship at "+row+","+column);
            }
            else System.out.println("Try placing ship somewhere else again.");
        }
        return layout;
    }


    public Integer[][] placesecondship(int row, int column, String dimension,Integer[][] layout){
        System.out.println("Placing the second ship:");

        if (dimension.equalsIgnoreCase("horizontal")) {
            if (row > size-1 || column + 3 > size-1 || row < 0 || column < 0 ) {
                System.out.println("Index out of bounds. Try again.");
            }
            else if (layout[row][column] == 0 && layout[row][column+1] == 0 && layout[row][column+2] == 0
                    && layout[row][column+3] == 0){
                layout[row][column] = 1;
                layout[row][column+1] = 1;
                layout[row][column+2] = 1;
                layout[row][column+3] = 1;
                System.out.println("Placed the ship at "+row+","+column);
            }
            else{
                System.out.println("Try placing ship somewhere else again.");
            }
        }

        if (dimension.equalsIgnoreCase("vertical")) {
            if (row + 3 > size-1 || column > size-1 || row < 0 || column < 0 ) {
                System.out.println("Index out of bounds. Try again.");
            }
            else if (layout[row][column] == 0 && layout[row+1][column] == 0 && layout[row+2][column] == 0
                    && layout[row+3][column] == 0){
                layout[row][column] = 1;
                layout[row+1][column] = 1;
                layout[row+2][column] = 1;
                layout[row+3][column] = 1;
                System.out.println("Placed the ship at "+row+","+column);
            }
            else System.out.println("Try placing ship somewhere else again.");
        }
        return layout;
    }

    public Integer[][] enter_location1(Integer[][] grid) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the coordinates of the first ship");
        String coor = sc.next();
        String[] arr = coor.split(",");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter if you want horizontal or vertical?");
        String direction = scanner.next();
        grid = placefirstship(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), direction,grid);
        System.out.println("\n");
        return grid;
    }

    public Integer[][] enter_location2(Integer[][] grid) {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the coordinates of the second ship");
        String coor2 = sc2.next();
        String[] arr2 = coor2.split(",");
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter if you want horizontal or vertical?");
        String direction2 = scanner2.next();
        grid = placesecondship(Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]),direction2,grid);
        return grid;
    }


    public String[][] print_map(String[][] map) {
        System.out.println("\n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        return map;
    }
}
