import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel; 
public class SelectProblemStatement extends JFrame {
    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JList<String> problemStatementList;
    private JButton okButton;

    private SelectionListener selectionListener;

    public SelectProblemStatement(String problemStatements) {
        setTitle("Select Problem Statement");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Select a Problem Statement:");
        lblNewLabel.setBounds(10, 10, 200, 20);
        contentPane.add(lblNewLabel);

        DefaultListModel<String> listModel = new DefaultListModel<>();

        String[] statements = problemStatements.split("<br>");
        for (String statement : statements) {
            listModel.addElement(statement);
        }

        problemStatementList = new JList<>(listModel);
        problemStatementList.setBounds(10, 40, 380, 150);
        contentPane.add(problemStatementList);

        okButton = new JButton("OK");
        okButton.setBounds(150, 200, 100, 30);
        contentPane.add(okButton);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProblemStatement = problemStatementList.getSelectedValue();
                if (selectedProblemStatement != null) {
                    if (selectionListener != null) {
                        selectionListener.onSelection(selectedProblemStatement);
                    }
                    dispose();
                }
            }
        });
    }

    public void addSelectionListener(SelectionListener listener) {
        this.selectionListener = listener;
    }
}
