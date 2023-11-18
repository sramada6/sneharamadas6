package ScrumPlay_FrontEnd;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ScrumBoardTest {

    @org.junit.Test
    public void testInitialization() {
        SwingUtilities.invokeLater(() -> {
            ScrumBoard scrumBoard = new ScrumBoard();

            assertNotNull(scrumBoard);
            assertEquals("Scrum Board", scrumBoard.getTitle());
            assertEquals(1200, scrumBoard.getWidth());
            assertEquals(800, scrumBoard.getHeight());
            assertEquals(JFrame.EXIT_ON_CLOSE, scrumBoard.getDefaultCloseOperation());

            // Test the existence of UI components
            assertNotNull(scrumBoard.userDropdown);
            assertNotNull(scrumBoard.boardPanel);
            assertNotNull(scrumBoard.updateArea);
        });
    }

    @Test
    public void testUpdateBoard() {
        SwingUtilities.invokeLater(() -> {
            ScrumBoard scrumBoard = new ScrumBoard();

            scrumBoard.userDropdown.setSelectedItem("JohnDoe");
            scrumBoard.updateBoard();


        });
    }

    @Test
    void testShowStatusInputDialog() {
        SwingUtilities.invokeLater(() -> {
            ScrumBoard scrumBoard = new ScrumBoard();

            JButton flipButton = new JButton("Update Status");
            flipButton.addActionListener(e -> scrumBoard.showStatusInputDialog("SampleUserStory"));

            flipButton.doClick();

            assertNotNull(scrumBoard.updateArea.getText());
            assertTrue(scrumBoard.updateArea.getText().contains("SampleUserStory"));
        });
    }

    @Test
    void testCreateCardPanel() {
        SwingUtilities.invokeLater(() -> {
            ScrumBoard scrumBoard = new ScrumBoard();
            JPanel cardPanel = scrumBoard.createCardPanel("UserStory1", "To Do");

            assertNotNull(cardPanel);
            assertEquals(BorderFactory.createEtchedBorder(), cardPanel.getBorder());
            assertEquals(new Color(255, 250, 250), cardPanel.getBackground());

            Component[] components = cardPanel.getComponents();
            assertEquals(2, components.length);
            assertTrue(components[0] instanceof JLabel);
            assertTrue(components[1] instanceof JButton);
        });
    }

}