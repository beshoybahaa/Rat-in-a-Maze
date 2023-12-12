import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartView extends JPanel {
    private final JLabel sizeLabel = new JLabel("Enter size of the maze (n)");
    private final JTextField sizeTextField = new JTextField();
    private final JButton startBtn = new JButton("Start the Maze");

    public StartView() {
        this.setLayout(null);
        this.setBackground(new Color(201, 142, 254));
        this.setSize(500, 400);
        this.add(sizeLabel);
        this.add(sizeTextField);
        this.add(startBtn);
        // Font
        sizeLabel.setFont(new Font("", Font.BOLD, 25));
        sizeTextField.setFont(new Font("", Font.PLAIN, 20));
        startBtn.setFont(new Font("", Font.ITALIC, 25));
        // Bounds
        sizeLabel.setBounds(70, 100, 350, 30);
        sizeTextField.setBounds(50, 150, 350, 40);
        startBtn.setBounds(50, 200, 350, 50);
        // Colors
        startBtn.setBackground(new Color(0, 0, 0));
        startBtn.setForeground(new Color(255, 255, 255));

        startBtn.addActionListener(new onButtonClick());
    }

    private class onButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                try {
                    if (!sizeTextField.getText().isEmpty()) {
                        // Size value
                        int n = Integer.parseInt(sizeTextField.getText());
                        // Check if the entered size is 4 or more
                        if (n >=4) {
                            StartView.this.removeAll();
                            revalidate();
                            repaint();
                            new MazeFrame(n);
                            JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                            terminate.dispose();
                        } else {
                            // Show error in case of size less than or equal 4
                            JOptionPane.showMessageDialog(null, "Size should be 4 or more!");
                        }
                    } else {
                        // Show error in case of size value is empty
                        JOptionPane.showMessageDialog(null, "Maze size is required!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}
