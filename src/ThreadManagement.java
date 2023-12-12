import java.util.Set;
import java.util.Stack;

public class ThreadManagement implements Runnable {
    private static final Object obj = new Object();
    private final Maze maze;
    private final Stack<int[]> stack = new Stack<>();
    private static int numberOfCurrentThreads = 0;
    private int x;
    private int y;
    private static boolean found=false;

    public ThreadManagement(Maze maze , int x , int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public static void incrementNumberOfCurrentThreads() {
        if (numberOfCurrentThreads < 4) {
            ++numberOfCurrentThreads;
        }
    }

    public int[] popStack() {
        return (int[])this.stack.pop();
    }



    @Override
    public void run() {
        if (found == true) {
            return;
        }
//        synchronized (obj) {
            int actionNumber = maze.checkNext(x, y);
            switch (actionNumber) {
                case 0:
                synchronized (obj) {
                    maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    maze.printMaze();
                    found = true;
                    break;

                }
                case 1:
                synchronized (obj) {
                    maze.visit(x, ++y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    maze.printMaze();
                    found = true;
                    break;
                }
                case 2:
                    if (numberOfCurrentThreads < 4) {
//                    synchronized (obj) {
                        ThreadManagement tm1 = new ThreadManagement(maze, x, y + 1);
                        maze.visit(x, (y + 1), Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        Thread t1 = new Thread(tm1);
                        incrementNumberOfCurrentThreads();
                        t1.start();
                        maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        run();
//                    }
                        break;
                    } else {
                        int[] a = {++x, y};
                        stack.push(a);
                        maze.visit(x, ++y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        run();
                    }
                    break;
                case 3:
                    maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    run();
                    break;
                case 4:
                    maze.visit(x, ++y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    run();
                    break;
                default:
                    if (!this.stack.isEmpty()) {
                        int[] a = popStack();
                        maze.visit(a[0], a[1], Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        run();
                    }
                    break;
            }
        }
//    }
}