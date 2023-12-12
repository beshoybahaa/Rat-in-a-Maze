import java.awt.*;
import javax.swing.*;

public class StartFrame extends JFrame {

    private final StartView startView = new StartView();

    public StartFrame() {
        this.setTitle("Rat In The Maze ");
        this.setExtendedState(getExtendedState());
        this.setResizable(false);
        this.setSize(450, 400);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocation(0, 0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(startView);
    }

}
