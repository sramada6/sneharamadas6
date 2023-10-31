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

public class LandingPage {

	private JFrame frame;
	private JLabel logo;
	private JLabel title;
	private JLabel Description;

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
		title.setForeground(new Color(128, 0, 0));
		title.setFont(new Font("Tahoma", Font.PLAIN, 45));
		title.setBounds(119, 24, 770, 100);
		frame.getContentPane().add(title);
		
		Description = new JLabel("Lets Start With a Quick Lesson About Scrum! Click Read More for a Detailed Description...");
		Description.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Description.setBounds(10, 135, 852, 43);
		frame.getContentPane().add(Description);
		frame.setBounds(100, 100, 878, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
 