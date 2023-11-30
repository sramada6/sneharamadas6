package ScrumPlay_FrontEnd;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SprintChartPage extends JFrame {

    private CombinedData combinedData;

    public SprintChartPage(CombinedData combinedData) {
        this.combinedData = combinedData;

        setTitle("Sprint Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SprintChartPanel sprintChartPanel = new SprintChartPanel(combinedData);
        add(sprintChartPanel);

        JButton gameScoreButton = new JButton("Game Score");
        gameScoreButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Game Score Button Clicked!");
        });

        add(gameScoreButton, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static CombinedData fetchCombinedDataFromBackend() throws IOException {
        String apiUrl = "http://localhost:8080/api/sprint-data/1"; // Replace 1 with the actual sprint ID
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(connection.getInputStream(), CombinedData.class);
        } else {
            System.out.println("Failed to fetch data. HTTP error code: " + connection.getResponseCode());
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                CombinedData combinedData = fetchCombinedDataFromBackend();
                if (combinedData != null) {
                    new SprintChartPage(combinedData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static class CombinedData {
        private SprintData sprint;
        private List<UserStoryData> userStories;

        public SprintData getSprint() {
            return sprint;
        }

        public List<UserStoryData> getUserStories() {
            return userStories;
        }
    }

    public static class SprintData {
        private List<SprintChartData> sprintChartDataList;

        public List<SprintChartData> getSprintChartDataList() {
            return sprintChartDataList;
        }
    }

    public static class UserStoryData {
        private int day;
        private int workRemaining;

        public int getDay() {
            return day;
        }

        public int getWorkRemaining() {
            return workRemaining;
        }
    }

    public static class SprintChartData {
        private int workRemaining;

        public int getWorkRemaining() {
            return workRemaining;
        }
    }

    class SprintChartPanel extends JPanel {

        private CombinedData combinedData;

        SprintChartPanel(CombinedData combinedData) {
            this.combinedData = combinedData;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();
            int numDays = combinedData.getSprint().getSprintChartDataList().size();

            g.drawString("Days", width / 2 - 10, height - 20);
            g.drawLine(50, height - 50, width - 50, height - 50);

            g.drawString("Remaining Work", 10, height / 2);
            g.drawLine(50, 50, 50, height - 50);

            // Paint sprint data
            paintSprint(g, width, height, numDays);

            // Paint user story data
            paintUserStories(g, width, height, numDays);
        }

        private void paintSprint(Graphics g, int width, int height, int numDays) {
            List<SprintChartData> sprintChartDataList = combinedData.getSprint().getSprintChartDataList();

            for (int i = 0; i < numDays; i++) {
                int x = 50 + (i * (width - 100) / (numDays - 1));
                int y = height - 50 - (sprintChartDataList.get(i).getWorkRemaining() * (height - 100) / getMaxValue());

                g.setColor(Color.RED);
                g.fillOval(x - 5, y - 5, 10, 10);

                if (i > 0) {
                    int prevX = 50 + ((i - 1) * (width - 100) / (numDays - 1));
                    int prevY = height - 50 - (sprintChartDataList.get(i - 1).getWorkRemaining() * (height - 100) / getMaxValue());
                    g.drawLine(prevX, prevY, x, y);
                }
            }
        }

        private void paintUserStories(Graphics g, int width, int height, int numDays) {
            List<UserStoryData> userStories = combinedData.getUserStories();

            for (UserStoryData userStory : userStories) {
                int x = 50 + (userStory.getDay() * (width - 100) / (numDays - 1));
                int y = height - 50 - (userStory.getWorkRemaining() * (height - 100) / getMaxValue());

                g.setColor(Color.BLUE);
                g.fillRect(x - 5, y - 5, 10, 10);
            }
        }

        private int getMaxValue() {
            int maxSprintValue = combinedData.getSprint().getSprintChartDataList().stream()
                    .mapToInt(SprintChartData::getWorkRemaining)
                    .max()
                    .orElse(1);

            int maxUserStoryValue = combinedData.getUserStories().stream()
                    .mapToInt(UserStoryData::getWorkRemaining)
                    .max()
                    .orElse(1);

            return Math.max(maxSprintValue, maxUserStoryValue);
        }
    }
}
