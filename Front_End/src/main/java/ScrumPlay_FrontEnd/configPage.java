package ScrumPlay_FrontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class configPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblGameID;
	private JTextField txtGameID;
	private JLabel lblSprintLength;
	private JLabel lblTeamSize;
	private JTextField txtSprintLength;
	private JLabel lblScrumCallLength;
	private JTextField txtScrumCallLength;
	private JTextField txtTeamSize;
	private JTextField[] txtplayerNames;
	private JComboBox<String>[] ddplayerRoles;
	private JButton btnStartSprint;
	private int yOffset;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScrumPlay_FrontEnd.configPage frame = new ScrumPlay_FrontEnd.configPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public configPage() {
		setTitle("Game Config Setup");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblGameID = new JLabel("Game ID");
		lblGameID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGameID.setBounds(50, 50, 200, 25);
		contentPane.add(lblGameID);
		
		txtGameID = new JTextField();
		txtGameID.setBounds(250, 50, 100, 25);
		contentPane.add(txtGameID);

		lblSprintLength = new JLabel("Sprint Length:");
		lblSprintLength.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSprintLength.setBounds(50, 100, 150, 25);
		contentPane.add(lblSprintLength);

		txtSprintLength = new JTextField();
		txtSprintLength.setBounds(250, 100, 100, 25);
		contentPane.add(txtSprintLength);
		
		lblScrumCallLength = new JLabel("Scrum Call Length:");
		lblScrumCallLength.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblScrumCallLength.setBounds(50, 150, 200, 25);
		contentPane.add(lblScrumCallLength);


		txtScrumCallLength = new JTextField();
		txtScrumCallLength.setBounds(250, 150, 100, 25);
		contentPane.add(txtScrumCallLength);


		lblTeamSize = new JLabel("Team Size:");
		lblTeamSize.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTeamSize.setBounds(50, 200, 100, 25);
		contentPane.add(lblTeamSize);

		txtTeamSize = new JTextField();
		txtTeamSize.setBounds(250, 200, 50, 25);
		contentPane.add(txtTeamSize);

		JButton btnAddPlayers = new JButton("Add Players");
		btnAddPlayers.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddPlayers.setBounds(410, 200, 150, 30);
		contentPane.add(btnAddPlayers);

		btnAddPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int teamSize = Integer.parseInt(txtTeamSize.getText());
				initializePlayerDetails(teamSize);
			}
		});
		
		JButton btnStartSprint = new JButton("Start Sprint");
		btnStartSprint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStartSprint.setBounds(300, 600, 150, 25);
		contentPane.add(btnStartSprint);
		btnStartSprint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sendDataToBackend();
				ProblemStatement problemStatementFrame = new ProblemStatement();
				problemStatementFrame.setVisible(true);
			}			
		});	
	}

	private void initializePlayerDetails(int teamSize) {
		txtplayerNames = new JTextField[teamSize];
		ddplayerRoles = new JComboBox[teamSize];

		yOffset = 250;

		for (int i = 0; i < teamSize; i++) {
			JLabel lblPlayer = new JLabel("Player " + (i + 1) + ":");
			lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblPlayer.setBounds(50, yOffset, 100, 25);
			contentPane.add(lblPlayer);

			txtplayerNames[i] = new JTextField();
			txtplayerNames[i].setBounds(250, yOffset, 150, 25);
			contentPane.add(txtplayerNames[i]);

			ddplayerRoles[i] = new JComboBox<>();
			ddplayerRoles[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			ddplayerRoles[i].setModel(new DefaultComboBoxModel<>(new String[]{"--Select Role--", "Product Owner", "Scrum Master", "Developer"}));
			ddplayerRoles[i].setSelectedIndex(0);
			ddplayerRoles[i].setBounds(410, yOffset, 150, 25);
			contentPane.add(ddplayerRoles[i]);

			yOffset += 40;
		}
		

	}
	
	   private JSONObject prepareJsonData() {
	        JSONObject jsonData = new JSONObject();
	        jsonData.put("gameId", txtGameID.getText());
	        jsonData.put("sprintLength", txtSprintLength.getText());
	        jsonData.put("scrumCallLength", txtScrumCallLength.getText());
	        jsonData.put("teamSize", txtTeamSize.getText());

	        JSONArray playersArray = new JSONArray();
	        for (int i = 0; i < txtplayerNames.length; i++) {
	            JSONObject playerJson = new JSONObject();
	            playerJson.put("playerName", txtplayerNames[i].getText());
	            playerJson.put("playerRole", ddplayerRoles[i].getSelectedItem().toString());
	            playersArray.put(playerJson);
	        }
	        jsonData.put("players", playersArray);

	        return jsonData;
	    }
	

	private void sendDataToBackend() {
        try {
            URL url = new URL("http://localhost:8080/add-gameConfig");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject jsonData = prepareJsonData();
			System.out.println(jsonData);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(this, "Data sent successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to send data to the backend. Please try again later.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred. Please check your network connection.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 
		
}
