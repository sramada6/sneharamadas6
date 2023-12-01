package ScrumPlay_FrontEnd;


import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class ScrumBoard extends JFrame {
    private JPanel boardPanel;
    private JComboBox<String> userDropdown;
    private Map<String, List<StoryCard>> userStoriesMap;
    public static Map<String, String> playerNameToIdMap;

    public int scrumNum = 1;
    public int completedStoryPoints = 0;

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
                updateBackendWithStoryCardValues();
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
                updateBoard();
                timer.start();
                boardPanel.revalidate();
                boardPanel.repaint();
                for (Component lane : boardPanel.getComponents()) {
                    if (lane instanceof JPanel) {
                        lane.revalidate();
                        lane.repaint();
                        for (Component card : ((JPanel) lane).getComponents()) {
                            if (card instanceof StoryCard) {
                                card.revalidate();
                                card.repaint();
                            }
                        }
                    }
                }
            }
        });

        JButton endScrumButton = new JButton("End Scrum Call");
        endScrumButton.addActionListener(e -> {
            timer.stop();
            endScrumCall();
        });

        JButton Chart = new JButton("Generate Sprint Chart");
        Chart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SprintChartPage here
                SprintChartPage.Func();
                //sprintChartPage.setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(countdownLabel);
        bottomPanel.add(startScrumCallButton);
        bottomPanel.add(endScrumButton);
        bottomPanel.add(Chart);

        add(bottomPanel, BorderLayout.PAGE_END);

        // Initial board update
        updateBoard();
    }

    private int[] fetchTimer() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/sprint/timer/1";
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        System.out.println(jsonResponse);
        int[] countdown = new int[0];
        if(jsonResponse != null) {
            countdown = new int[]{(int)Double.parseDouble(jsonResponse) * 60};
        }else{
            countdown = new int[]{10 * 60};
        }
        return countdown;
    }

    private int fetchSprintLength() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/sprint/length/1";
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        int sLength = 0;
        if(jsonResponse != null) {
            System.out.println("Setting sprint length as ....");
            sLength = Integer.parseInt(jsonResponse);
            System.out.println(sLength);
        }else{
            System.out.println("Unable to fetch Sprint Length");
            sLength = 3;
        }
        return sLength;
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
            flippedStoryCard flippedstoryCard = new flippedStoryCard(storyid, title, description, status, assignedTo, storyPoints, this, null);
            StoryCard storyCard = new StoryCard(storyid, title, description, status, assignedTo, storyPoints, this, flippedstoryCard);
            flippedstoryCard.setCorrespondingStoryCard(storyCard);
            System.out.println("card created");
            System.out.println(storyCard.getRootPane());

            // Add the StoryCard to the appropriate lane
            addToLanePanel(status, storyCard, flippedstoryCard);
            }
        }

    public void flipCard(StoryCard storyCard, flippedStoryCard flippedstoryCard) {
        storyCard.setVisible(!storyCard.isVisible());
        flippedstoryCard.setVisible(!flippedstoryCard.isVisible());
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



    private void addToLanePanel(String status, StoryCard storyCard, flippedStoryCard flippedstoryCard) {
        // Find the appropriate lane panel
        JPanel lanePanel = (JPanel) boardPanel.getComponent(getLaneIndex(status));

        // Add the StoryCard to the lane panel
        lanePanel.add(storyCard);
        lanePanel.add(flippedstoryCard);
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


    private void clearBoard() {
        Component[] lanePanels = boardPanel.getComponents();
        for (Component lanePanel : lanePanels) {
            ((JPanel) lanePanel).removeAll();
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    public void endScrumCall() {
        System.out.println("waiting for back end");
        updateBackendWithStoryCardValues();
        if(scrumNum < fetchSprintLength())
        {
            JOptionPane.showMessageDialog(this, "Scrum Call "+ scrumNum + " Ended!", "Scrum " + scrumNum +" Ended", JOptionPane.INFORMATION_MESSAGE);
            scrumNum++;
        }else{
            updateBackendCompletedPointsInSprint();
            JOptionPane.showMessageDialog(this, "Story Points completed: " + completedStoryPoints, "End of Sprint", JOptionPane.INFORMATION_MESSAGE);
            //add code here to go to new page

        }
    }

    private void updateBackendCompletedPointsInSprint() {
        try {
            // Assuming completedStoryPoints is a variable holding the completed story points
            int sprintId =1; // Replace with the actual method to get the sprint ID

            for (Component lane : boardPanel.getComponents()) {
                if (lane instanceof JPanel) {
                    for (Component card : ((JPanel) lane).getComponents()) {
                        if (card instanceof StoryCard) {
                            System.out.println("got the updated cards");
                            StoryCard storyCard = (StoryCard) card;
                            String updatedStatus = storyCard.getStatusComboBox();
                            if (updatedStatus.equals("Completed")){
                                String updatedStoryPoints = storyCard.getStoryPointsComboBox();
                                completedStoryPoints += Integer.parseInt(updatedStoryPoints);
                            }

                        }}}}

            String apiUrl = "http://localhost:8080/sprint/story-points/" + sprintId + "?storyPointsCompleted=" + completedStoryPoints;

            System.out.println("generatingPayload");
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("endDate", LocalDateTime.now());

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(jsonPayload);

            // Set the request method to PUT
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(jsonPayload.length()));

            // Write JSON payload to the connection's output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code for sprint update: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBackendWithStoryCardValues() {
        for (Component lane : boardPanel.getComponents()) {
            if (lane instanceof JPanel) {
                for (Component card : ((JPanel) lane).getComponents()) {
                    if (card instanceof StoryCard) {
                        System.out.println("got the updated cards");
                        StoryCard storyCard = (StoryCard) card;

                        // Extract updated values from the StoryCard
                        String storyId = storyCard.getStoryId();
                        String updatedStatus = storyCard.getStatusComboBox();
                        String updatedAssignedTo = storyCard.getAssignedToComboBox();
                        String updatedStoryPoints = storyCard.getStoryPointsComboBox();
                        String updatedDescription = storyCard.getDescription();

                        System.out.println("Saving your changes...");

                        // Send a PUT request to the backend with the updated values
                        sendPutRequest(storyId, updatedDescription, updatedStatus, updatedAssignedTo, updatedStoryPoints);
                    }
                }
            }
        }
    }

    private JSONObject prepareJsonData(String updatedDescription, String updatedStatus, String updatedAssignedTo, String updatedStoryPoints) {


        JSONObject jsonData1 = new JSONObject();
        jsonData1.put("storyDescription", updatedDescription);
        jsonData1.put("status", updatedStatus);
        if (updatedStatus.equals("In Progress"))
        {
            jsonData1.put("startDate", LocalDateTime.now());
        }
        if (updatedStatus.equals("Completed"))
        {
            jsonData1.put("completionDate", LocalDateTime.now());
        }

        jsonData1.put("storyPoints", updatedStoryPoints);
        JSONObject jsonData2 = new JSONObject();
        jsonData2.put("playerid", updatedAssignedTo);
        jsonData1.put("assignedTo", jsonData2);

        return jsonData1;
    }

    private void sendPutRequest(String storyId, String updatedDescription, String updatedStatus, String updatedAssignedTo, String updatedStoryPoints) {

        try {
            // Replace with your actual API endpoint
            String apiUrl = "http://localhost:8080/backlog-modify/" + storyId;

            JSONObject jsonPayload = prepareJsonData( updatedDescription,  updatedStatus,  updatedAssignedTo,  updatedStoryPoints);
            System.out.println("jsonPayload");

            // Create URL object
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to PUT
            connection.setRequestMethod("PUT");

            // Enable input and output streams
            connection.setDoOutput(true);

            // Set content type and length
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(jsonPayload.length()));

            // Write JSON payload to the connection's output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonPayload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ScrumBoard().setVisible(true);
//            }
//        });
//    }
}