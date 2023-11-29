package ScrumPlay_FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreDisplay extends JFrame {
    public ScoreDisplay() {
        setTitle("Leaderboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton showScoresButton = new JButton("Show Scores");
        showScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScoresPage();
            }
        });

        setLayout(new BorderLayout());

        add(showScoresButton, BorderLayout.CENTER);
    }

    private void showScoresPage() {
        SwingUtilities.invokeLater(() -> {
            ShowScoresPage showScoresPage = new ShowScoresPage(this);
            showScoresPage.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScoreDisplay scoreDisplay = new ScoreDisplay();
            scoreDisplay.setVisible(true);
        });
    }
}
