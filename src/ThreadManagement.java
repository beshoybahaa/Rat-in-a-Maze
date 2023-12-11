import java.util.Stack;

public class ThreadManagement extends Thread {
    Stack<int[]> stack = new Stack();
    private static int numberOfCurrentThreads = 0;

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

    public void run() {
    }

    public int[] popStack() {
        return (int[])this.stack.pop();
    }
}