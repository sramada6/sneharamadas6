/*
package ScrumPlay_FrontEnd;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ProblemStatementTest {

    @Test
    public void testJsonToMap() {

        String jsonString = "{\"statement\":\"Sample Statement\",\"comments\":\"Sample Comments\"}";

        Map<String, Object> result = ProblemStatement.jsonToMap(jsonString);

        assertEquals("Sample Statement", result.get("statement"));
        assertEquals("Sample Comments", result.get("comments"));

        String jsonStringWithoutStatement = "{\"comments\":\"Sample Comments\"}";
        Map<String, Object> resultWithoutStatement = ProblemStatement.jsonToMap(jsonStringWithoutStatement);

        assertNull(resultWithoutStatement.get("statement"));
        assertEquals("Sample Comments", resultWithoutStatement.get("comments"));
    }
}

*/
