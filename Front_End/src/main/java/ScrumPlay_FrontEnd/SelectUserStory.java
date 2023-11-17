package ScrumPlay_FrontEnd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectUserStory extends JFrame{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> userStoryList;
    private JButton addButton;

    private SelectionListener selectionListener;

    public SelectUserStory(String userStory) {
        setTitle("Select User Story");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usNewLabel = new JLabel("Select a User Story");
        usNewLabel.setBounds(10, 10, 200, 20);
        contentPane.add(usNewLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Map<String, Object>> mapStComments = jsonStringToList(userStory);
        ArrayList<String> statements = extractStatements(mapStComments);

        JSONArray jsonArray = new JSONArray();
        for (String story : statements) {
            System.out.println(story);
            listModel.addElement(story);
        }

        userStoryList = new JList<>(listModel);
        userStoryList.setBounds(10, 40, 380, 150);
        contentPane.add(userStoryList);

        addButton = new JButton("OK");
        addButton.setBounds(150, 200, 100, 30);
        contentPane.add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedUserStory = userStoryList.getSelectedValue();
                if (selectedUserStory != null) {
                    // You need to get the associated comments here
                    String comments = getCommentsForStatement(selectedUserStory, mapStComments);

                    if (selectionListener != null) {
                        selectionListener.onSelection(selectedUserStory, comments);
                    }
                    dispose();
                }
            }
        });

    }
    public void addSelectionListener(SelectionListener listener) {
        this.selectionListener = listener;
    }

    public static ArrayList<String> extractStatements(ArrayList<Map<String, Object>> jsonArr) {
        ArrayList<String> extractedStatements = new ArrayList<>();

        for (Map<String, Object> statement : jsonArr) {
            Object statementValue = statement.get("statement");
            if (statementValue != null) {
                extractedStatements.add(statementValue.toString());
            }
        }

        return extractedStatements;
    }

    public static ArrayList<Map<String, Object>> jsonStringToList(String jsonString) {
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

    private String getCommentsForStatement(String selectedStatement, ArrayList<Map<String, Object>> mapStComments) {
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

