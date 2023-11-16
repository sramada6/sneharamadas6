package ScrumPlay_FrontEnd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FollowUpPage_3 extends JFrame{

	private JFrame frame;
	private JLabel logo;
	private JLabel title;
	private JLabel FooterLogo;
	private JButton PreviousPageButton;
	private JTextArea information;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FollowUpPage_3 window = new FollowUpPage_3();
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
	public FollowUpPage_3() {
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
		frame.setBounds(100, 100, 908, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		logo = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/ScrumPlay Logo.jpg")).getImage();
//		logo.setIcon(new ImageIcon(img));
//		logo.setBounds(10, 24, 100, 100);
//		frame.getContentPane().add(logo);
		
		title = new JLabel("What is Sprint Backlog?");
		title.setBackground(Color.BLUE);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 770, 100);
		frame.getContentPane().add(title);
		
//		FooterLogo = new JLabel("");
//		Image FootImg = new ImageIcon(this.getClass().getResource("/ScrumPlay Footer Logo.png")).getImage();
//		FooterLogo.setIcon(new ImageIcon(FootImg));
//		FooterLogo.setBounds(10, 572, 872, 93);
//		frame.getContentPane().add(FooterLogo);
		
		PreviousPageButton = new JButton("Back");
		PreviousPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LandingPage newFrame = new LandingPage();
				newFrame.setVisible(true);
			}
		});
		PreviousPageButton.setBackground(new Color(139, 69, 19));
		PreviousPageButton.setBounds(760, 526, 122, 43);
		frame.getContentPane().add(PreviousPageButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 136, 872, 365);
		frame.getContentPane().add(scrollPane);
		
		information = new JTextArea();
		information.setWrapStyleWord(true);
		information.setLineWrap(true);
		information.setText("The sprint backlog is a list of tasks identified by the Scrum team to be completed during the Scrum sprint. During the sprint planning meeting, the team selects some number of product backlog items, usually in the form of user stories, and identifies the tasks necessary to complete each user story. Most teams also estimate how many hours each task will take someone on the team to complete.\n \n"
				+ "It's critical that the team selects the items and size of the sprint backlog. Because they are the people committing to completing the tasks, they must be the people to choose what they are committing to during the Scrum sprint.\n \n"
				+ "The sprint backlog is commonly maintained as a spreadsheet, but it is also possible to use your defect tracking system or any of a number of software products designed specifically for Scrum or agile.\n \n"
				+ "During the Scrum sprint, team members are expected to update the sprint backlog as new information is available, but minimally once per day. Many teams will do this during the daily scrum. Once each day, the estimated work remaining in the sprint is calculated and graphed by the ScrumMaster, resulting in a sprint burndown chart like this one.\n \n"
				+ "The team does its best to pull the right amount of work into the Scrum sprint, but sometimes too much or too little work is pulled in during planning. In this case, the team needs to add or remove tasks.\n \n");
		scrollPane.setViewportView(information);
		
		frame.setVisible(true);
	}
}
 