package ScrumPlay_FrontEnd;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.swing.Timer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class ScrumBoard extends JFrame {
    public Map<String, Map<String, String>> userData;
    public JComboBox<String> userDropdown;
    public JPanel boardPanel;
    public JTextArea updateArea;
    private Map<String, String> playerNameToIdMap;

    public ScrumBoard() {

        String[] x = fetchPlayerDataAndPopulateDropdown("http://localhost:8080/players");


        // Set up the main frame
        setTitle("Scrum Board");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0,255,255));// Set background color

        //userDropdown = new JComboBox<>(userData.keySet().toArray(new String[0]));

        try {
            // Send a GET request to the backend API to fetch the players
            URL url = new URL("http://localhost:8080/players");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONArray players = new JSONArray(response.toString());

            for (int i = 0; i < players.length(); i++) {
                JSONObject player = players.getJSONObject(i);
                String item = "x";
                userDropdown.addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        boardPanel = createBoardPanel();
        add(userDropdown, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        JPanel updatePanel = createUpdatePanel();
        add(updatePanel, BorderLayout.SOUTH);

        final int[] countdown = {10 * 60};

        JLabel countdownLabel = new JLabel();
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countdown[0]--; // Decrease the countdown value

                int minutes = countdown[0] / 60;
                int seconds = countdown[0] % 60;

                countdownLabel.setText(String.format("Countdown: %02d:%02d", minutes, seconds));

                // If countdown reaches 0, stop the timer and show a message
                if (countdown[0] <= 0) {
                    ((Timer)e.getSource()).stop();
                    endScrumCall();
                }
            }
        });

        JButton startScrumCallButton = new JButton("Start Scrum Call");
        startScrumCallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the timer when the button is clicked
                timer.start();
            }
        });

        JButton endScrumButton = new JButton("End Scrum Call");
        endScrumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                endScrumCall();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(countdownLabel);
        bottomPanel.add(startScrumCallButton);
        bottomPanel.add(endScrumButton);

        add(bottomPanel, BorderLayout.PAGE_END);

        // Initial board update
        updateBoard();
    }

    private String[] fetchPlayerDataAndPopulateDropdown(String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        List<Map<String, Object>> playerData = parseJsonResponse(jsonResponse);

        String[] playerNames = new String[0];
        playerNameToIdMap = new HashMap<>();

        if (playerData != null) {
            playerNames = extractPlayerNames(playerData);
            userDropdown = new JComboBox<>(playerNames);

            // Populate playerNameToIdMap
            for (Map<String, Object> player : playerData) {
                String playerName = player.get("playerName").toString();
                String playerId = player.get("playerid").toString();
                playerNameToIdMap.put(playerName, playerId);
            }

            userDropdown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Fetch user story details for the selected player
                    String selectedPlayerName = (String) userDropdown.getSelectedItem();
                    String selectedPlayerId = playerNameToIdMap.get(selectedPlayerName);
                    fetchUserStoryDetails(selectedPlayerId);

                    // Update the board
                    updateBoard();
                }
            });

            return playerNames;
        } else {
            // Handle the case where data parsing fails or is empty
            JOptionPane.showMessageDialog(null, "Failed to fetch player data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return playerNames;
    }

    private void fetchUserStoryDetails(String selectedPlayer) {
        // Assuming you have an API to fetch user story details based on the selected player
        String userStoryDetailsUrl = "http://localhost:8080/player-stories/" + selectedPlayer;

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(userStoryDetailsUrl, String.class);

        // Parse the user story details and update userData map
        List<Map<String, Object>> userStoryData = parseUserStoryDetails(jsonResponse);

        if (userStoryData != null) {
            Map<String, String> userStoryMap = new HashMap<>();
            for (Map<String, Object> userStory : userStoryData) {
                userStoryMap.put(userStory.get("userStoryName"), userStory.get("status"));
                System.out.println(userStory.get("storyTitle"));
            }

            userData.put(selectedPlayer, userStoryMap);
        } else {
            // Handle the case where data parsing fails or is empty
            JOptionPane.showMessageDialog(null, "Failed to fetch user story details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Map<String, Object>> parseUserStoryDetails(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Map<String, Object>> userStoryDetails = objectMapper.readValue(
                    jsonResponse,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            return userStoryDetails;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    private String[] extractPlayerNames(List<Map<String, Object>> playerData) {
        // Assuming 'playerName' is the key for player names in the JSON response
        return playerData.stream()
                .map(player -> player.get("playerName").toString())
                .toArray(String[]::new);
    }

    public JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(1, 3));

        for (String lane : new String[]{"To Do", "In Progress", "Done"}) {
            JPanel lanePanel = new JPanel();
            lanePanel.setLayout(new BoxLayout(lanePanel, BoxLayout.Y_AXIS));
            lanePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), lane));
            lanePanel.setBackground(new Color(255, 255, 224)); // Set lane background color

            boardPanel.add(lanePanel);
        }

        return boardPanel;
    }

    public void updateBoard() {
        String selectedUser = (String) userDropdown.getSelectedItem();
        if (selectedUser != null) {
//            Map<String, String> userStories = userData.get(selectedUser);
//            clearBoard();
//
//            for (Map.Entry<String, String> entry : userStories.entrySet()) {
//                String userStory = entry.getKey();
//                String status = entry.getValue();
//
//                JPanel cardPanel = createCardPanel(userStory, status);
//                addToLanePanel(status, cardPanel);
//            }
        }
    }

    public JPanel createCardPanel(String userStory, String status) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEtchedBorder());
        cardPanel.setBackground(Color.RED); // Set card background color
        cardPanel.setPreferredSize(new Dimension(800, 200)); // Set fixed size

        JLabel userStoryLabel = new JLabel(userStory);
        userStoryLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        userStoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(userStoryLabel);

        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton updateStatus = new JButton("Update Status");
        updateStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show daily status input dialog
                showStatusInputDialog(userStory);
            }
        });

        JPanel flipPanel = new JPanel();
        flipPanel.setLayout(new BoxLayout(flipPanel, BoxLayout.Y_AXIS));
        flipPanel.setBorder(BorderFactory.createEtchedBorder());
        flipPanel.setBackground(Color.GREEN); // Set card background color
        flipPanel.setPreferredSize(new Dimension(800, 200));
        flipPanel.setVisible(false);

        JLabel flippedUserStoryLabel = new JLabel(userStory);
        flippedUserStoryLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        flippedUserStoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipPanel.add(flippedUserStoryLabel);

        JLabel storyPoints = new JLabel("Story Points");
        storyPoints.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        storyPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipPanel.add(storyPoints);

        JLabel description = new JLabel("Description");
        description.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipPanel.add(description);

        JLabel acceptanceCriteria = new JLabel("Acceptance Criteria");
        acceptanceCriteria.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        acceptanceCriteria.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipPanel.add(acceptanceCriteria);

        JLabel todayUpdate = new JLabel("Today's Update");
        todayUpdate.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
        todayUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
        flipPanel.add(todayUpdate);

        JButton provideUpdate = new JButton("Provide Update");
        provideUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
        provideUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show daily status input dialog
                cardPanel.setVisible(!cardPanel.isVisible());
                flipPanel.setVisible(!flipPanel.isVisible());
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show daily status input dialog
                cardPanel.setVisible(!cardPanel.isVisible());
                flipPanel.setVisible(!flipPanel.isVisible());
            }
        });
        flipPanel.add(backButton);
        cardPanel.add(updateStatus);
        cardPanel.add(provideUpdate);

        JPanel container = new JPanel();
        container.add(cardPanel);
        container.add(flipPanel);

        return container;
    }

    public void addToLanePanel(String status, JPanel cardPanel) {
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

    public void clearBoard() {
        Component[] lanePanels = boardPanel.getComponents();
        for (Component lanePanel : lanePanels) {
            ((JPanel) lanePanel).removeAll();
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public JPanel createUpdatePanel() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BorderLayout());
        updatePanel.setBackground(new Color(240, 240, 240)); // Set background color

        // Update area
        updateArea = new JTextArea();
        updateArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(updateArea);
        updatePanel.add(scrollPane, BorderLayout.CENTER);

        return updatePanel;
    }

    public String getUpdateForUserStory(String userStory) {

        return "Update for " + userStory + ": Today's progress...";
    }

    public void showStatusInputDialog(String userStory) {
        JTextArea statusInput = new JTextArea();
        statusInput.setLineWrap(true);
        statusInput.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(statusInput);

        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Enter Daily Status for " + userStory,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String dailyStatus = statusInput.getText();
            updateArea.setText("Daily Status for " + userStory + ":\n" + dailyStatus);
        }
    }

    public void endScrumCall() {
        JOptionPane.showMessageDialog(this, "Scrum Call Ended!", "Scrum Ended", JOptionPane.INFORMATION_MESSAGE);
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
