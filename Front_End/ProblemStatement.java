import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProblemStatement extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel_1;
    private JTextArea textArea;

    private static final String DEFAULT_PROBLEM_STATEMENT = "Build a Scrum Simulator and Training Tool to help users understand the Agile methodology.";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProblemStatement frame = new ProblemStatement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

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
        lblNewLabel_1.setText("<html>" + DEFAULT_PROBLEM_STATEMENT + "</html>");
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

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductBacklog newFrame = new ProductBacklog();
                newFrame.setVisible(true);
            }
        });
        
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String apiUrl = "http";
                String problemStatements = sendGetRequestToBackend(apiUrl);
                
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        SelectProblemStatement selectProblemStatement = new SelectProblemStatement(problemStatements);
                        selectProblemStatement.addSelectionListener(new SelectionListener() {
                            @Override
                            public void onSelection(String selectedProblemStatement) {
                                lblNewLabel_1.setText("<html>" + selectedProblemStatement + "</html>");
                            }
                        });
                        selectProblemStatement.setVisible(true);
                    }
                });
            }
        });
    }
    
    private String sendGetRequestToBackend(String apiUrl) {
        try {
            @SuppressWarnings("deprecation")
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

interface SelectionListener {
    void onSelection(String selectedProblemStatement);
}
