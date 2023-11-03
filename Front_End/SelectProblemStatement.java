import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectProblemStatement extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> problemStatementList;
    private JButton okButton;

    private SelectionListener selectionListener;

    public SelectProblemStatement(String problemStatements) {
        setTitle("Select Problem Statement");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Select a Problem Statement:");
        lblNewLabel.setBounds(10, 10, 200, 20);
        contentPane.add(lblNewLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Convert the JSON string to an ArrayList of statements
        ArrayList<Map<String, Object>> mapStComments = jsonStringToList(problemStatements);
        ArrayList<String> statements = extractStatements(mapStComments);

        JSONArray jsonArray = new JSONArray();
        for (String statement : statements) {
            System.out.println(statement);
            listModel.addElement(statement);
        }

        problemStatementList = new JList<>(listModel);
        problemStatementList.setBounds(10, 40, 380, 150);
        contentPane.add(problemStatementList);

        okButton = new JButton("OK");
        okButton.setBounds(150, 200, 100, 30);
        contentPane.add(okButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProblemStatement = problemStatementList.getSelectedValue();
                if (selectedProblemStatement != null) {
                    // You need to get the associated comments here
                    String comments = getCommentsForStatement(selectedProblemStatement, mapStComments);

                    if (selectionListener != null) {
                        selectionListener.onSelection(selectedProblemStatement, comments);
                    }
                    dispose();
                }
            }
        });
    }

//    private Map<String, String> jsonStringToMap(String jsonString) {
//        Map<String, String> statements = new Map<>();
//    }

    public void addSelectionListener(SelectionListener listener) {
        this.selectionListener = listener;
    }

    private ArrayList<String> extractStatements(ArrayList<Map<String, Object>> jsonArr) {
        ArrayList<String> extractedStatements = new ArrayList<>();

        for (Map<String, Object> statement : jsonArr) {
            Object statementValue = statement.get("statement");
            if (statementValue != null) {
                extractedStatements.add(statementValue.toString());
            }
        }

        return extractedStatements;
    }

    private ArrayList<Map<String, Object>> jsonStringToList(String jsonString) {
        ArrayList<Map<String, Object>> statements = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Map<String, Object> statementMap = new HashMap<>();
                statementMap.put("statementid", jsonObject.getInt("statementid"));
                statementMap.put("comments", jsonObject.getString("comments"));
                statementMap.put("statement", jsonObject.getString("statement"));
                statementMap.put("numOfUserStories", jsonObject.getInt("numOfUserStories"));

                statements.add(statementMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return statements;
    }

    // Implement a method to get comments for the selected problem statement
    private String getCommentsForStatement(String selectedStatement, ArrayList<Map<String, Object>> mapStComments) {
        System.out.println(selectedStatement);
        for (Map<String, Object> comment : mapStComments) {
            String statement = comment.get("statement").toString();
            if (selectedStatement.equals(statement)) {
                String comments = comment.get("comments").toString();
                return comments;
            }
        }
        return selectedStatement;
    }
}
