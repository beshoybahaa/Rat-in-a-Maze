import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.createMaze();
        maze.addBlock(1,1);
        maze.printMaze();
    }
}