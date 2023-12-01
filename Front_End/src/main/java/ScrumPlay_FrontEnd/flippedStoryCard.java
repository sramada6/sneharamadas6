package ScrumPlay_FrontEnd;

import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class flippedStoryCard extends JPanel {
    private final String title;
    private final String description;
    private final String status;
    private final String assignedTo;
    private final String storyPoints;
    private final String storyid;
    private JComboBox<String> statusComboBox;
    private JComboBox<String> assignedToComboBox;

    private JComboBox<String> storyPointsComboBox;

    private JTextArea descriptionArea;

    //JButton backButton = new JButton("Back");
    ScrumBoard scrumBoard;
    StoryCard correspondingStoryCard;

    public flippedStoryCard(String storyid, String title, String description, String status, String assignedTo, String storyPoints, ScrumBoard scrumBoard, StoryCard correspondingStoryCard) {
        this.title = title;
        this.storyid = storyid;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.storyPoints = storyPoints;
        this.scrumBoard = scrumBoard;
        this.correspondingStoryCard = correspondingStoryCard;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(350, 150));
        setVisible(false);

        // Create a JLabel to display the title
        JLabel idLabel = new JLabel("#" + storyid);
        idLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));

        add(idLabel,BorderLayout.LINE_START);
        // Add the title label to the StoryCard
        add(titleLabel, BorderLayout.NORTH);

        // Create JTextAreas for description and comments
        descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);


        // Add the JTextAreas to the StoryCard
        add(descriptionArea, BorderLayout.CENTER);

        // Fetch values from the backend and create JComboBoxes
        statusComboBox = new JComboBox<>(new String[]{"Ready", "In Progress", "Completed"});
        List<String> playerNamesList = fetchPlayerData("http://localhost:8080/players");
        assignedToComboBox = new JComboBox<>(playerNamesList.toArray(new String[0]));
        storyPointsComboBox = new JComboBox<>(new String[]{"1", "2", "3", "5", "8"});

        // Set the initial values
        statusComboBox.setSelectedItem(status);
        String combinedName = parsePlayerInfo(assignedTo);
        assignedToComboBox.setSelectedItem(combinedName);
        storyPointsComboBox.setSelectedItem(storyPoints);


        // Create a JPanel to hold the JComboBoxes
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1, 3));
        comboBoxPanel.add(statusComboBox);
        comboBoxPanel.add(assignedToComboBox);
        comboBoxPanel.add(storyPointsComboBox);

        // Add the JComboBox panel to the StoryCard
        add(comboBoxPanel, BorderLayout.EAST);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new StoryCardMouseListener());

/*        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (scrumBoard != null) {
                    scrumBoard.flipCard(correspondingStoryCard, flippedStoryCard.this);
                }
            }
        });
        add(backButton);*/
    }

    public void setCorrespondingStoryCard(StoryCard correspondingStoryCard) {
        this.correspondingStoryCard = correspondingStoryCard;
    }

    public String getStoryId() {
        return  storyid;
    }

    public String getStatusComboBox() {
        return statusComboBox.getSelectedItem().toString();
    }

    public String getAssignedToComboBox() {
        String assigned = assignedToComboBox.getSelectedItem().toString();
        String[] parts = assigned.split("#");
        return parts[0];
    }

    public String getStoryPointsComboBox() {
        return storyPointsComboBox.getSelectedItem().toString();
    }


    private class StoryCardMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed data called");
            JComponent component = (JComponent) e.getSource();
            System.out.println("component data called");
            TransferHandler handler = component.getTransferHandler();
            System.out.println("handler data called");
            handler.exportAsDrag(component, e, TransferHandler.MOVE);
            System.out.println("exportAsDrag data called");
        }
    }




    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return descriptionArea.getText();
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    private List<String> fetchPlayerData(String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        List<Map<String, Object>> playerData = parseJsonResponse(jsonResponse);

        List<String> playerNamesList = new ArrayList<>();

        if (playerData != null) {
            int i = 0;
            for (Map<String, Object> player : playerData) {
                String playerName = player.get("playerName").toString();
                String playerId = player.get("playerid").toString();
                String combinedName = playerId + "#" + playerName;
                playerNamesList.add(combinedName);
            }
            return playerNamesList;
        } else {
            // Handle the case where data parsing fails or is empty
            JOptionPane.showMessageDialog(null, "Failed to fetch player data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return playerNamesList;
    }

    private List<Map<String, Object>> parseJsonResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String parsePlayerInfo(String playerInfoString) {
        // Remove curly braces and split by commas
        String[] keyValuePairs = playerInfoString.replaceAll("[{}]", "").split(", ");

        // Create a map to store key-value pairs
        Map<String, String> playerInfoMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            if (entry.length == 2) {
                playerInfoMap.put(entry[0], entry[1]);
            }
        }

        // Fetch playerid and playerName from the map
        String playerId = playerInfoMap.get("playerid");
        String playerName = playerInfoMap.get("playerName");

        // Combine playerid and playerName
        return playerId + "#" + playerName;
    }
}


