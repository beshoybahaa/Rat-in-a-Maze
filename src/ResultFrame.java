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
        this.add(solutionView);
    }
}
