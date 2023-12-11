import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Object obj = new Object();
        ThreadManagement tm1 = new ThreadManagement();
        ThreadManagement tm2 = new ThreadManagement();

        ThreadManagement.incrementNumberOfCurrentThreads(obj);
        ThreadManagement.incrementNumberOfCurrentThreads(obj);

        System.out.println(ThreadManagement.getNumberOfCurrentThreads());

//        Maze maze = new Maze();
//        maze.createMaze();
//        maze.addBlock(1,1);
//        maze.printMaze();
    }
}