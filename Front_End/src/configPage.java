import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					configPage frame = new configPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
	}

	private void initializePlayerDetails(int teamSize) {
		txtplayerNames = new JTextField[teamSize];
		ddplayerRoles = new JComboBox[teamSize];

		int yOffset = 250;

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
}
