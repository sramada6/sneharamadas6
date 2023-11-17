package ScrumPlay_FrontEnd;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectProblemStatementTest {

    private SelectProblemStatement selectProblemStatement;

    @Before
    public void setUp() {
        selectProblemStatement = new SelectProblemStatement(getMockProblemStatements());
    }

    @Test
    public void testGetCommentsForStatement() {
        String selectedStatement = "Statement 1";
        String expectedComments = "Comments for Statement 1";

        ArrayList<Map<String, Object>> mockMapStComments = getMockMapStComments();
        String resultComments = selectProblemStatement.getCommentsForStatement(selectedStatement, mockMapStComments);

        assertEquals(expectedComments, resultComments);
    }

    @Test
    public void testGetCommentsForNonexistentStatement() {
        String selectedStatement = "Nonexistent Statement";

        ArrayList<Map<String, Object>> mockMapStComments = getMockMapStComments();
        String resultComments = selectProblemStatement.getCommentsForStatement(selectedStatement, mockMapStComments);

        assertEquals(selectedStatement, resultComments);
    }

    private String getMockProblemStatements() {
        return "[{\"statementid\":1,\"comments\":\"Comments for Statement 1\",\"statement\":\"Statement 1\",\"numOfUserStories\":3}," +
                "{\"statementid\":2,\"comments\":\"Comments for Statement 2\",\"statement\":\"Statement 2\",\"numOfUserStories\":2}]";
    }

    private ArrayList<Map<String, Object>> getMockMapStComments() {
        ArrayList<Map<String, Object>> mockMapStComments = new ArrayList<>();

        Map<String, Object> statement1 = new HashMap<>();
        statement1.put("statementid", 1);
        statement1.put("comments", "Comments for Statement 1");
        statement1.put("statement", "Statement 1");
        statement1.put("numOfUserStories", 3);

        Map<String, Object> statement2 = new HashMap<>();
        statement2.put("statementid", 2);
        statement2.put("comments", "Comments for Statement 2");
        statement2.put("statement", "Statement 2");
        statement2.put("numOfUserStories", 2);

        mockMapStComments.add(statement1);
        mockMapStComments.add(statement2);

        return mockMapStComments;
    }
}
