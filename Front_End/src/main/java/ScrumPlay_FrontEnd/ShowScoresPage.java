//package ScrumPlay_FrontEnd;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.charset.StandardCharsets;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class ShowScoresPage extends JFrame {
//    private DefaultTableModel tableModel;
//
//    public ShowScoresPage(ScoreDisplay scoreDisplay) {
//        setTitle("Scores");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        String[] columnNames = {"Player", "Score"};
//        String[][] data = {{"Player 1", "0"}};
//        tableModel = new DefaultTableModel(data, columnNames);
//
//        JTable scoresTable = new JTable(tableModel);
//
//        setLayout(new BorderLayout());
//
//        JScrollPane scrollPane = new JScrollPane(scoresTable);
//        add(scrollPane, BorderLayout.CENTER);
//
//        updateScoresFromRestEndpoint("http://localhost:8080/display-score");
//    }
//
//    private void updateScoresFromRestEndpoint(String restEndpointUrl) {
//        try {
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(restEndpointUrl))
//                    .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
//
//            if (response.statusCode() == 200) {
//                String jsonResponse = response.body();
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//                tableModel.setRowCount(0);
//
//                for (JsonNode playerNode : jsonNode) {
//                    String playerName = playerNode.get("playerName").asText();
//                    int playerScore = playerNode.get("playerScore").asInt();
//                    tableModel.addRow(new Object[]{playerName, playerScore});
//                }
//            } else {
//                System.out.println("Failed to fetch data from the REST endpoint. Status code: " + response.statusCode());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ScoreDisplay scoreDisplay = new ScoreDisplay();
//            scoreDisplay.setVisible(true);
//   });
//}
//}