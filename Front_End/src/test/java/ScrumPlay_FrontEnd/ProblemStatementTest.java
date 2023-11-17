package ScrumPlay_FrontEnd;

import ScrumPlay_FrontEnd.ProblemStatement;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProblemStatementTest {

    @Test
    public void testDefaultProblemStatement() {
        ProblemStatement problemStatement = Mockito.spy(new ProblemStatement());
        Mockito.when(problemStatement.sendGetRequestToBackend(Mockito.anyString())).thenReturn("{\"statement\":\"Test Statement\",\"comments\":\"Test Comments\"}");

        String defaultStatement = problemStatement.lblNewLabel_1.getText().trim();

        assertNotNull(defaultStatement);
        assertEquals("<html>Test Statement</html>", defaultStatement);
    }

    @Test
    public void testJsonToMap() {
        ProblemStatement problemStatement = new ProblemStatement();
        String jsonString = "{\"statement\":\"Test Statement\",\"comments\":\"Test Comments\"}";
        Map<String, Object> statementMap = problemStatement.jsonToMap(jsonString);

        assertEquals("Test Statement", statementMap.get("statement"));
        assertEquals("Test Comments", statementMap.get("comments"));
    }
}
