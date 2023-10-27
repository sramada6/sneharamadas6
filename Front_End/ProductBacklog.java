import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ProductBacklog extends JFrame {
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private JPanel detailPanel;
    private Map<String, String> userStoryDescriptions;
    private JTextField userStoryPointsField;
    private JComboBox<String> playerDropdown;

    public ProductBacklog() {
        setTitle("Product Backlog");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        listModel = new DefaultListModel<>();
        listModel.addElement("US#001/Create Landing Page");
        listModel.addElement("US#002/Follow up for Scrum Intro");
        listModel.addElement("US#003/Create Database");
        listModel.addElement("US#004/Design game configuration page");
        listModel.addElement("US#005/Display previous game scores and sprint history");

        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScrollPane = new JScrollPane(userList);
        listScrollPane.setPreferredSize(new Dimension(200, 0));

        detailPanel = new JPanel(new BorderLayout());
        mainPanel.add(listScrollPane, BorderLayout.WEST);
        mainPanel.add(detailPanel, BorderLayout.CENTER);

        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUserStory = userList.getSelectedValue();
                showUserStoryDetails(selectedUserStory);
            }
        });

        userStoryDescriptions = new HashMap<>();
        userStoryDescriptions.put("US#001/Create Landing Page", "This is the description for Create Landing Page.");
        userStoryDescriptions.put("US#002/Follow up for Scrum Intro", "This is the description for Follow up for Scrum Intro.");
        userStoryDescriptions.put("US#003/Create Database", "This is the description for Create Database.");
        userStoryDescriptions.put("US#004/Design game configuration page", "This is the description for Design game configuration page.");
        userStoryDescriptions.put("US#005/Display previous game scores and sprint history", "This is the description for Display previous game scores and sprint history.");
    }

    private void showUserStoryDetails(String userStory) {
        detailPanel.removeAll();

        JPanel userStoryInfoPanel = new JPanel(new GridLayout(1, 2));
        JPanel pointsDescriptionPanel = new JPanel(new BorderLayout());

        JPanel assignmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        playerDropdown = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"});
        assignmentPanel.add(new JLabel("Assign to:"));
        assignmentPanel.add(playerDropdown);

        userStoryPointsField = new JTextField(10);
        JPanel pointsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pointsPanel.add(new JLabel("User Story Points: "));
        pointsPanel.add(userStoryPointsField);

        JLabel userStoryLabel = new JLabel("User Story: " + userStory);

        String description = userStoryDescriptions.get(userStory);
        JLabel userStoryDescription = new JLabel("User Story Description:" + description);

        userStoryInfoPanel.add(userStoryLabel);
        userStoryInfoPanel.add(assignmentPanel);

        pointsDescriptionPanel.add(pointsPanel, BorderLayout.WEST);
        pointsDescriptionPanel.add(userStoryDescription, BorderLayout.CENTER);

        detailPanel.add(userStoryInfoPanel, BorderLayout.NORTH);
        detailPanel.add(pointsDescriptionPanel, BorderLayout.CENTER);
        detailPanel.revalidate();
        detailPanel.repaint();
    }
}
