import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResultView extends JPanel {
    private final JButton[][] grid;
    private final Maze maze;
    private final int n;
    private boolean found;

    public ResultView(Maze maze) {
        this.maze = maze;
        this.n = this.maze.maze[0].length;
        grid = new JButton[n][n];
        this.setLayout(new GridLayout(n, n));
        this.setBackground(new Color(200, 200, 200));
        buildResultGrid();
    }

    private void buildResultGrid() {
        ActionListener buttonListener = evt -> {
            JButton selectedBtn = (JButton) evt.getSource();
            for (JButton[] jButtons : grid) {
                for (JButton jButton : jButtons) {
                    if (jButton == selectedBtn) {
                        if (grid[0][0] == selectedBtn) {
                            JOptionPane.showMessageDialog(null, "Start new game?");
                            ResultView.this.removeAll();
                            revalidate();
                            repaint();
                            new StartFrame();
                            JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                            terminate.dispose();
                        } else if (grid[n-1][n-1] == selectedBtn) {
                            JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                            terminate.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, found ? "Green path is the solution!" : "No path found!");
                        }
                    }
                }
            }
        };
        // Build Solution Path
        createResultPath(buttonListener);
    }

    private void createResultPath(ActionListener buttonListener) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                // Init all squares text
                grid[x][y] = new JButton("");
                // Check square state
                if (maze.maze[x][y] == '1') { // Block
                    grid[x][y].setBackground(new Color(0, 0, 0));
                    grid[x][y].setForeground(new Color(0, 0, 0));
                } else if (maze.maze[x][y] == '2') { // Start or End
                    if( x == 0 && y== 0 ) {
                        grid[x][y] = new JButton("Start");
                        grid[x][y].setBackground(new Color(0, 150, 120));
                        grid[x][y].setForeground(new Color(255, 255, 255));
                    } else {
                        grid[x][y] = new JButton("End");
                        colorSquares(x, y);
                    }
                } else if (maze.maze[x][y] == '3') {
                    colorSquares(x, y);
                } else { // Empty
                    grid[x][y].setBackground(new Color(255, 255, 255));
                    grid[x][y].setForeground(new Color(0, 0, 0));
                }
                grid[x][y].addActionListener(buttonListener);
                this.add(grid[x][y]);

            }
        }
    }

    private void colorSquares(int x, int y) {
        switch (this.maze.threadid[x][y]) {
            case 0:
                grid[x][y].setBackground(new Color(255, 0, 0));
                grid[x][y].setForeground(new Color(255, 0, 0));
                break;
            case 1:
                grid[x][y].setBackground(new Color(81, 1, 178));
                grid[x][y].setForeground(new Color(81, 1, 178));
                break;
            case 2:
                grid[x][y].setBackground(new Color(247, 255, 0));
                grid[x][y].setForeground(new Color(247, 255, 0));
                break;
            case 3:
                grid[x][y].setBackground(new Color(0, 255, 0));
                grid[x][y].setForeground(new Color(0, 255, 0));
                break;
            default:
                grid[x][y].setBackground(new Color(255, 0, 234));
                grid[x][y].setForeground(new Color(0, 255, 0));
                break;
        }
    }

}
