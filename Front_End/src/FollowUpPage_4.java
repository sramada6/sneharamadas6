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

public class FollowUpPage_4 {

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
					FollowUpPage_4 window = new FollowUpPage_4();
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
	public FollowUpPage_4() {
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
		
		title = new JLabel("What is Burndown Charts & Sprint Velocity?");
		title.setBackground(Color.BLUE);
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 872, 100);
		frame.getContentPane().add(title);
		
		FooterLogo = new JLabel("");
		Image FootImg = new ImageIcon(this.getClass().getResource("/ScrumPlay Footer Logo.png")).getImage();
		FooterLogo.setIcon(new ImageIcon(FootImg));
		FooterLogo.setBounds(10, 572, 872, 93);
		frame.getContentPane().add(FooterLogo);
		
		PreviousPageButton = new JButton("Back");
		PreviousPageButton.setBackground(new Color(139, 69, 19));
		PreviousPageButton.setBounds(760, 526, 122, 43);
		frame.getContentPane().add(PreviousPageButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 133, 872, 365);
		frame.getContentPane().add(scrollPane);
		
		information = new JTextArea();
		information.setWrapStyleWord(true);
		information.setLineWrap(true);
		information.setText("The Scrum Burndown Chart is a visual measurement tool that shows the completed work per day against the projected rate of completion for the current project release. Its purpose is to enable that the project is on the track to deliver the expected solution within the desired schedule.\n \n"
				+ "The rate of progress of a Scrum Team is called \"velocity\". It expresses the amount of e.g. story points completed per iteration. An import rule for calculating the velocity is that only stories that are completed at the end of the iteration are counted. Counting partially finished work (e.g. coding only - test missing) is strictly forbidden.\n \n"
				+ "After a few Sprints the velocity of a Scrum Team will most likely be predictable and would allow quite accurate estimation about the time needed until all entries in the Scrum Product Backlog will be completed. If the velocity of a Scrum Team is e.g. 30 story points and the total amount of remaining work is 155, we can predict that we need about 6 Sprint to complete all stories in the Backlog.\n \n"
				+ "However in reality the entries in the Scrum Product Backlog will change over the duration of the project. New stories are added and other stories are changed or even deleted. In the simple Burndown Chart the velocity of the Scrum Team and the change in the scope cannot be distinguished. To reflect this, another form of diagram can be used.\n \n"
				+ "Here a bar chart instead of a line diagram is used. The size of each bar represents the total amount of work remaining at the start of each sprint. The velocity of the team is subtracted from the top while changes in the Scope change the bottom of the bar.\n \n"
				+ "To get even more accurate we can also take the rate of changes in total work into account. However we have to be careful when using this model since the rate of change will be high in the beginning of the project but will drop at the end.\n \n");
		scrollPane.setViewportView(information);
	    
		frame.setBounds(100, 100, 1038, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
 