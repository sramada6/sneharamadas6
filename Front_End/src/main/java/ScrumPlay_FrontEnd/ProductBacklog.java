package ScrumPlay_FrontEnd;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

    public ProductBacklog() {
        setTitle("Product Backlog");
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        getContentPane().setBackground(new Color(0, 255, 255));
        getContentPane().setBounds(100, 100, 908, 720);

        listModel = new DefaultListModel<>();
//        listModel.addElement("US#001/Create Landing Page");
//        listModel.addElement("US#002/Follow up for Scrum Intro");
//        listModel.addElement("US#003/Create Database");
//        listModel.addElement("US#004/Design game configuration page");
//        listModel.addElement("US#005/Display previous game scores and sprint history");

        userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fetchUserStoriesFromAPI();
        JScrollPane listScrollPane = new JScrollPane(userList);
        listScrollPane.setPreferredSize(new Dimension(200, 0));

        detailPanel = new JPanel(new BorderLayout());
        mainPanel.add(listScrollPane, BorderLayout.WEST);
        getContentPane().setBackground(new Color(0, 255, 255));
        mainPanel.setPreferredSize(new Dimension(800, 600));

        mainPanel.add(detailPanel, BorderLayout.CENTER);
        getContentPane().setBackground(new Color(0, 255, 255));
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedUserStory = userList.getSelectedValue();
                System.out.println("What is this?"+ selectedUserStory);
                showUserStoryDetails(selectedUserStory);

            }
        });

        userStoryDescriptions = new HashMap<>();
//        userStoryDescriptions.put("US#001/Create Landing Page", "As a developer, I want to design an informative page for the trainees which contains the basic details of scrum and the vocabulary used, such as sprint, sprint backlog, burndown charts and sprint velocity. The page displays a one-line definition of each term followed by a link to the full description.");
//        userStoryDescriptions.put("US#002/Follow up for Scrum Intro", "As a developer, I want to design a webpage which is a follow up for the landing page when the \"see more..\" button is clicked. This page would provide detailed information including a tutorial for the concepts such as sprint, sprint backlog, etc.");
//        userStoryDescriptions.put("US#003/Create Database", "As a developer, I want to design a database which can store all the information regarding ScrumPlay which includes the game configuration, problem statement, user stories, etc. This can be used to fetch details when required so that it can be displayed in the front-end of ScrumPlay.");
//        userStoryDescriptions.put("US#004/Design game configuration page", "As a developer, I want to create a set game configuration page where the user can set parameters such as team size, length of sprint and length of scrum call. The user can also decide the roles of the players, such as Product Owner, Scrum Master and developers.");
//        userStoryDescriptions.put("US#005/Display previous game scores and sprint history", "As a developer, I want to display previous game scores and sprint charts so that the user can have an idea what all values are expected and also to know about the history of the previous games.");
        pack();
    }
    private JSONArray fetchUserStoriesFromAPI() {
        if (listModel.isEmpty()) {  // Check if the list is empty before fetching
            String apiUrl = "http://localhost:8080/backlog";
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();

                JSONArray userStoriesArray = new JSONArray(response.toString());

                for (int i = 0; i < userStoriesArray.length(); i++) {
                    JSONObject userStory = userStoriesArray.getJSONObject(i);
                    String storyTitle = userStory.getString("storyTitle");
                    listModel.addElement(storyTitle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();
    }
    // ... existing code ...

    private void fetchPlayerNamesFromAPI() {
        String apiUrl = "http://localhost:8080/player-names";
        String response = sendGetRequestToBackend(apiUrl);

        try {
            // Parse the JSON response
            JSONArray playerNamesArray = new JSONArray(response);

            // Clear existing items
            playerDropdown.removeAllItems();

            // Add player names to the dropdown
            for (int i = 0; i < playerNamesArray.length(); i++) {
                String playerName = playerNamesArray.getString(i);
                System.out.println("Name"+playerName);
                playerDropdown.addItem(playerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getStoryDescriptionFromAPI(String selectedUserStory) {
        String apiUrl = "http://localhost:8080/backlog";
        String response = sendGetRequestToBackend(apiUrl);

        try {
            // Parse the JSON response
            JSONArray userStoriesArray = new JSONArray(response);

            for (int i = 0; i < userStoriesArray.length(); i++) {
                JSONObject userStory = userStoriesArray.getJSONObject(i);
                String storyTitle = userStory.getString("storyTitle");
                String storyDescription = userStory.getString("storyDescription");

                if (selectedUserStory.equals(storyTitle)) {
                    return storyDescription;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


// ... existing code ...



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
        playerDropdown = new JComboBox<>();
        fetchPlayerNamesFromAPI();

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

        // Fetch and set the story description from the API
        String storyDescription = getStoryDescriptionFromAPI(userStory);
        descriptionTextArea.setText(storyDescription);

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

        // Panel for the "Start Sprint Planning" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // Button to clear comments
        JButton clearCommentsButton = new JButton("Clear Comments");
        //JButton FetchUSButton = new JButton("More User Stories");

        clearCommentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                commentsTextArea.setText("");
            }
        });
        buttonPanel.add(clearCommentsButton);
        JButton startSprintButton = new JButton("Start Sprint");
        startSprintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScrumBoard ScrumBoardFrame = new ScrumBoard();
                ScrumBoardFrame.setVisible(true);
            }
        });
        buttonPanel.add(startSprintButton);
        getContentPane().setBackground(new Color(0, 255, 255));

        detailPanel.add(userStoryInfoPanel, BorderLayout.CENTER);
        detailPanel.add(buttonPanel, BorderLayout.SOUTH);
        detailPanel.revalidate();
        detailPanel.repaint();

        //buttonPanel.add(FetchUSButton);

        playerDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected player name
                String selectedPlayer = (String) playerDropdown.getSelectedItem();
                // Update the database with the selected player name
                updateDatabaseWithPlayer(selectedPlayer, userStory);
            }
        });

        userStoryPointsField = new JComboBox<>(new String[]{"1", "2", "3", "5"});
        userStoryPointsField.setSelectedIndex(0);

        // Add an ActionListener to userStoryPointsField
        userStoryPointsField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected user story points
                String selectedPoints = (String) userStoryPointsField.getSelectedItem();
                // Update the database with the selected user story points
                updateDatabaseWithPoints(selectedPoints, userStory);
            }
        });
    }

    private void updateDatabaseWithPlayer(String selectedPlayer, String userStory) {
        // Get the userStoryID based on the userStory title from the API
        String userStoryID = getUserStoryIDFromAPI(userStory);

        // Construct the API endpoint
        String apiUrl = "http://localhost:8080/backlog-modify/" + userStoryID;

        // Construct the request parameters (assuming a POST request)
        Map<String, String> params = new HashMap<>();
        params.put("playerName", selectedPlayer);

        // Send the POST request to update the database
        sendPutRequestToBackend(apiUrl, params);

    }

    // Method to update the database with the selected user story points
    private void updateDatabaseWithPoints(String selectedPoints, String userStory) {
        // Get the userStoryID based on the userStory title from the API
        String userStoryID = getUserStoryIDFromAPI(userStory);

        // Construct the API endpoint
        String apiUrl = "http://localhost:8080/backlog-modify/" + userStoryID;

        // Construct the request parameters (assuming a POST request)
        Map<String, String> params = new HashMap<>();
        params.put("storyPoints", selectedPoints);

        // Send the POST request to update the database
        sendPutRequestToBackend(apiUrl, params);
    }
    private String getUserStoryIDFromAPI(String userStoryTitle) {
        // Fetch user stories from the API
        JSONArray userStoriesArray = fetchUserStoriesFromAPI();

        // Iterate through user stories to find the ID
        for (int i = 0; i < userStoriesArray.length(); i++) {
            JSONObject userStory = userStoriesArray.getJSONObject(i);
            String storyTitle = userStory.getString("storyTitle");
            String storyID = userStory.getString("storyID");

            if (userStoryTitle.equals(storyTitle)) {
                return storyID;
            }
        }

        return ""; // Return empty string if not found (handle this case appropriately in your application)
    }

// ... existing code ...

    // Method to send a POST request to the backend
    private void sendPutRequestToBackend(String apiUrl, Map<String, String> params) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            // Construct the request parameters
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            }

            // Write the parameters to the connection
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData.toString().getBytes("UTF-8"));
            }

            // Check the response code to ensure the request was successful
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle successful response (if needed)
            } else {
                // Handle error response (if needed)
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String sendGetRequestToBackend(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private String getStoryDescription(String selectedUserStory, String userStories) {
        JSONArray jsonArray = new JSONArray(userStories);
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObject = jsonArray.getJSONObject(i);
            if (selectedUserStory.equals(String.valueOf(jsonObject.getInt("storyid")))) {
                System.out.println("if" + jsonObject.getString("storyDescription"));

                return jsonObject.getString("storyDescription");
            }
            System.out.println("getstorydescription" + jsonObject.getString("storyDescription"));


        }

        return jsonObject.getString("storyDescription");
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                ProductBacklog frame = new ProductBacklog();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
}