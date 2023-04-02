package parsers;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StackOverflowLinkHandler implements LinkHandler {

	private static final String STACK_OVERFLOW_PATTERN = "^https?://stackoverflow.com/questions/(?<id>\\d+)((\\/[-a-zA-Z0-9_]*)*)$";

    public boolean canHandle(String url) {
        return url.matches(STACK_OVERFLOW_PATTERN);
    }

    public Integer handle(String url) throws MalformedURLException {
        Pattern pattern = Pattern.compile(STACK_OVERFLOW_PATTERN);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group("id"));
        } else {
            throw new MalformedURLException("Invalid StackOverflow URL format");
        }
    }
}