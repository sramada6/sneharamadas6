package ScrumPlay_FrontEnd;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class SprintChartPage extends JFrame {

    private CombinedData combinedData;

    public SprintChartPage(CombinedData combinedData) {
        this.combinedData = combinedData;

        setTitle("Sprint Burn Down Chart");
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        SprintChartPanel sprintChartPanel = new SprintChartPanel(combinedData);
        add(sprintChartPanel);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void Func() {
        try {
            long sprintId = 1;

            List<Map<String, Object>> combinedDataList = fetchCombinedDataFromBackend(sprintId);

            CombinedData combinedData = createCombinedData(combinedDataList);

            SwingUtilities.invokeLater(() -> {
                SprintChartPage sprintChartPage = new SprintChartPage(combinedData);
                sprintChartPage.setSize(800, 600);
                sprintChartPage.setLocationRelativeTo(null);
                sprintChartPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                sprintChartPage.setVisible(true);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CombinedData createCombinedData(List<Map<String, Object>> combinedDataList) {
        CombinedData combinedData = new CombinedData();
        SprintData sprintData = new SprintData();

        int totalStoryPoints = combinedDataList.stream()
                .mapToInt(data -> (int) data.get("storyPoints"))
                .sum();

        int totalWorkRemaining = totalStoryPoints;

        for (Map<String, Object> data : combinedDataList) {
            String startDateStr = (String) data.get("startDate");
            Date startDate = parseDate(startDateStr);
            //String CompletedPoints = (String) data.get("storyPointsCompleted");
            int workRemaining = (int) data.get("workRemaining");


            totalWorkRemaining -= workRemaining;

            SprintChartData sprintChartData = new SprintChartData(startDate, totalWorkRemaining);
            sprintData.getSprintChartDataList().add(sprintChartData);
        }

        combinedData.setSprint(sprintData);
        return combinedData;
    }

    private static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty() || dateStr.equalsIgnoreCase("null")) {
            return null;
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing date: " + dateStr, e);
        }
    }

    private static List<Map<String, Object>> fetchCombinedDataFromBackend(long sprintId) throws IOException {
        String apiUrl = "http://localhost:8080/sprint/user-stories/" + Long.toString(sprintId);
        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(connection.getInputStream());

                List<Map<String, Object>> combinedDataList = new ArrayList<>();

                if (jsonNode.isArray()) {
                    ArrayNode arrayNode = (ArrayNode) jsonNode;
                    arrayNode.forEach(element -> {
                        Map<String, Object> combinedData = new HashMap<>();
                        combinedData.put("startDate", element.path("startDate").asText());
                        combinedData.put("workRemaining", element.path("workRemaining").asInt());
                        //combinedData.put("CompletedPoints", element.path("CompletedPoints").asInt());
                        combinedData.put("storyPoints", element.path("storyPoints").asInt());
                        combinedDataList.add(combinedData);
                    });
                }

                return combinedDataList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to fetch data. HTTP error code: " + connection.getResponseCode());
        }

        return Collections.emptyList();
    }

    public static class CombinedData {
        private SprintData sprint;

        public SprintData getSprint() {
            return sprint;
        }

        public void setSprint(SprintData sprint) {
            this.sprint = sprint;
        }
    }

    public static class SprintData {
        private List<SprintChartData> sprintChartDataList = new ArrayList<>();

        public List<SprintChartData> getSprintChartDataList() {
            return sprintChartDataList;
        }
    }

    public static class SprintChartData {
        private Date startDate;
        private int workRemaining;

        public SprintChartData(Date startDate, int workRemaining) {
            this.startDate = startDate;
            this.workRemaining = workRemaining;
        }

        public Date getStartDate() {
            return startDate;
        }

        public int getWorkRemaining() {
            return workRemaining;
        }
    }

    static class SprintChartPanel extends JPanel {

        private CombinedData combinedData;

        SprintChartPanel(CombinedData combinedData) {
            this.combinedData = combinedData;

            JButton myButton = new JButton("Show Score");
            myButton.addActionListener(e -> handleButtonClick()); // Add your button click logic here


            setLayout(null);
            add(myButton);


            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();


                    myButton.setBounds(panelWidth - 100, 0, 100, 30);
                }
            });
        }
        private void handleButtonClick() {
            // Add your logic here for what should happen when the button is clicked
            ShowScoresPage showScoresPage = new ShowScoresPage();
            showScoresPage.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int width = getWidth();
            int height = getHeight();
            int numDays = combinedData.getSprint().getSprintChartDataList().size();

            g.drawString("Days", width / 2 - 10, height - 20);
            g.drawLine(50, height - 50, width - 50, height - 50);

            g.drawString("Work", 10, height / 2);
            g.drawLine(50, 50, 50, height - 50);

            paintSprint(g, width, height, numDays);
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

        private int getMaxValue() {
            return combinedData.getSprint().getSprintChartDataList().stream()
                    .mapToInt(SprintChartData::getWorkRemaining)
                    .max()
                    .orElse(1);
        }
    }
}
