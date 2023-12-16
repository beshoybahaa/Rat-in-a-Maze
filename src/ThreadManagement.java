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
        if (numberOfCurrentThreads < 3) {
            ++numberOfCurrentThreads;
        }
    }

    public int[] popStack() {
        return (int[])this.stack.pop();
    }

    @Override
    public void run() {
        if (found) {
            return;
        }
        int actionNumber = maze.checkNext(x, y);
        switch (actionNumber) {
            case 0:
            synchronized (obj) {
                maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                found = true;
                break;
            }
            case 1:
            synchronized (obj) {
                maze.visit(x, ++y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                found = true;
                break;
            }
            case 2:
                synchronized (obj) {
                    if (numberOfCurrentThreads < 3) {
                        ThreadManagement tm1 = new ThreadManagement(maze, x, y);
                        Thread t1 = new Thread(tm1);
                        incrementNumberOfCurrentThreads();
                        t1.start();
                        maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        run();
                    } else {
                        int[] a = {x, y+1};
                        stack.push(a);
                        maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                        run();
                    }
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
}