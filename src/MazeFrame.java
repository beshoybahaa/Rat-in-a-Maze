import javax.swing.*;
import java.awt.*;

public class MazeFrame extends JFrame{
    public MazeFrame(int n) {
        this.setTitle("Rat In The Maze");
        this.setExtendedState(getExtendedState());
        this.setResizable(true);
        this.setVisible(true);
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Show Maze view
        MazeView gridView = new MazeView(n);
        this.add(gridView);
    }
}
