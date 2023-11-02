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

public class FollowUpPage_1 {

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
					FollowUpPage_1 window = new FollowUpPage_1();
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
	public FollowUpPage_1() {
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
		
		title = new JLabel("What is Scrum?");
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
		PreviousPageButton.setBackground(new Color(139, 69, 19));
		PreviousPageButton.setBounds(760, 526, 122, 43);
		frame.getContentPane().add(PreviousPageButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 136, 872, 365);
		frame.getContentPane().add(scrollPane);
		
		information = new JTextArea();
		information.setWrapStyleWord(true);
		information.setLineWrap(true);
		information.setText("If you are just getting started, think of Scrum as a way to get work done as a team in small pieces at a time, with continuous experimentation and feedback loops along the way to learn and improve as you go. Scrum helps people and teams deliver value incrementally in a collaborative way. As an agile framework, Scrum provides just enough structure for people and teams to integrate into how they work, while adding the right practices to optimize for their specific needs.  You may be thinking, that sounds great! But, how do I get started? \n \n"
				+ "It starts with understanding the Scrum framework which is defined in The Scrum Guide and was first introduced to the world in 1995 as a better way of team collaboration for solving complex problems.  The Scrum framework is fairly simple being made up of a Scrum Team consisting of a Product Owner, a Scrum Master and Developers, each of which have specific accountabilities. The Scrum Team takes part in five events and produces three artifacts. Scrum co-creators Ken Schwaber and Jeff Sutherland wrote and maintain The Scrum Guide, which explains Scrum clearly and succinctly.  The guide contains the definition of Scrum,  describing the Scrum accountabilities, events, artifacts and the guidance that binds them together. \n \n"
				+ "So, why is it called Scrum? People often ask, “Is Scrum an acronym for something?” and the answer is no. It is actually inspired by a scrum in the sport of rugby. In rugby, the team comes together in what they call a scrum to work together to move the ball forward. In this context, Scrum is where the team comes together to move the product forward.\n \n"
				+ "Scrum is an empirical process, where decisions are based on observation, experience and experimentation. Scrum has three pillars: transparency, inspection and adaptation. This supports the concept of working iteratively. Think of Empiricism as working through small experiments, learning from that work and adapting both what you are doing and how you are doing it as needed.\n \n"
				+ "One critical Scrum Team characteristic that binds all of the elements together is Trust. If Trust is not present on a Scrum Team, there will likely be tension and bottlenecks in the way of getting work done. The Scrum Values are also critical for Scrum Teams to adhere to as they help to guide how you work and drive trust. The Scrum Values of Courage, Focus, Commitment, Respect, and Openness, are all important elements that Scrum Team members must consider when working together. The Scrum Values are particularly important in environments where experimentation is core to making progress.\n \n"
				+ "Scrum operates within a framework that necessitates the delivery of valuable work in short, iterative cycles (Sprints) lasting a month or less. This approach encourages ongoing feedback loops, facilitating continual inspection and adaptation of both the process and the deliverables throughout each Sprint. Central to the Scrum approach is the collaborative effort of a designated Scrum Team comprising a Product Owner, Scrum Master, and Developers, jointly accountable for converting selected work into a valuable Increment during each Sprint. This team, along with stakeholders such as business representatives, users, and customers, actively engage in inspecting the Sprint's outcomes and iteratively adjusting and planning for subsequent Sprints, ensuring the alignment of delivered work with evolving needs and expectations.\n \n"
				+ "\"Professional Scrum\" delves beyond the surface of merely adhering to the structural elements of the Scrum framework. It acknowledges that effectiveness within Scrum demands a shift in mindset and approaches to working and thinking, fostering an environment conducive to this change, notably underpinned by trust. Going beyond the mechanical application, it necessitates embracing the Scrum Values within everyday work practices. This mindset shift, which embodies a deeper commitment to the principles of Scrum, is what constitutes \"Professional Scrum.\"");
		scrollPane.setViewportView(information);
	    
		frame.setBounds(100, 100, 908, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
 