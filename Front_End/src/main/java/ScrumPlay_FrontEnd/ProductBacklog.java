package ScrumPlay_FrontEnd;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ProductBacklog extends JFrame {
    private static final long serialVersionUID = 1L;
    public JList<String> userList;
    private DefaultListModel<String> listModel;
    public JPanel detailPanel;
    private Map<String, String> userStoryDescriptions;
    private JComboBox<String> userStoryPointsField; // Updated to JComboBox
    private JComboBox<String> playerDropdown;
    private JComboBox<String> statusDropdown;
    private JTextArea commentsTextArea;

    private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().setForeground(new Color(128, 0, 0));
		frame.getContentPane().setBackground(new Color(0, 206, 209));
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 908, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public ProductBacklog() {
        setTitle("Product Backlog");
        initialize();
        

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
        userStoryDescriptions.put("US#001/Create Landing Page", "As a developer, I want to design an informative page for the trainees which contains the basic details of scrum and the vocabulary used, such as sprint, sprint backlog, burndown charts and sprint velocity. The page displays a one-line definition of each term followed by a link to the full description.");
        userStoryDescriptions.put("US#002/Follow up for Scrum Intro", "As a developer, I want to design a webpage which is a follow up for the landing page when the \"see more..\" button is clicked. This page would provide detailed information including a tutorial for the concepts such as sprint, sprint backlog, etc.");
        userStoryDescriptions.put("US#003/Create Database", "As a developer, I want to design a database which can store all the information regarding ScrumPlay which includes the game configuration, problem statement, user stories, etc. This can be used to fetch details when required so that it can be displayed in the front-end of ScrumPlay.");
        userStoryDescriptions.put("US#004/Design game configuration page", "As a developer, I want to create a set game configuration page where the user can set parameters such as team size, length of sprint and length of scrum call. The user can also decide the roles of the players, such as Product Owner, Scrum Master and developers.");
        userStoryDescriptions.put("US#005/Display previous game scores and sprint history", "As a developer, I want to display previous game scores and sprint charts so that the user can have an idea what all values are expected and also to know about the history of the previous games.");
    }

    private void showUserStoryDetails(String userStory) {
        detailPanel.removeAll();

        JPanel userStoryInfoPanel = new JPanel();
        userStoryInfoPanel.setLayout(new BoxLayout(userStoryInfoPanel, BoxLayout.PAGE_AXIS));

        JLabel userStoryLabel = new JLabel("User Story: " + userStory);
        userStoryLabel.setToolTipText(userStoryDescriptions.get(userStory));
        Font boldFont = new Font(userStoryLabel.getFont().getName(), Font.BOLD, userStoryLabel.getFont().getSize());
        userStoryLabel.setFont(boldFont);

        // Panel for status and assign to
        JPanel statusAssignPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusDropdown = new JComboBox<>(new String[]{"New", "Ready", "In Progress", "Ready for Test", "Closed"});
        playerDropdown = new JComboBox<>(new String[]{"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"});

        userStoryPointsField = new JComboBox<>(new String[]{"1", "2", "3", "5"});
        userStoryPointsField.setSelectedIndex(0);

        statusAssignPanel.add(new JLabel("Status:"));
        statusAssignPanel.add(statusDropdown);
        statusAssignPanel.add(new JLabel("Assign to:"));
        statusAssignPanel.add(playerDropdown);
        statusAssignPanel.add(new JLabel("User Story Points: "));
        statusAssignPanel.add(userStoryPointsField);

        // Panel for user story description
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JTextArea descriptionTextArea = new JTextArea(5, 20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setText(userStoryDescriptions.get(userStory));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionPanel.add(new JLabel("User Story Description:"), BorderLayout.NORTH);
        descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        // Panel for comments
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsTextArea = new JTextArea(5, 20);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);
        commentsTextArea.setEditable(true);
        JScrollPane commentsScrollPane = new JScrollPane(commentsTextArea);
        commentsPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);
        commentsPanel.add(commentsScrollPane, BorderLayout.CENTER);

        userStoryInfoPanel.add(userStoryLabel);
        userStoryInfoPanel.add(statusAssignPanel);
        userStoryInfoPanel.add(descriptionPanel);
        userStoryInfoPanel.add(commentsPanel);

        // Panel for the "End Sprint Planning" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton endSprintButton = new JButton("End Sprint Planning");
        endSprintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle button action here
            }
        });
        buttonPanel.add(endSprintButton);

        detailPanel.add(userStoryInfoPanel, BorderLayout.CENTER);
        detailPanel.add(buttonPanel, BorderLayout.SOUTH);
        detailPanel.revalidate();
        detailPanel.repaint();
    }
}
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProductBacklog frame = new ProductBacklog();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}