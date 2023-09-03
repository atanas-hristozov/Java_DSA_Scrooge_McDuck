import java.util.Scanner;

public class ScroogeMcDuck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] maze = new int[row][col];

        int startRow = 0;
        int startCol = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = scanner.nextInt();
                if (maze[i][j] == 0) {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        System.out.println(collectCoinsInMaze(maze, startRow, startCol));
    }

    public static int collectCoinsInMaze(int[][] maze, int row, int col) {
        if (isSurroundedByZeros(maze, row, col)) {
            return 0;
        }
        return consumeBiggestNumber(maze, row, col);
    }

    public static boolean isOutOfBounds(int[][] maze, int row, int col) {
        return row < 0 || row >= maze.length || col < 0 || col >= maze[0].length;
    }

    public static boolean isSurroundedByZeros(int[][] maze, int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!isOutOfBounds(maze, newRow, newCol) && maze[newRow][newCol] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int consumeBiggestNumber(int[][] maze, int row, int col) {
        int maxCoins = 0;
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (!isOutOfBounds(maze, newRow, newCol) && maze[newRow][newCol] > maxCoins) {
                maxCoins = maze[newRow][newCol];
            }
        }

        if (maxCoins == 0) {
            return 0;
        }

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (!isOutOfBounds(maze, newRow, newCol) && maze[newRow][newCol] == maxCoins) {
                maze[newRow][newCol]--;
                return 1 + collectCoinsInMaze(maze, newRow, newCol);
            }
        }

        return 0;
    }
}