package ScrumPlay_FrontEnd;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//import javax.swing.Timer;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.web.client.RestTemplate;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//public class ScrumBoard extends JFrame {
//    public Map<String, Map<String, String>> userData;
//    public JComboBox<String> userDropdown;
//    public JPanel boardPanel;
//    public JTextArea updateArea;
//    private Map<String, String> playerNameToIdMap;
//
//    public ScrumBoard() {
//
//        String[] x = fetchPlayerDataAndPopulateDropdown("http://localhost:8080/players");
//
//
//        // Set up the main frame
//        setTitle("Scrum Board");
//        setSize(1200, 800);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        getContentPane().setBackground(new Color(0,255,255));// Set background color
//
//        //userDropdown = new JComboBox<>(userData.keySet().toArray(new String[0]));
//
//        try {
//            // Send a GET request to the backend API to fetch the players
//            URL url = new URL("http://localhost:8080/players");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            conn.disconnect();
//
//            JSONArray players = new JSONArray(response.toString());
//
//            for (int i = 0; i < players.length(); i++) {
//                JSONObject player = players.getJSONObject(i);
//                String item = "x";
//                userDropdown.addItem(item);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        boardPanel = createBoardPanel();
//        add(userDropdown, BorderLayout.NORTH);
//        add(boardPanel, BorderLayout.CENTER);
//
//        JPanel updatePanel = createUpdatePanel();
//        add(updatePanel, BorderLayout.SOUTH);
//
//        final int[] countdown = {10 * 60};
//
//        JLabel countdownLabel = new JLabel();
//        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
//        Timer timer = new Timer(1000, new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                countdown[0]--; // Decrease the countdown value
//
//                int minutes = countdown[0] / 60;
//                int seconds = countdown[0] % 60;
//
//                countdownLabel.setText(String.format("Countdown: %02d:%02d", minutes, seconds));
//
//                // If countdown reaches 0, stop the timer and show a message
//                if (countdown[0] <= 0) {
//                    ((Timer)e.getSource()).stop();
//                    endScrumCall();
//                }
//            }
//        });
//
//        JButton startScrumCallButton = new JButton("Start Scrum Call");
//        startScrumCallButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Start the timer when the button is clicked
//                timer.start();
//            }
//        });
//
//        JButton endScrumButton = new JButton("End Scrum Call");
//        endScrumButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timer.stop();
//                endScrumCall();
//            }
//        });
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
//
//        bottomPanel.add(countdownLabel);
//        bottomPanel.add(startScrumCallButton);
//        bottomPanel.add(endScrumButton);
//
//        add(bottomPanel, BorderLayout.PAGE_END);
//
//        // Initial board update
//        updateBoard();
//    }
//
//    private String[] fetchPlayerDataAndPopulateDropdown(String apiUrl) {
//        RestTemplate restTemplate = new RestTemplate();
//        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
//
//        List<Map<String, Object>> playerData = parseJsonResponse(jsonResponse);
//
//        String[] playerNames = new String[0];
//        playerNameToIdMap = new HashMap<>();
//
//        if (playerData != null) {
//            playerNames = extractPlayerNames(playerData);
//            userDropdown = new JComboBox<>(playerNames);
//
//            // Populate playerNameToIdMap
//            for (Map<String, Object> player : playerData) {
//                String playerName = player.get("playerName").toString();
//                String playerId = player.get("playerid").toString();
//                playerNameToIdMap.put(playerName, playerId);
//            }
//
//            userDropdown.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Fetch user story details for the selected player
//                    String selectedPlayerName = (String) userDropdown.getSelectedItem();
//                    String selectedPlayerId = playerNameToIdMap.get(selectedPlayerName);
//                    fetchUserStoryDetails(selectedPlayerId);
//
//                    // Update the board
//                    updateBoard();
//                }
//            });
//
//            return playerNames;
//        } else {
//            // Handle the case where data parsing fails or is empty
//            JOptionPane.showMessageDialog(null, "Failed to fetch player data.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        return playerNames;
//    }
//
//    private void fetchUserStoryDetails(String selectedPlayer) {
//        // Assuming you have an API to fetch user story details based on the selected player
//        String userStoryDetailsUrl = "http://localhost:8080/player-stories/" + selectedPlayer;
//
//        RestTemplate restTemplate = new RestTemplate();
//        String jsonResponse = restTemplate.getForObject(userStoryDetailsUrl, String.class);
//
//        // Parse the user story details and update userData map
//        List<Map<String, Object>> userStoryData = parseUserStoryDetails(jsonResponse);
//
//        if (userStoryData != null) {
//            Map<String, String> userStoryMap = new HashMap<>();
//            for (Map<String, Object> userStory : userStoryData) {
//                userStoryMap.put((String) userStory.get("userStoryName"), (String) userStory.get("status"));
//                System.out.println(userStory.get("storyTitle"));
//            }
//
//            userData.put(selectedPlayer, userStoryMap);
//        } else {
//            // Handle the case where data parsing fails or is empty
//            JOptionPane.showMessageDialog(null, "Failed to fetch user story details.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private List<Map<String, Object>> parseUserStoryDetails(String jsonResponse) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            List<Map<String, Object>> userStoryDetails = objectMapper.readValue(
//                    jsonResponse,
//                    new TypeReference<List<Map<String, Object>>>() {}
//            );
//
//            return userStoryDetails;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    private List<Map<String, Object>> parseJsonResponse(String jsonResponse) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.readValue(jsonResponse, new TypeReference<List<Map<String, Object>>>() {});
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String[] extractPlayerNames(List<Map<String, Object>> playerData) {
//        // Assuming 'playerName' is the key for player names in the JSON response
//        return playerData.stream()
//                .map(player -> player.get("playerName").toString())
//                .toArray(String[]::new);
//    }
//
//    public JPanel createBoardPanel() {
//        JPanel boardPanel = new JPanel(new GridLayout(1, 3));
//
//        for (String lane : new String[]{"To Do", "In Progress", "Done"}) {
//            JPanel lanePanel = new JPanel();
//            lanePanel.setLayout(new BoxLayout(lanePanel, BoxLayout.Y_AXIS));
//            lanePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), lane));
//            lanePanel.setBackground(new Color(255, 255, 224)); // Set lane background color
//
//            boardPanel.add(lanePanel);
//        }
//
//        return boardPanel;
//    }
//
//    public void updateBoard() {
//        String selectedUser = (String) userDropdown.getSelectedItem();
//        if (selectedUser != null) {
////            Map<String, String> userStories = userData.get(selectedUser);
////            clearBoard();
////
////            for (Map.Entry<String, String> entry : userStories.entrySet()) {
////                String userStory = entry.getKey();
////                String status = entry.getValue();
////
////                JPanel cardPanel = createCardPanel(userStory, status);
////                addToLanePanel(status, cardPanel);
////            }
//        }
//    }
//
//    public JPanel createCardPanel(String userStory, String status) {
//        JPanel cardPanel = new JPanel();
//        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
//        cardPanel.setBorder(BorderFactory.createEtchedBorder());
//        cardPanel.setBackground(Color.RED); // Set card background color
//        cardPanel.setPreferredSize(new Dimension(800, 200)); // Set fixed size
//
//        JLabel userStoryLabel = new JLabel(userStory);
//        userStoryLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        userStoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        cardPanel.add(userStoryLabel);
//
//        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//
//        JButton updateStatus = new JButton("Update Status");
//        updateStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
//        updateStatus.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Show daily status input dialog
//                showStatusInputDialog(userStory);
//            }
//        });
//
//        JPanel flipPanel = new JPanel();
//        flipPanel.setLayout(new BoxLayout(flipPanel, BoxLayout.Y_AXIS));
//        flipPanel.setBorder(BorderFactory.createEtchedBorder());
//        flipPanel.setBackground(Color.GREEN); // Set card background color
//        flipPanel.setPreferredSize(new Dimension(800, 200));
//        flipPanel.setVisible(false);
//
//        JLabel flippedUserStoryLabel = new JLabel(userStory);
//        flippedUserStoryLabel.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        flippedUserStoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        flipPanel.add(flippedUserStoryLabel);
//
//        JLabel storyPoints = new JLabel("Story Points");
//        storyPoints.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        storyPoints.setAlignmentX(Component.CENTER_ALIGNMENT);
//        flipPanel.add(storyPoints);
//
//        JLabel description = new JLabel("Description");
//        description.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        description.setAlignmentX(Component.CENTER_ALIGNMENT);
//        flipPanel.add(description);
//
//        JLabel acceptanceCriteria = new JLabel("Acceptance Criteria");
//        acceptanceCriteria.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        acceptanceCriteria.setAlignmentX(Component.CENTER_ALIGNMENT);
//        flipPanel.add(acceptanceCriteria);
//
//        JLabel todayUpdate = new JLabel("Today's Update");
//        todayUpdate.setFont(new Font("Arial", Font.BOLD, 12)); // Reduce font size
//        todayUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
//        flipPanel.add(todayUpdate);
//
//        JButton provideUpdate = new JButton("Provide Update");
//        provideUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
//        provideUpdate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Show daily status input dialog
//                cardPanel.setVisible(!cardPanel.isVisible());
//                flipPanel.setVisible(!flipPanel.isVisible());
//            }
//        });
//
//        JButton backButton = new JButton("Back");
//        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Show daily status input dialog
//                cardPanel.setVisible(!cardPanel.isVisible());
//                flipPanel.setVisible(!flipPanel.isVisible());
//            }
//        });
//        flipPanel.add(backButton);
//        cardPanel.add(updateStatus);
//        cardPanel.add(provideUpdate);
//
//        JPanel container = new JPanel();
//        container.add(cardPanel);
//        container.add(flipPanel);
//
//        return container;
//    }
//
//    public void addToLanePanel(String status, JPanel cardPanel) {
//        Component[] lanePanels = boardPanel.getComponents();
//        switch (status) {
//            case "To Do":
//                ((JPanel) lanePanels[0]).add(cardPanel);
//                break;
//            case "In Progress":
//                ((JPanel) lanePanels[1]).add(cardPanel);
//                break;
//            case "Done":
//                ((JPanel) lanePanels[2]).add(cardPanel);
//                break;
//        }
//        boardPanel.revalidate();
//        boardPanel.repaint();
//    }
//
//    public void clearBoard() {
//        Component[] lanePanels = boardPanel.getComponents();
//        for (Component lanePanel : lanePanels) {
//            ((JPanel) lanePanel).removeAll();
//        }
//        boardPanel.revalidate();
//        boardPanel.repaint();
//    }
//
//    public JPanel createUpdatePanel() {
//        JPanel updatePanel = new JPanel();
//        updatePanel.setLayout(new BorderLayout());
//        updatePanel.setBackground(new Color(240, 240, 240)); // Set background color
//
//        // Update area
//        updateArea = new JTextArea();
//        updateArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(updateArea);
//        updatePanel.add(scrollPane, BorderLayout.CENTER);
//
//        return updatePanel;
//    }
//
//    public String getUpdateForUserStory(String userStory) {
//
//        return "Update for " + userStory + ": Today's progress...";
//    }
//
//    public void showStatusInputDialog(String userStory) {
//        JTextArea statusInput = new JTextArea();
//        statusInput.setLineWrap(true);
//        statusInput.setWrapStyleWord(true);
//
//        JScrollPane scrollPane = new JScrollPane(statusInput);
//
//        int result = JOptionPane.showConfirmDialog(this, scrollPane, "Enter Daily Status for " + userStory,
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            String dailyStatus = statusInput.getText();
//            updateArea.setText("Daily Status for " + userStory + ":\n" + dailyStatus);
//        }
//    }
//
//    public void endScrumCall() {
//        JOptionPane.showMessageDialog(this, "Scrum Call Ended!", "Scrum Ended", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ScrumBoard().setVisible(true);
//            }
//        });
//    }
//}

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ScrumBoard extends JFrame {
    private JPanel boardPanel;
    private JComboBox<String> userDropdown;
    private Map<String, List<StoryCard>> userStoriesMap;
    private Map<String, String> playerNameToIdMap;

    public ScrumBoard() {
        userStoriesMap = new HashMap<>();
        String[] p_names = fetchPlayerDataAndPopulateDropdown("http://localhost:8080/players");

        // Set up the main frame
        setTitle("Scrum Board");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 255, 255));// Set background color

        // Fetch player names and populate the dropdown
        userDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch user story details for the selected player
                String selectedPlayerName = (String) userDropdown.getSelectedItem();
                String selectedPlayerId = playerNameToIdMap.get(selectedPlayerName);
                fetchUserStories(selectedPlayerId);

                // Update the board
                updateBoard();
            }
        });

        boardPanel = createBoardPanel();
        add(userDropdown, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);

        final int[] countdown = fetchTimer();

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

    private int[] fetchTimer() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/sprint/timer/1";
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        int[] countdown = new int[0];
        if(jsonResponse != null) {
            countdown = new int[]{(int)Double.parseDouble(jsonResponse) * 60};
        }else{
            countdown = new int[]{10 * 60};
        }
        return countdown;
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
            return playerNames;
        } else {
            // Handle the case where data parsing fails or is empty
            JOptionPane.showMessageDialog(null, "Failed to fetch player data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return playerNames;
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

    private void fetchUserStories(String selectedPlayerid) {
        // Fetch user stories for the selected player from the backend
        // Replace this with your actual API call to fetch user stories based on the player
        // Example: userStoriesMap.put(selectedPlayer, fetchUserStoriesFromBackend(selectedPlayer));
        String userStoryDetailsUrl = "http://localhost:8080/player-stories/" + selectedPlayerid;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(userStoryDetailsUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Long> storyidList = null;

        try {
            System.out.println("fetching user story ids...");
             storyidList = mapper.readValue(jsonResponse, new TypeReference<List<Long>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        fetchUserStoriesFromBackend(storyidList);
    }

    private String[] extractPlayerNames(List<Map<String, Object>> playerData) {
        // Assuming 'playerName' is the key for player names in the JSON response
        return playerData.stream()
                .map(player -> player.get("playerName").toString())
                .toArray(String[]::new);
    }

    private void fetchUserStoriesFromBackend(List<Long> storyidList) {
        // Replace this with your actual API call to fetch user stories from the backend
        // You might need to pass the selectedPlayer to the backend to get player-specific stories
        // Example: return restTemplate.getForObject(apiUrl, List<StoryCard>.class);
        // For now, returning dummy data userStoriesMap.put(selectedPlayerid, userStories);

        for(Long storyid : storyidList)
        {
            String userStoryDetailsUrl = "http://localhost:8080/backlog/" + storyid.toString();
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(userStoryDetailsUrl, String.class);

            List<Map<String, Object>> userStoryData = parseUserStoryDetails(jsonResponse);

            if (userStoryData != null) {
                System.out.println("creating cards...");
                createStoryCards(userStoryData);

                for (Map<String, Object> userStory : userStoryData) {
                    System.out.println(userStory.get("storyTitle"));
                }
            }else {
                // Handle the case where data parsing fails or is empty
                JOptionPane.showMessageDialog(null, "Failed to fetch user story details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private List<Map<String, Object>> parseUserStoryDetails(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Try to parse as a list
            return objectMapper.readValue(
                    jsonResponse,
                    new TypeReference<List<Map<String, Object>>>() {}
            );
        } catch (IOException e) {
            // If parsing as a list fails, try parsing as a single object
            try {
                Map<String, Object> userStoryDetails = objectMapper.readValue(
                        jsonResponse,
                        new TypeReference<Map<String, Object>>() {}
                );

                // Wrap the single object in a list
                List<Map<String, Object>> userStoryList = new ArrayList<>();
                userStoryList.add(userStoryDetails);
                return userStoryList;
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(1, 3));

        for (String lane : new String[]{"To Do", "In Progress", "Done"}) {
            JPanel lanePanel = new JPanel(new GridLayout(0, 1));
            lanePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), lane));
            lanePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            lanePanel.setBackground(new Color(224, 231, 255)); // Set lane background color

            // Add drag-and-drop functionality to the lane panel
            addDragDropFunctionality(lanePanel);

            boardPanel.add(lanePanel);
        }

        return boardPanel;
    }

    private void addDragDropFunctionality(JPanel panel)  {
        DropTarget dropTarget = new DropTarget();
        dropTarget.setComponent(panel);

        try {
            dropTarget.addDropTargetListener(new DropTargetAdapter() {
                @Override
                public void drop(DropTargetDropEvent dtde) {
                    try {
                        Transferable transferable = dtde.getTransferable();

                        if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);

                            // Process the dropped data here

                            dtde.dropComplete(true);
                            return;
                        }

                        dtde.rejectDrop();
                    } catch (Exception e) {
                        e.printStackTrace();
                        dtde.rejectDrop();
                    }
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void createStoryCards(List<Map<String, Object>> userStories) {
        //clearBoard(); // Clear existing cards
        System.out.println("creating cards...board cleared");

        for (Map<String, Object> userStory : userStories) {
            // Extract story details
            String storyid = userStory.get("storyid").toString();
            String title = userStory.get("storyTitle").toString();
            String description = userStory.get("storyDescription").toString();
            String assignedTo = userStory.get("assignedTo").toString();
            String status = userStory.get("status").toString();
            String storyPoints = userStory.get("storyPoints").toString();

            // Create a StoryCard for the current user story
            StoryCard storyCard = new StoryCard(storyid, title, description, status, assignedTo, storyPoints);

            // Add the StoryCard to the appropriate lane
            addToLanePanel(status, storyCard);
        }
    }

    // Modified method to create a card panel with dynamic content
    public JPanel createCardPanel(String dtoryid, String title, String description,  String status,  String storyPoints) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEtchedBorder());
        cardPanel.setBackground(Color.CYAN); // Set card background color
        cardPanel.setPreferredSize(new Dimension(20, 50));;  // Set fixed size

        JLabel titleLabel = new JLabel("Title: " + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(titleLabel);

        JLabel descriptionLabel = new JLabel("Description: " + description);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(descriptionLabel);

        JLabel commentsLabel = new JLabel("Status: " + status);
        commentsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        commentsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(commentsLabel);

        JLabel pointsLabel = new JLabel("storyPoints: " + storyPoints);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 12));
        pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(pointsLabel);

        // Other fields...
        System.out.println(cardPanel);
        return cardPanel;
    }

    private void moveCardToLane(String cardTitle, JPanel newLane) {
        // Find the card in the existing lanes and move it to the new lane
        for (List<StoryCard> userStories : userStoriesMap.values()) {
            for (StoryCard storyCard : userStories) {
                if (storyCard.getTitle().equals(cardTitle)) {
                    // Remove the card from the old lane
                    for (Component oldLane : boardPanel.getComponents()) {
                        if (Arrays.asList(((JPanel) oldLane).getComponents()).contains(storyCard)) {
                            ((JPanel) oldLane).remove(storyCard);
                            break;
                        }
                    }

                    // Add the card to the new lane
                    newLane.add(storyCard);
                    break;
                }
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void addToLanePanel(String status, StoryCard storyCard) {
        // Find the appropriate lane panel
        JPanel lanePanel = (JPanel) boardPanel.getComponent(getLaneIndex(status));

        // Add the StoryCard to the lane panel
        lanePanel.add(storyCard);


    }

    private int getLaneIndex(String status) {
        switch (status.toLowerCase()) {
            case "ready":
                return 0;
            case "new":
                return 0;
            case "in progress":
                return 1;
            case "completed":
                return 2;
            default:
                return -1; // Handle invalid status
        }
    }



    private void updateBoard() {
        clearBoard(); // Clear the board before updating
        String selectedPlayerName = (String) userDropdown.getSelectedItem();
        String selectedPlayerId = playerNameToIdMap.get(selectedPlayerName);
        System.out.println(selectedPlayerId);
        fetchUserStories(selectedPlayerId);
    }

    private JPanel getLanePanel(String status) {
        Component[] lanePanels = boardPanel.getComponents();
        for (Component lanePanel : lanePanels) {
            if (lanePanel instanceof JPanel) {
                Border border = ((JPanel) lanePanel).getBorder();
                if (border instanceof TitledBorder) {
                    String title = ((TitledBorder) border).getTitle();
                    if (title.equals(status)) {
                        return (JPanel) lanePanel;
                    }
                }
            }
        }
        return null;
    }

    private void clearBoard() {
        Component[] lanePanels = boardPanel.getComponents();
        for (Component lanePanel : lanePanels) {
            ((JPanel) lanePanel).removeAll();
        }
        boardPanel.revalidate();
        boardPanel.repaint();
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
