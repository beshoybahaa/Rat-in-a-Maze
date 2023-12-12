public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.insertTestMaze();

        ThreadManagement tm1 = new ThreadManagement(maze,0,0);
        Thread t1 = new Thread(tm1);
        t1.start();
    }
}