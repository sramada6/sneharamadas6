import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ScrumBoard extends JFrame {
    private Map<String, Map<String, String>> userData;
    private JComboBox<String> userDropdown;
    private JPanel boardPanel;
    private JTextArea updateArea;

    public ScrumBoard() {
        // Initialize user data
        userData = new HashMap<>();

        // Create sample user data
        Map<String, String> user1Data = new HashMap<>();
        user1Data.put("UserStory1", "To Do");
        user1Data.put("UserStory2", "In Progress");
        user1Data.put("UserStory3", "Done");

        Map<String, String> user2Data = new HashMap<>();
        user2Data.put("UserStory4", "To Do");
        user2Data.put("UserStory5", "Done");

        userData.put("User1", user1Data);
        userData.put("User2", user2Data);

        // Set up the main frame
        setTitle("JIRA Board");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create user dropdown
        userDropdown = new JComboBox<>(userData.keySet().toArray(new String[0]));
        userDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBoard();
            }
        });

        // Create the board panel
        boardPanel = createBoardPanel();
        add(userDropdown, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        // Create the update panel
        JPanel updatePanel = createUpdatePanel();
        add(updatePanel, BorderLayout.SOUTH);

        // Initial board update
        updateBoard();
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(1, 3));

        for (String lane : new String[]{"To Do", "In Progress", "Done"}) {
            JPanel lanePanel = new JPanel();
            lanePanel.setLayout(new BoxLayout(lanePanel, BoxLayout.Y_AXIS));
            lanePanel.setBorder(BorderFactory.createTitledBorder(lane));

            boardPanel.add(lanePanel);
        }

        return boardPanel;
    }

    private void updateBoard() {
        String selectedUser = (String) userDropdown.getSelectedItem();
        if (selectedUser != null) {
            Map<String, String> userStories = userData.get(selectedUser);
            clearBoard();

            for (Map.Entry<String, String> entry : userStories.entrySet()) {
                String userStory = entry.getKey();
                String status = entry.getValue();

                JPanel cardPanel = createCardPanel(userStory, status);
                addToLanePanel(status, cardPanel);
            }
        }
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

        // Flip card button
        JButton flipButton = new JButton("Flip Card");
        flipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show update in the update area
                updateArea.setText(getUpdateForUserStory(userStory));
            }
        });
        cardPanel.add(flipButton);

        return cardPanel;
    }

    private void addToLanePanel(String status, JPanel cardPanel) {
        Component[] lanePanels = boardPanel.getComponents();
        switch (status) {
            case "To Do":
                ((JPanel) lanePanels[0]).add(cardPanel);
                break;
            case "In Progress":
                ((JPanel) lanePanels[1]).add(cardPanel);
                break;
            case "Done":
                ((JPanel) lanePanels[2]).add(cardPanel);
                break;
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void clearBoard() {
        Component[] lanePanels = boardPanel.getComponents();
        for (Component lanePanel : lanePanels) {
            ((JPanel) lanePanel).removeAll();
        }
        boardPanel.revalidate();
        boardPanel.repaint();
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
