package ScrumPlay_FrontEnd;

import com.google.gson.Gson;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;


public class StoryCard extends JPanel {
    private final String title;
    private final String description;
    private final String status;
    private final String assignedTo;
    private final String storyPoints;
    private final String storyid;

    public StoryCard(String storyid, String title, String description,  String status, String assignedTo, String storyPoints) {
        this.title = title;
        this.storyid = storyid;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.storyPoints = storyPoints;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a JLabel to display the title
        JLabel idLabel = new JLabel("#" + storyid);
        idLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));

//        Map<String, String> assign = new HashMap<String, String>();
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            //convert JSON string to Map
//            assign = mapper.readValue(assignedTo, new TypeReference<HashMap<String, String>>() {});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        JLabel assignLabel = new JLabel(assign.get("playerName"));
//        assignLabel.setFont(new Font("Arial", Font.BOLD, 12));
//
//        add(assignLabel, BorderLayout.NORTH);

        add(idLabel,BorderLayout.LINE_START);
        // Add the title label to the StoryCard
        add(titleLabel, BorderLayout.NORTH);

        // Create JTextAreas for description and comments
        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);


        // Add the JTextAreas to the StoryCard
        add(descriptionArea, BorderLayout.CENTER);

        // Fetch values from the backend and create JComboBoxes
        JComboBox<String> statusComboBox = new JComboBox<>(new String[]{"Ready", "In Progress", "Completed"});
        // JComboBox<String> assignedToComboBox = new JComboBox<>(new String[]{"John", "Alice", "Bob"});
        JComboBox<String> storyPointsComboBox = new JComboBox<>(new String[]{"1", "2", "3", "5", "8"});

        // Set the initial values
        statusComboBox.setSelectedItem(status);
        // assignedToComboBox.setSelectedItem(assignedTo);
        storyPointsComboBox.setSelectedItem(storyPoints);

        // Add action listeners to update the status, assignedTo, and storyPoints
        statusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // status = (String) statusComboBox.getSelectedItem();
            }
        });

        storyPointsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // storyPoints = (String) storyPointsComboBox.getSelectedItem();
            }
        });

        // Create a JPanel to hold the JComboBoxes
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(1, 3));
        comboBoxPanel.add(statusComboBox);
        //comboBoxPanel.add(assignedToComboBox);
        comboBoxPanel.add(storyPointsComboBox);

        // Add the JComboBox panel to the StoryCard
        add(comboBoxPanel, BorderLayout.EAST);

        setTransferHandler(new StoryCardTransferHandler());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new StoryCardMouseListener());
    }

    private class StoryCardTransferHandler extends TransferHandler {
        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection("StoryCard"); // Transferable content, can be any data
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            // Handle export completion if needed
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                JComponent target = (JComponent) support.getComponent();

                if (target instanceof JPanel && data.equals("StoryCard")) {
                    // Determine the target lane based on the parent of the target JPanel
                    JPanel targetPanel = (JPanel) target;
                    JPanel targetLane = (JPanel) targetPanel.getParent();

                    // Handle the drop operation based on your logic
                    // For example, move the StoryCard to the target lane
                    moveStoryCardToLane((StoryCard) support.getComponent(), targetLane);

                    targetLane.revalidate();
                    targetLane.repaint();
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        private void moveStoryCardToLane(StoryCard storyCard, JPanel targetLane) {
            // Implement the logic to move the StoryCard to the target lane
            // You may want to remove it from the current lane and add it to the target lane
            Container currentLane = (Container) storyCard.getParent();
            currentLane.remove(storyCard);
            targetLane.add(storyCard);
            targetLane.revalidate();
            targetLane.repaint();
        }

    }

    private class StoryCardMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            JComponent component = (JComponent) e.getSource();
            TransferHandler handler = component.getTransferHandler();
            handler.exportAsDrag(component, e, TransferHandler.MOVE);
        }
    }




    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

//    public String getAssignedTo() {
//        return assignedTo;
//    }

    public String getStoryPoints() {
        return storyPoints;
    }
}


