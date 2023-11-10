package ScrumPlay_FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ScrumBoard extends JFrame {
    private Map<String, String> userStories;
    private JTextArea updateArea;

    public ScrumBoard() {
        // Initialize user stories
        userStories = new HashMap<>();
        userStories.put("UserStory1", "To Do");
        userStories.put("UserStory2", "In Progress");
        userStories.put("UserStory3", "Done");

        // Set up the main frame
        setTitle("Scrum Board");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the scrum board panel
        JPanel scrumBoardPanel = createScrumBoardPanel();
        add(scrumBoardPanel, BorderLayout.CENTER);

        // Create the update panel
        JPanel updatePanel = createUpdatePanel();
        add(updatePanel, BorderLayout.SOUTH);
    }

    private JPanel createScrumBoardPanel() {
        JPanel scrumBoardPanel = new JPanel(new GridLayout(1, userStories.size()));

        for (Map.Entry<String, String> entry : userStories.entrySet()) {
            String userStory = entry.getKey();
            String status = entry.getValue();

            JPanel cardPanel = createCardPanel(userStory, status);
            scrumBoardPanel.add(cardPanel);
        }

        return scrumBoardPanel;
    }

    private JPanel createCardPanel(String userStory, String status) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEtchedBorder());

        // User story label
        JLabel userStoryLabel = new JLabel(userStory);
        userStoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userStoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(userStoryLabel);

        // Status label
        JLabel statusLabel = new JLabel(status);
        statusLabel.setForeground(getStatusColor(status));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(statusLabel);

        // Update button
        JButton updateButton = new JButton("Update");
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show update in the update area
                updateArea.setText(getUpdateForUserStory(userStory));
            }
        });
        cardPanel.add(updateButton);

        return cardPanel;
    }

    private Color getStatusColor(String status) {
        switch (status) {
            case "To Do":
                return Color.RED;
            case "In Progress":
                return Color.ORANGE;
            case "Done":
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }

    private JPanel createUpdatePanel() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BorderLayout());

        // Update area
        updateArea = new JTextArea();
        updateArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(updateArea);
        updatePanel.add(scrollPane, BorderLayout.CENTER);

        return updatePanel;
    }

    private String getUpdateForUserStory(String userStory) {
        // In a real application, you would fetch the update from the database or some other source
        // For simplicity, using dummy data here
        return "Update for " + userStory + ": Today's progress...";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScrumBoard().setVisible(true);
            }
        });
    }
}
