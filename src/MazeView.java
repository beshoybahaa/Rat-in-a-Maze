import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MazeView extends JPanel {
    private final JButton[][] grid;
    private final char[][] blocks;
    private final int n;

    public MazeView(int n) {
        this.n = n;
        blocks = new char[n][n];
        for (char[] arr1 : blocks)
            Arrays.fill(arr1, '0');
        blocks[0][0] = '2';
        blocks[n - 1][n - 1] = '2';
        grid = new JButton[n][n];
        this.setLayout(new GridLayout(n, n));
        this.setBackground(new Color(255, 255, 255));
        buildGrid();
    }

    private void buildGrid() {
        ActionListener buttonListener = evt -> {
            JButton selectedBtn = (JButton) evt.getSource();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == selectedBtn) {
                        if (grid[n-1][n-1] == selectedBtn) {
                            // End button clicked
                            JOptionPane.showMessageDialog(null, "Select the blocks squares, Then click Start)");
                        } else if (grid[0][0] == selectedBtn) {
                            // Start button clicked (Start solving the maze)
                            MazeView.this.removeAll();
                            revalidate();
                            repaint();
                            try {
                                new ResultFrame(blocks);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                            terminate.dispose();
                        } else {
                            // Square is clicked (Make it a block)
                            blocks[row][col] = '0';
                            grid[row][col].setBackground(new Color(0, 0, 0));
                            grid[row][col].setForeground(new Color(255, 255, 255));
                        }
                    }
                }
            }
        };
        createButtons(buttonListener);
    }

    private void createButtons(ActionListener buttonListener) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (x == y && (x == 0)) {
                    grid[x][y] = new JButton("Start");
                    grid[x][y].setBackground(new Color(50, 150, 120));
                    grid[x][y].setForeground(new Color(255, 255, 255));
                } else if (x == y && (x == n - 1)) {
                    grid[x][y] = new JButton("End");
                    grid[x][y].setBackground(new Color(0, 150, 120));
                    grid[x][y].setForeground(new Color(255, 255, 255));
                } else {
                    grid[x][y] = new JButton("");
                    grid[x][y].setBackground(new Color(255, 255, 255));
                    grid[x][y].setForeground(new Color(0, 0, 0));
                }
                grid[x][y].addActionListener(buttonListener);
                this.add(grid[x][y]);
            }
        }
    }
}