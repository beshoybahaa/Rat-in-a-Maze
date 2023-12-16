import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {
    public ResultFrame(Maze maze) throws InterruptedException {
        this.setTitle("Rat In The Maze");
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ResultView solutionView = new ResultView(maze);
        ThreadManagement tm1 = new ThreadManagement(maze,0,0, solutionView);
        Thread t1 = new Thread(tm1);
        t1.start();
        this.add(solutionView);
    }
}
