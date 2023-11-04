package ScrumPlay_FrontEnd;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FollowUpPage_5 extends JFrame{

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
					FollowUpPage_5 window = new FollowUpPage_5();
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
	public FollowUpPage_5() {
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
		frame.setBounds(100, 100, 1038, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		logo = new JLabel("");
//		Image img = new ImageIcon(this.getClass().getResource("/ScrumPlay Logo.jpg")).getImage();
//		logo.setIcon(new ImageIcon(img));
//		logo.setBounds(10, 24, 100, 100);
//		frame.getContentPane().add(logo);
		
		title = new JLabel("How to Manage Sprint Efficiently?");
		title.setBackground(Color.BLUE);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 872, 100);
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
		scrollPane.setBounds(20, 133, 872, 365);
		frame.getContentPane().add(scrollPane);
		
		information = new JTextArea();
		information.setWrapStyleWord(true);
		information.setLineWrap(true);
		information.setText("Why is Sprint Planning Meeting Required? \r\n"
				+ "Sprint planning meetings set the tone for all the work that is performed in a particular sprint. These sessions contextualize the work of past sprints with respect to future ones to ensure timely delivery of all end-products. They also make it clear to all those involved the expectations from the final product, and the procedures that will be employed to achieve them.\n\n"
				+ "Involve the Team\r\n"
				+ "Dysfunctional sprint meetings most commonly result from dissatisfied teams who feel excluded from the process of planning. This is where the scrum master must step in and exercise his influence to involve all members of the team to include them and their skill sets in a mutually beneficial way.\n\n"
				+ "Use Inputs From Previous Sprints \r\n"
				+ "If this isn’t the first sprint in the process of development, feedback from the previous sprints can help a team repeat and avoid past mistakes for seamless and smoother development. The past sprint reviews and sprint retrospectives are immensely useful here.\n \n"
				+ "Ensure the Availability of Your Team\r\n"
				+ "This point is especially relevant at times of the year when holiday seasons are approaching, rendering team members unavailable for significant portions of the sprint.\n \n"
				+ "Update Your User Stories\r\n"
				+ "Grooming your user stories along with the backlog is a great way to see your project from the perspective of the potential end-user, and the difficulties they might face in using your product.\n \n"
				+ "Realistically Assess Story Points\r\n"
				+ "Story points are abstract estimations of how difficult actualizing a particular user story is depending on the composition of your scrum team. You can use this to gauge your velocity as well. \n \n"
				+ "Break Stories Down Into Tasks\r\n"
				+ "Many find it useful to translate stories into tasks, which in turn helps them estimate how long it would take them to complete it. The tasks have to be broken down into meaningful activities to communicate the real goal. The tasks should be outlined in such a way that it is easy to tick off the activities completed.\n\n"
				+ "Time Your Meetings Effectively\r\n"
				+ "Restrict your planning meetings to 8 hours divided over 30 days for a month-long sprint. For shorter ones, you should adjust the time accordingly. Here the entire team commits to perform their set of activities to achieve the end product to be presented in the market.\n \n"
				+ "Clearly Define What It Means For the Project to Be ‘Done’\r\n"
				+ "This must be done with all three cogs of a sprint planning meeting, working together to determine the exact requisites that need to be met for the sprint to be successful.\n \n"
				+ "Spell Out All Acronyms At The Start of Your Documents\r\n"
				+ "This is a relatively unknown but immensely useful way of saving time at meetings by avoiding re-explaining things in case some team members are unaware of particular references.\n \n");
		scrollPane.setViewportView(information);
	    
		frame.setVisible(true);
	}
}
 