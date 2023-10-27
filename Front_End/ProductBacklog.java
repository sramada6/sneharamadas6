import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProductBacklog extends JFrame {
    private static final long serialVersionUID = 3634750089181129308L;

	public ProductBacklog() {
        setTitle("Product Backlog");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        // Design the UI for the new page here...
        
        // For example, you can add components to the new frame as needed
        JLabel label = new JLabel("");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setBounds(6, 6, 200, 20);
        getContentPane().add(label);
    }
}
