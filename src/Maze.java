import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Maze {
    private int[][] maze;
    private LinkedList<int[]> visited = new LinkedList<>();

    public Maze() {}

    private int n=0;
    public Maze(int n) {
        this.maze = new int[n][n];
        this.n=n;
    }

    public void createMaze() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the maze (n) : ");
        int n = scanner.nextInt();
        this.n=n;

        this.maze = new int[n][n];

        this.maze[0][0] = 2;
        this.maze[n-1][n-1] = 2;

        scanner.close();
    }

    public void printMaze() {
        int n = maze.length;
        System.out.println("========\nMaze:\n========");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(String.valueOf(maze[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addBlock(int x, int y) {
        this.maze[x][y] = 1;
    }

    public void insertTestMaze() {
        this.maze = new int[8][8];

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

    public void printVisited() {
        System.out.println("========\nVisited:\n========");
        for (int[] element : this.visited) {
            System.out.println(Arrays.toString(element));
        }
        System.out.println();
    }
    
    public void visit(int i, int j, int threadNumber) {
        System.out.println("i = " + i + " j = " + j + " thread number = " + threadNumber);
        if (this.maze[i][j] != 1 && this.maze[i][j] != 2) {
            this.maze[i][j] = 3;
            this.visited.add(new int[]{i,j,threadNumber});
        }
    }

    public int checkNext(int i, int j) {
        int forward=0;
        int down=0;
        if(j+1==n){
            forward=1;
        } else if (i+1==n) {
            down=1;
        }else {
            forward = this.maze[i][j+1];
            down = this.maze[i+1][j];
        }
        if(forward == 2 || down == 2) {
            if(down == 2) {
                //visit(i+1, j);
                // addBlock();
                return 0;
            } else {
                //visit(i, j+1);
                // addBlock();
                return 1;
            }
            // printVisited(visited);
        } else if(forward == 0 && down == 0) {
            //create threads
            return 2;
        } else if(down == 0) {
            // down
            return 3;
        } else if(forward == 0) {
            // forward
            return 4;
        }
        return 999;
    }
}