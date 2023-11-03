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

public class FollowUpPage_2 extends JFrame{

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
					FollowUpPage_2 window = new FollowUpPage_2();
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
	public FollowUpPage_2() {
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
		
		logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/ScrumPlay Logo.jpg")).getImage();
		logo.setIcon(new ImageIcon(img));
		logo.setBounds(10, 24, 100, 100);
		frame.getContentPane().add(logo);
		
		title = new JLabel("What is Sprint?");
		title.setBackground(Color.BLUE);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 770, 100);
		frame.getContentPane().add(title);
		
		FooterLogo = new JLabel("");
		Image FootImg = new ImageIcon(this.getClass().getResource("/ScrumPlay Footer Logo.png")).getImage();
		FooterLogo.setIcon(new ImageIcon(FootImg));
		FooterLogo.setBounds(10, 572, 872, 93);
		frame.getContentPane().add(FooterLogo);
		
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
		information.setText("Sprints are the fundamental building blocks in agile methodologies like scrum, consisting of short, time-bound periods during which a scrum team works on completing a defined amount of work. They form the iterative approach of breaking down complex projects into manageable segments, allowing teams to deliver increments of working software regularly. While often associated with agile software development, scrum itself is a framework within the broader agile principles, emphasizing the values of adaptability and responsiveness. The Scrum Guide provides the foundational structure for sprints, fostering transparency, inspection, and adaptation. Through sprints, teams align with agile's ethos of frequent deliverables and prioritizing flexibility over rigid planning, ultimately facilitating the efficient delivery of high-quality products.\n \n"
				+ "Sprint planning sets the stage for each sprint, involving the product owner, scrum master, and the development team in selecting work items from the product backlog to achieve the sprint goal, forming the sprint backlog. Throughout the sprint, the daily scrum meetings track progress, aiming to identify and resolve any blockers. The sprint concludes with a sprint review, where the team showcases completed work, followed by a retrospective meeting that allows the team to reflect on the sprint, identifying areas for improvement. This cyclical process ensures a collaborative and iterative approach to achieving goals and refining strategies for subsequent sprints.\n \n"
				+ "To ensure effective sprint execution, it's crucial for the team to establish and comprehend the sprint goal while defining measurable success criteria. Alignment and progress toward a shared objective are key. A well-organized backlog with clear priorities and dependencies is essential to prevent process derailment. Managing it meticulously is crucial. Understanding the team's velocity, accounting for factors such as leaves and meetings, is vital for accurate planning. Utilize the sprint planning meeting to delve into the granular details of the tasks, encouraging team members to outline work for stories, bugs, and tasks slated for the sprint. Omit work that relies on unresolved dependencies, such as tasks from other teams or pending approvals. After decisions are made, ensure that the information and the reasoning behind it are documented in your project management or collaboration tool, like Jira tickets, facilitating transparency and easy accessibility for the entire team in the future. \n \n"
				+ "When planning sprints, avoid overloading with excessive stories or overestimating velocity, ensuring tasks are achievable within the sprint to prevent setting up for failure. Remember to prioritize quality and address technical debt, allocating time for QA, bug fixes, and general engineering upkeep. Clarify sprint contents to prevent ambiguity, emphasizing alignment over speed. Mitigate risk by breaking down uncertain or large tasks, leaving some for future sprints. Address team concerns promptly, whether about velocity, uncertain work, or underestimated tasks, and recalibrate plans as needed to ensure a successful sprint.\n \n"
				+ "Optimizing sprint processes in Jira involves leveraging automation to streamline tasks. Three common automation rules include sending a weekly Slack message listing all open sprint issues, automatically assigning unfinished issues to the next sprint upon sprint completion, and facilitating the movement of issues to the next active sprint if they transition to 'In Progress' and the current sprint is empty. These rules enhance efficiency by ensuring better tracking, timely assignment, and smoother transitions within the sprint cycles.");
		scrollPane.setViewportView(information);
	    
		frame.setVisible(true);
	}
}
 