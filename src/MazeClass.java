import java.util.Arrays;
import java.util.LinkedList;

public class MazeClass {
    public char[][] maze;
    private LinkedList<int[]> visited = new LinkedList<>();

    public MazeClass() {}

    public MazeClass(int n) {
        this.maze = new char[n][n];
    }

    public void createMaze(int n) {
        this.maze = new char[n][n];
        this.maze[0][0] = '2';
        this.maze[n-1][n-1] = '2';
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
        this.maze = new char[8][8];

        maze[0][0] = '2';

        maze[1][0] = '1';
        maze[6][0] = '1';

        maze[1][1] = '1';
        maze[4][1] = '1';

        maze[4][2] = '1';
        maze[5][2] = '1';

        maze[0][3] = '1';
        maze[1][3] = '1';

        maze[3][4] = '1';
        maze[5][4] = '1';

        maze[1][5] = '1';
        maze[3][5] = '1';
        maze[4][5] = '1';
        maze[5][5] = '1';

        maze[0][6] = '1';
        maze[1][6] = '1';

        maze[1][7] = '1';

        maze[7][7] = '2';
    }

    public void printVisited() {
        System.out.println("========\nVisited:\n========");
        for (int[] element : this.visited) {
            System.out.println(Arrays.toString(element));
        }
        System.out.println();
    }
    
    public void visit(int i, int j, int threadNumber) {
        if (this.maze[i][j] != 1 && this.maze[i][j] != 2) {
            this.maze[i][j] = 3;
            this.visited.add(new int[]{i,j,threadNumber});
        }
    }

    public void checkNext(int i, int j) {
        int forward = this.maze[i][j+1];
        int down = this.maze[i+1][j];
        if(forward == 2 || down == 2) {
            if(down == 2) {
                //visit(i+1, j);
                // addBlock();
            } else {
                //visit(i, j+1);
                // addBlock();
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
}
