package ScrumPlay_FrontEnd;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShowScoresPage extends JFrame {
    private DefaultTableModel tableModel;

    public ShowScoresPage(ScoreDisplay scoreDisplay) {
        setTitle("Player Scores");
        setSize(600, 400); // Set the preferred size of the JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Player", "Score"};
        String[][] data = {{"Player 1", "0"}};
        tableModel = new DefaultTableModel(data, columnNames);

        JTable scoresTable = new JTable(tableModel);
        scoresTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Customize header font

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        scoresTable.setDefaultRenderer(Object.class, centerRenderer);

        setLayout(new BorderLayout());

        TitledBorder titledBorder = BorderFactory.createTitledBorder("Player Scores");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16)); // Customize border title font
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setBorder(titledBorder);

        add(scrollPane, BorderLayout.CENTER);

        updateScoresFromRestEndpoint("http://localhost:8080/display-score");
    }

    private void updateScoresFromRestEndpoint(String restEndpointUrl) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(restEndpointUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                tableModel.setRowCount(0);

                for (JsonNode playerNode : jsonNode) {
                    String playerName = playerNode.get("playerName").asText();
                    int playerScore = playerNode.get("playerScore").asInt();
                    tableModel.addRow(new Object[]{playerName, playerScore});
                }
            } else {
                System.out.println("Failed to fetch data from the REST endpoint. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScoreDisplay scoreDisplay = new ScoreDisplay();
            scoreDisplay.setVisible(true);
        });
    }
}
