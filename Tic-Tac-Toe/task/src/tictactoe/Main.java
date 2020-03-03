package tictactoe;

import java.util.Scanner;

public class Main {
    public static char[][] grid = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    public static int row = 0;
    public static int col = 0;
    public static char sign = 'X';

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        while (checkState().equals("Game not finished")) {
            printGrid();
            getCoordinates(scanner);
        }
        printGrid();
        System.out.println(checkState());
    }

    private static void getCoordinates(Scanner scanner) {
        row = 0;
        col = 0;
        while (row < 1 || row > 3 || col < 1 || col > 3) {
            System.out.print("Enter coordinates: ");
            try {
                col = scanner.nextInt();
                row = scanner.nextInt();
                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (grid[Math.abs(row - 3)][col - 1] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        row = 0;
                        col = 0;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
            }
        }
        grid[Math.abs(row-3)][col-1] = sign;

        if (sign == 'X') {
            sign = 'O';
        } else {
            sign = 'X';
        }
    }

    private static String checkState() {
        int numX = 0;
        int numO = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    ++numX;
                }
                if (grid[i][j] == 'O') {
                    ++numO;
                }
            }
        }
        if (Math.abs(numX - numO) > 1) {
            return "Impossible";
        }

        int result = 0;
        // X wins
        if ((grid[0][0] + grid[0][1] + grid[0][2]) == 264
                || (grid[1][0] + grid[1][1] + grid[1][2]) == 264
                || (grid[2][0] + grid[2][1] + grid[2][2]) == 264
                || (grid[0][0] + grid[1][0] + grid[2][0]) == 264
                || (grid[0][1] + grid[1][1] + grid[2][1]) == 264
                || (grid[0][2] + grid[1][2] + grid[2][2]) == 264
                || (grid[0][0] + grid[1][1] + grid[2][2]) == 264
                || (grid[2][0] + grid[1][1] + grid[0][2]) == 264
        ) {
            result = 1;
        }

        // O wins
        if ((grid[0][0] + grid[0][1] + grid[0][2]) == 237
                || (grid[1][0] + grid[1][1] + grid[1][2]) == 237
                || (grid[2][0] + grid[2][1] + grid[2][2]) == 237
                || (grid[0][0] + grid[1][0] + grid[2][0]) == 237
                || (grid[0][1] + grid[1][1] + grid[2][1]) == 237
                || (grid[0][2] + grid[1][2] + grid[2][2]) == 237
                || (grid[0][0] + grid[1][1] + grid[2][2]) == 237
                || (grid[2][0] + grid[1][1] + grid[0][2]) == 237
        ) {
            result += 2;
        }

        if (result == 1) {
            return "X wins";
        } else if (result == 2) {
            return "O wins";
        } else if (result == 3) {
            return "Impossible";
        }

        // draw
        result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                result += grid[i][j];
            }
        }
        if (result == 756) {
            return "Draw";
        }

        // not finished game;
        return "Game not finished";
    }

    private static void getDataInput(Scanner scanner) {
        char[] input = scanner.nextLine().toCharArray();

        for (int i = 0; i < input.length; i++) {
            grid[i / 3][i % 3] = input[i];
        }
    }

    private static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


}
