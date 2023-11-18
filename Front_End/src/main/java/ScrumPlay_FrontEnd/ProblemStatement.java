
package ScrumPlay_FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class ProblemStatement extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JLabel lblNewLabel_1;
    JTextArea textArea;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ProblemStatement frame = new ProblemStatement();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public ProblemStatement() {
        setTitle("Problem Statement");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("Start Sprint Planning");
        btnNewButton.setBounds(244, 236, 200, 30);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Your Problem Statement:");
        lblNewLabel.setBounds(6, 6, 200, 20);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setBounds(6, 29, 428, 46);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Notes Regarding Problem Statement:");
        lblNewLabel_2.setBounds(6, 101, 235, 16);
        contentPane.add(lblNewLabel_2);

        textArea = new JTextArea();
        textArea.setBounds(6, 123, 428, 51);
        contentPane.add(textArea);

        JButton btnNewButton_1 = new JButton("Change Problem Statement");
        btnNewButton_1.setBounds(6, 237, 215, 29);
        contentPane.add(btnNewButton_1);

        String apiUrl = "http://localhost:8080/statements/1";
        String DefaultProblemStatement = sendGetRequestToBackend(apiUrl);
        Map<String, Object> defaultStatementMap = jsonToMap(DefaultProblemStatement);

        Object statementValue = defaultStatementMap.get("statement");

        if (statementValue == null) {
            System.out.println("Key 'statement' not found in the map.");
        }
        lblNewLabel_1.setText("<html>" + statementValue.toString() + "</html");

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String apiUrl = "http://localhost:8080/statements";
                String problemStatements = sendGetRequestToBackend(apiUrl);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ScrumPlay_FrontEnd.SelectProblemStatement selectProblemStatement = new ScrumPlay_FrontEnd.SelectProblemStatement(problemStatements);
                        selectProblemStatement.addSelectionListener(new ScrumPlay_FrontEnd.SelectionListener() {
                            @Override
                            public void onSelection(String selectedProblemStatement, String comments) {
                                lblNewLabel_1.setText("<html>" + selectedProblemStatement + "</html");
                                textArea.setText(comments);
                            }
                        });
                        selectProblemStatement.setVisible(true);
                    }
                });
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScrumPlay_FrontEnd.ProductBacklog newFrame = new ScrumPlay_FrontEnd.ProductBacklog();
                newFrame.setVisible(true);
            }
        });
    }

    public static ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
        ArrayList<String> statementsAndComments = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("statement")) {
                String statement = jsonObject.getString("statement");
                if (jsonObject.has("comments")) {
                    String comments = jsonObject.getString("comments");
                    statement += " (Comments: " + comments + ")";
                }
                statementsAndComments.add(statement);
            }
        }

        return statementsAndComments;
    }public static Map<String, Object> jsonToMap(String jsonString) {
        return new JSONObject(jsonString).toMap();
    }

    public String sendGetRequestToBackend(String apiUrl) {
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
}
