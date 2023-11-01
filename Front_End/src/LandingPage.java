import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LandingPage {

	private JFrame frame;
	private JLabel logo;
	private JLabel title;
	private JLabel Description;
	private JTextField SubWindow1;
	private JButton SubButton1;
	private JLabel FooterLogo;
	private JButton NextPageButton;
	private JTextField SubWindow2;
	private JButton SubButton1_1;
	private JTextField SubWindow3;
	private JButton SubButton1_2;
	private JTextField SubWindow4;
	private JButton SubButton1_3;
	private JTextField SubWindow5;
	private JButton SubButton1_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage window = new LandingPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LandingPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().setForeground(new Color(128, 0, 0));
		frame.getContentPane().setBackground(new Color(0, 206, 209));
		frame.getContentPane().setLayout(null);
		
		logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/ScrumPlay Logo.jpg")).getImage();
		logo.setIcon(new ImageIcon(img));
		logo.setBounds(10, 24, 100, 100);
		frame.getContentPane().add(logo);
		
		title = new JLabel("ScrumPlay: A Scrum Game Experience");
		title.setBackground(Color.BLUE);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 770, 100);
		frame.getContentPane().add(title);
		
		Description = new JLabel("Lets Start With a Quick Lesson About Scrum! Click Read More for a Detailed Description...");
		Description.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Description.setBounds(10, 135, 852, 43);
		frame.getContentPane().add(Description);
		
		SubWindow1 = new JTextField();
		SubWindow1.setBackground(Color.CYAN);
		SubWindow1.setFont(new Font("Tahoma", Font.BOLD, 11));
		SubWindow1.setText("What is Scrum?");
		SubWindow1.setBounds(31, 191, 139, 43);
		frame.getContentPane().add(SubWindow1);
		SubWindow1.setColumns(10);
		SubWindow1.setCaretColor(SubWindow1.getBackground());
		
		SubButton1 = new JButton("Read More->");
		SubButton1.setBackground(Color.GRAY);
		SubButton1.setBounds(30, 234, 140, 23);
		frame.getContentPane().add(SubButton1);
		
		FooterLogo = new JLabel("");
		Image FootImg = new ImageIcon(this.getClass().getResource("/ScrumPlay Footer Logo.png")).getImage();
		FooterLogo.setIcon(new ImageIcon(FootImg));
		FooterLogo.setBounds(10, 572, 872, 93);
		frame.getContentPane().add(FooterLogo);
		
		NextPageButton = new JButton("Let's Go!");
		NextPageButton.setBackground(new Color(139, 69, 19));
		NextPageButton.setBounds(760, 526, 122, 43);
		frame.getContentPane().add(NextPageButton);
		
		SubWindow2 = new JTextField();
		SubWindow2.setText("What is Sprint?");
		SubWindow2.setFont(new Font("Tahoma", Font.BOLD, 11));
		SubWindow2.setColumns(10);
		SubWindow2.setCaretColor(Color.CYAN);
		SubWindow2.setBackground(Color.CYAN);
		SubWindow2.setBounds(31, 313, 139, 43);
		frame.getContentPane().add(SubWindow2);
		
		SubButton1_1 = new JButton("Read More->");
		SubButton1_1.setBackground(Color.GRAY);
		SubButton1_1.setBounds(31, 357, 139, 23);
		frame.getContentPane().add(SubButton1_1);
		
		SubWindow3 = new JTextField();
		SubWindow3.setText("What is Sprint Backlog?");
		SubWindow3.setFont(new Font("Tahoma", Font.BOLD, 11));
		SubWindow3.setColumns(10);
		SubWindow3.setCaretColor(Color.CYAN);
		SubWindow3.setBackground(Color.CYAN);
		SubWindow3.setBounds(31, 449, 139, 43);
		frame.getContentPane().add(SubWindow3);
		
		SubButton1_2 = new JButton("Read More->");
		SubButton1_2.setBackground(Color.GRAY);
		SubButton1_2.setBounds(31, 492, 139, 23);
		frame.getContentPane().add(SubButton1_2);
		
		SubWindow4 = new JTextField();
		SubWindow4.setText("What are Burndown Charts and Sprint Velocity?");
		SubWindow4.setFont(new Font("Tahoma", Font.BOLD, 11));
		SubWindow4.setColumns(10);
		SubWindow4.setCaretColor(Color.CYAN);
		SubWindow4.setBackground(Color.CYAN);
		SubWindow4.setBounds(270, 191, 275, 43);
		frame.getContentPane().add(SubWindow4);
		
		SubButton1_3 = new JButton("Read More->");
		SubButton1_3.setBackground(Color.GRAY);
		SubButton1_3.setBounds(270, 234, 275, 23);
		frame.getContentPane().add(SubButton1_3);
		
		SubWindow5 = new JTextField();
		SubWindow5.setText("What to Manage Sprint Efficiently?");
		SubWindow5.setFont(new Font("Tahoma", Font.BOLD, 11));
		SubWindow5.setColumns(10);
		SubWindow5.setCaretColor(Color.CYAN);
		SubWindow5.setBackground(Color.CYAN);
		SubWindow5.setBounds(270, 313, 275, 43);
		frame.getContentPane().add(SubWindow5);
		
		SubButton1_4 = new JButton("Read More->");
		SubButton1_4.setBackground(Color.GRAY);
		SubButton1_4.setBounds(270, 357, 275, 23);
		frame.getContentPane().add(SubButton1_4);
		frame.setBounds(100, 100, 908, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
 