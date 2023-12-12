import java.util.Stack;

public class ThreadManagement implements Runnable {

    // enter the number of N from GUI
    Maze maze ;
    int x;
    int y;
    Stack<int[]> stack = new Stack();
    private static int numberOfCurrentThreads = 0;

    public ThreadManagement(Maze maze , int x , int y) {
        this.maze = maze;
        this.x=x;
        this.y=y;
    }

    public static int getNumberOfCurrentThreads() {
        return numberOfCurrentThreads;
    }

    public static void incrementNumberOfCurrentThreads(Object obj) {
        synchronized(obj) {
            if (numberOfCurrentThreads < 4) {
                ++numberOfCurrentThreads;
            }
        }
    }

    public static void decrementNumberOfCurrentThreads(Object obj) {
        synchronized(obj) {
            if (numberOfCurrentThreads > 0) {
                --numberOfCurrentThreads;
            }
        }
    }

    @Override
    public void run() {
        int actionNumber = maze.checkNext(x,y);
        switch (actionNumber){
            case 0:
                maze.visit(++x,y,Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                break;
            case 1:
                maze.visit(x,++y,Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                break;
            case 2:
                if(numberOfCurrentThreads!=4){
                    ThreadManagement tm1 = new ThreadManagement(maze,x,++y);
                    Thread t1 = new Thread(tm1);
                    t1.start();
                    break;
                }else{
                    int[] a = {++x,y};
                    stack.push(a);
                    maze.visit(x,++y,Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                    run();
                }
                break;
            case 3:
                maze.visit(++x,y,Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                run();
                break;
            case 4:
                maze.visit(x,++y,Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                run();
                break;
            default:
                int[] a = popStack();
                maze.visit(a[0],a[1],Integer.parseInt(String.valueOf(Thread.currentThread().getId())));
                run();
                break;
        }
    }

    public int[] popStack() {
        return (int[])this.stack.pop();
    }
}