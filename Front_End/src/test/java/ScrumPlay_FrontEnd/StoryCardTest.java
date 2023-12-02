//package ScrumPlay_FrontEnd;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.swing.*;
//import java.awt.event.MouseEvent;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class StoryCardTest {
//
//    private StoryCard storyCard;
//
//    @BeforeEach
//    void setUp() {
//        // Set up a new StoryCard instance before each test
//        storyCard = new StoryCard("1", "Title", "Description", "Ready", "1#John", "3");
//    }
//
//    @Test
//    void getStoryId() {
//        assertEquals("1", storyCard.getStoryId());
//    }
//
////    @Test
////    void getStatusComboBox() {
////        String statusComboBox = storyCard.getStatusComboBox();
////        assertNotNull(statusComboBox);
////
////        // Assuming "Ready" is the default status
////        assertEquals("Ready", storyCard.getStatusComboBox().getSelectedItem());
////    }
//
////    @Test
////    void getAssignedToComboBox() {
////        String assignedToComboBox = storyCard.getAssignedToComboBox();
////        assertNotNull(assignedToComboBox);
////
////        // Assuming "1#John" is the default assignedTo value
////        assertEquals("1", storyCard.getAssignedToComboBox());
////    }
//
////    @Test
////    void getStoryPointsComboBox() {
////        String storyPointsComboBox = storyCard.getStoryPointsComboBox();
////        assertNotNull(storyPointsComboBox);
////
////        // Assuming "3" is the default storyPoints value
////        assertEquals("3", storyCard.getStoryPointsComboBox().getSelectedItem());
////    }
//
//    @Test
//    void getTitle() {
//        assertEquals("Title", storyCard.getTitle());
//    }
//
//    @Test
//    void getDescription() {
//        assertEquals("Description", storyCard.getDescription());
//    }
//
//    @Test
//    void getStatus() {
//        assertEquals("Ready", storyCard.getStatus());
//    }
//
////    @Test
////    void getAssignedTo() {
////        assertEquals("1#John", storyCard.getAssignedTo());
////    }
//
//    @Test
//    void getStoryPoints() {
//        assertEquals("3", storyCard.getStoryPoints());
//    }
//
////    @Test
////    void mousePressed() {
////        // Create a mock MouseEvent
////        MouseEvent mockEvent = new MouseEvent(new JButton(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 0, 0, 0, false);
////
////        // Invoke the mousePressed method and check if it completes without exceptions
////        assertDoesNotThrow(() -> storyCard.mousePressed(mockEvent));
////    }
//}
