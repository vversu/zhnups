package parsers;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackOverflowLinkHandler implements LinkHandler {

    private static final String STACKOVERFLOW_PATTERN = "^https?://stackoverflow.com/questions/(?<id>\\d+)/?$";

    public boolean canHandle(String url) {
        return url.matches(STACKOVERFLOW_PATTERN);
    }

    public Object handle(String url) throws MalformedURLException {
        Pattern pattern = Pattern.compile(STACKOVERFLOW_PATTERN);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group("id"));
        } else {
            throw new MalformedURLException("Invalid StackOverflow URL format");
        }
    }
}
