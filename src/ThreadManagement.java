import java.util.Stack;

public class ThreadManagement implements Runnable {
    private static final Object obj = new Object();
    private final Maze maze;
    private final Stack<int[]> stack = new Stack<>();
    private static int numberOfCurrentThreads = 0;
    private int x;
    private int y;
    private static boolean found=false;
    private ResultView solutionView;

    public ThreadManagement(Maze maze , int x , int y, ResultView solutionView) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        this.solutionView = solutionView;
    }

    public static void incrementNumberOfCurrentThreads() {
        if (getNumberOFCurrentThreads() < 3) {
            ++numberOfCurrentThreads;
        }
    }

    public static int getNumberOFCurrentThreads() {
        synchronized (obj) {
            return numberOfCurrentThreads;
        }
    }

    public int[] popStack() {
        return (int[])this.stack.pop();
    }

    @Override
    public void run() {
        solutionView.createResultPath(this.maze);
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
                if (getNumberOFCurrentThreads() < 3) {
                    Thread t1;
                    synchronized (obj) {
                        ThreadManagement tm1 = new ThreadManagement(maze, x, y, solutionView);
                        t1 = new Thread(tm1);
                        incrementNumberOfCurrentThreads();
                    }
                    t1.start();
                    maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    run();
                } else {
                    int[] a = {x, y+1};
                    stack.push(a);
                    maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    run();
                }
                break;
            case 3:
                maze.visit(++x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                run();
                break;
            case 4:
                maze.visit(x, ++y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                run();
                break;
            default:
                if (!this.stack.isEmpty()) {
                    int[] a = popStack();
                    this.x = a[0];
                    this.y = a[1];
                    if(this.maze.maze[x][y] == '0') {
                        maze.visit(x, y, Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    run();
                }
                break;
        }
    }
}