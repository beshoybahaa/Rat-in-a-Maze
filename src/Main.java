import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    // Global Variables
    public static int[][] maze = new int[8][8];
    public static LinkedList<int[]> visited = new LinkedList<>();

    // Functions
    public static void insertMaze() {
        maze[0][0] = 2;

        maze[1][0] = 1;
        maze[6][0] = 1;

        maze[1][1] = 1;
        maze[4][1] = 1;

        maze[4][2] = 1;
        maze[5][2] = 1;

        maze[0][3] = 1;
        maze[1][3] = 1;

        maze[3][4] = 1;
        maze[5][4] = 1;

        maze[1][5] = 1;
        maze[3][5] = 1;
        maze[4][5] = 1;
        maze[5][5] = 1;

        maze[0][6] = 1;
        maze[1][6] = 1;

        maze[1][7] = 1;

        maze[7][7] = 2;
    }
    public static void printMaze() {
        System.out.println("========\nMaze:\n========");
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                System.out.printf(String.valueOf(maze[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printVisited() {
        System.out.println("========\nVisited:\n========");
        for (int[] element : visited) {
            System.out.println(Arrays.toString(element));
        }
    }
    public static void visit(int i, int j, int threadNumber) {
        if (maze[i][j] != 1 && maze[i][j] != 2) {
            maze[i][j] = 3;
            visited.add(new int[]{i,j,threadNumber});
        }
    }
    public static void checkNext(int i, int j) {
        int forward = maze[i][j+1];
        int down = maze[i+1][j];
        if(forward == 2 || down == 2) {
            if(down == 2) {
                //visit(i+1, j);
                // addBlock();
            } else {
                //visit(i, j+1);
            }
            // printVisited(visited);
        } else if(forward == 0 && down == 0) {
            //create threads
        } else if(down == 0) {
            // down
        } else if(forward == 0) {
            // forward
        }
    }

    // Main
    public static void main(String[] args) {
        insertMaze();
        printMaze();
        visit(1,2, 64);
        printVisited();
        printMaze();
    }
}