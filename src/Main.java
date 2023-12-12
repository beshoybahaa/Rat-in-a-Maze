public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.createMaze();
        Object obj = new Object();
        ThreadManagement tm1 = new ThreadManagement(maze,0,0);
        Thread t1 = new Thread(tm1);
        t1.start();
        maze.printMaze();

//        Maze maze = new Maze();
//        maze.createMaze();
//        maze.addBlock(1,1);
//        maze.printMaze();
    }
}