import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class configPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textGameID;
	private JTextField textTeamSize;
	private JTextField txtName1;
	private JLabel lblName1;
	private JLabel lblRole1;
	private JTextField txtRole1;
	private JLabel lblName2;
	private JTextField txtName2;
	private JLabel lblRole2;
	private JTextField txtRole2;
	private JLabel lblPlayer3;
	private JLabel lblName3;
	private JTextField txtName3;
	private JLabel lblRole3;
	private JTextField txtRole3;
	private JLabel lblPlayer4;
	private JLabel lblName4;
	private JTextField txtName4;
	private JLabel lblRole4;
	private JTextField txtRole4;
	private JLabel lblPlayer5;
	private JLabel lblName5;
	private JTextField txtName5;
	private JLabel lblRole5;
	private JTextField txtRole5;
	private JLabel lblSprintLength;
	private JTextField txtSprintLength;
	private JLabel lblScrumCallLength;
	private JTextField txtScrumCallLength;

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
		setBounds(100, 100, 1057, 736);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlayerDetails = new JLabel("Player Details");
		lblPlayerDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayerDetails.setBounds(140, 119, 125, 26);
		contentPane.add(lblPlayerDetails);
		
		JLabel lblGameID = new JLabel("Game ID");
		lblGameID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGameID.setBounds(140, 23, 79, 26);
		contentPane.add(lblGameID);
		
		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayer1.setBounds(140, 144, 74, 26);
		contentPane.add(lblPlayer1);
		
		JLabel lblTeamSize = new JLabel("Team Size");
		lblTeamSize.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTeamSize.setBounds(140, 63, 92, 26);
		contentPane.add(lblTeamSize);
		
		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayer2.setBounds(140, 222, 79, 26);
		contentPane.add(lblPlayer2);
		
		textGameID = new JTextField();
		textGameID.setBounds(355, 23, 147, 20);
		contentPane.add(textGameID);
		textGameID.setColumns(10);
		
		textTeamSize = new JTextField();
		textTeamSize.setColumns(10);
		textTeamSize.setBounds(355, 63, 147, 20);
		contentPane.add(textTeamSize);
		
		txtName1 = new JTextField();
		txtName1.setColumns(10);
		txtName1.setBounds(355, 181, 147, 20);
		contentPane.add(txtName1);
		
		lblName1 = new JLabel("Name");
		lblName1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName1.setBounds(140, 181, 74, 14);
		contentPane.add(lblName1);
		
		lblRole1 = new JLabel("Role");
		lblRole1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRole1.setBounds(574, 181, 49, 14);
		contentPane.add(lblRole1);
		
		txtRole1 = new JTextField();
		txtRole1.setBounds(633, 181, 147, 20);
		contentPane.add(txtRole1);
		txtRole1.setColumns(10);
		
		lblName2 = new JLabel("Name");
		lblName2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName2.setBounds(140, 259, 74, 14);
		contentPane.add(lblName2);
		
		txtName2 = new JTextField();
		txtName2.setColumns(10);
		txtName2.setBounds(355, 259, 147, 20);
		contentPane.add(txtName2);
		
		lblRole2 = new JLabel("Role");
		lblRole2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRole2.setBounds(574, 259, 49, 14);
		contentPane.add(lblRole2);
		
		txtRole2 = new JTextField();
		txtRole2.setColumns(10);
		txtRole2.setBounds(633, 259, 147, 20);
		contentPane.add(txtRole2);
		
		lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayer3.setBounds(140, 300, 79, 26);
		contentPane.add(lblPlayer3);
		
		lblName3 = new JLabel("Name");
		lblName3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName3.setBounds(140, 337, 74, 14);
		contentPane.add(lblName3);
		
		txtName3 = new JTextField();
		txtName3.setColumns(10);
		txtName3.setBounds(355, 337, 147, 20);
		contentPane.add(txtName3);
		
		lblRole3 = new JLabel("Role");
		lblRole3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRole3.setBounds(574, 337, 49, 14);
		contentPane.add(lblRole3);
		
		txtRole3 = new JTextField();
		txtRole3.setColumns(10);
		txtRole3.setBounds(633, 337, 147, 20);
		contentPane.add(txtRole3);
		
		lblPlayer4 = new JLabel("Player 4");
		lblPlayer4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayer4.setBounds(140, 378, 79, 26);
		contentPane.add(lblPlayer4);
		
		lblName4 = new JLabel("Name");
		lblName4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName4.setBounds(140, 415, 74, 14);
		contentPane.add(lblName4);
		
		txtName4 = new JTextField();
		txtName4.setColumns(10);
		txtName4.setBounds(355, 415, 147, 20);
		contentPane.add(txtName4);
		
		lblRole4 = new JLabel("Role");
		lblRole4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRole4.setBounds(574, 415, 49, 14);
		contentPane.add(lblRole4);
		
		txtRole4 = new JTextField();
		txtRole4.setColumns(10);
		txtRole4.setBounds(633, 415, 147, 20);
		contentPane.add(txtRole4);
		
		lblPlayer5 = new JLabel("Player 5");
		lblPlayer5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlayer5.setBounds(140, 458, 79, 26);
		contentPane.add(lblPlayer5);
		
		lblName5 = new JLabel("Name");
		lblName5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName5.setBounds(140, 495, 74, 14);
		contentPane.add(lblName5);
		
		txtName5 = new JTextField();
		txtName5.setColumns(10);
		txtName5.setBounds(355, 495, 147, 20);
		contentPane.add(txtName5);
		
		lblRole5 = new JLabel("Role");
		lblRole5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRole5.setBounds(574, 495, 49, 14);
		contentPane.add(lblRole5);
		
		txtRole5 = new JTextField();
		txtRole5.setColumns(10);
		txtRole5.setBounds(633, 495, 147, 20);
		contentPane.add(txtRole5);
		
		lblSprintLength = new JLabel("Sprint Length");
		lblSprintLength.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSprintLength.setBounds(140, 543, 125, 26);
		contentPane.add(lblSprintLength);
		
		txtSprintLength = new JTextField();
		txtSprintLength.setColumns(10);
		txtSprintLength.setBounds(355, 543, 147, 20);
		contentPane.add(txtSprintLength);
		
		lblScrumCallLength = new JLabel("Scrum Call Length");
		lblScrumCallLength.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblScrumCallLength.setBounds(140, 580, 163, 26);
		contentPane.add(lblScrumCallLength);
		
		txtScrumCallLength = new JTextField();
		txtScrumCallLength.setColumns(10);
		txtScrumCallLength.setBounds(355, 580, 147, 20);
		contentPane.add(txtScrumCallLength);
		
		JButton btnSatrtSprint = new JButton("Start Sprint");
		btnSatrtSprint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSatrtSprint.setBounds(424, 622, 167, 23);
		contentPane.add(btnSatrtSprint);
	}
}
