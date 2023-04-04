package parsers;

import java.net.MalformedURLException;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LinkParserTest {

    private final LinkParser parser = new LinkParser();

    @Test
    public void parseGitHubLink_validLink_success() throws MalformedURLException {
        String url = "https://github.com/vversu/zhnups";
        LinkParser parser = new LinkParser();
        Object result = parser.parse(url);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result instanceof Map.Entry);
        Map.Entry<String, String> entry = (Map.Entry<String, String>) result;
        Assertions.assertEquals("vversu", entry.getKey());
        Assertions.assertEquals("zhnups", entry.getValue());
    }

    @Test
    public void parseStackOverflowLink_validLink_success() throws MalformedURLException {
        String url = "https://stackoverflow.com/questions/123456789/how-to-unit-test-a-java-application";
        LinkParser parser = new LinkParser();
        Object result = parser.parse(url);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result instanceof Integer);
        Assertions.assertEquals(123456789, (int) result);
    }

    
    @Test
    public void parseInvalidLink_returnNull() throws MalformedURLException {
        String url = "google.com";
        LinkParser parser = new LinkParser();
        Object result = parser.parse(url);
        Assertions.assertNull(result);
    }

}
