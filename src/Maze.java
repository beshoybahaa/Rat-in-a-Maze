import java.util.Arrays;
import java.util.LinkedList;

public class Maze {
    char[][] maze;
    private final LinkedList<int[]> visited = new LinkedList<>();
    private int n = 0;

    public Maze() {}

    public Maze(int n) {
        this.maze = new char[n][n];
        this.n = n;
        for(int x = 0 ; x < this.n ; x++ ) {
            for(int y = 0 ; y < this.n ; y++ ) {
                maze[x][y] = '0';
            }
        }
        maze[0][0] = '2';
        maze[n-1][n-1] = '2';
    }

    public void insertTestMaze() {
        this.n = 8;
        this.maze = new char[8][8];
        for(int x = 0 ; x < 8 ; x++ ) {
            for(int y = 0 ; y < 8 ; y++ ) {
                maze[x][y] = '0';
            }
        }

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

    public void printMaze() {
        int n = maze.length;
        System.out.println("========\nMaze:\n========");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addBlock(int i, int j) {
        this.maze[i][j] = '1';
    }
    
    public void visit(int i, int j, int threadNumber) {
        if (this.maze[i][j] != '1') {
            this.visited.add(new int[]{i,j,threadNumber});
            if (this.maze[i][j] != '2') {
                this.maze[i][j] = '3';
            }
        }
    }

    public int checkNext(int i, int j) {
        char forward;
        char down;

        if (j+1 < this.n) forward = maze[i][j+1];
        else forward = '1';
        if (i+1 < this.n) down = maze[i+1][j];
        else down = '1';

        if(forward == '2' || down == '2') {
            if(forward == '2') return 1;
            else return 0;
        } else if (forward == '0' && down == '0' ) return 2;
        else if (down == '0') return 3;
        else if(forward == '0') return 4;
        else return 999;
    }
}
