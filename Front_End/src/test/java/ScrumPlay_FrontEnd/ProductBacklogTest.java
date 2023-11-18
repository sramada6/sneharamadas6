//package ScrumPlay_FrontEnd;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Objects;
//
//import static org.junit.Assert.assertEquals;
//
//public class ProductBacklogTest {
//
//    private ProductBacklog productBacklog;
//
//    @Before
//    public void setUp() {
//        SwingUtilities.invokeLater(() -> {
//            productBacklog = new ProductBacklog();
//            productBacklog.setVisible(true);
//        });
//
//        try {
//            SwingUtilities.invokeAndWait(() -> {});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @After
//    public void tearDown() {
//        productBacklog.dispose();
//    }
//
//    @Test
//    public void testUserStorySelection() {
//        SwingUtilities.invokeLater(() -> {
//            productBacklog.userList.setSelectedIndex(0);
//
//            assertEquals("User Story: US#001/Create Landing Page", productBacklog.detailPanel.getComponent(0).getName());
//            assertEquals("New", ((JComboBox<?>) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "statusDropdown"))).getSelectedItem());
//            assertEquals("Player 1", ((JComboBox<?>) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "playerDropdown"))).getSelectedItem());
//            assertEquals("1", ((JComboBox<?>) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "userStoryPointsField"))).getSelectedItem());
//            assertEquals("As a developer, I want to design an informative page for the trainees...",
//                    ((JTextArea) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "descriptionTextArea"))).getText());
//
//            ((JTextArea) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "commentsTextArea"))).setText("Test Comment");
//            assertEquals("Test Comment", ((JTextArea) Objects.requireNonNull(getComponentByName(productBacklog.detailPanel, "commentsTextArea"))).getText());
//        });
//    }
//
//    private Component getComponentByName(Container container, String name) {
//        for (Component component : container.getComponents()) {
//            if (name.equals(component.getName())) {
//                return component;
//            }
//        }
//        return null;
//    }
//}
