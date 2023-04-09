package parsers;

import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GitHubLinkHandler implements LinkHandler {

    private static final String GITHUB_PATTERN = "^https?://github.com/(?<user>[^/]+)/(?<repo>[^/]+)((\\/[-a-zA-Z0-9_]*)*)$";

    public boolean canHandle(String url) {
        return url.matches(GITHUB_PATTERN);
    }

    public Map.Entry<String, String> handle(String url) throws MalformedURLException {
        Pattern pattern = Pattern.compile(GITHUB_PATTERN);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            return new AbstractMap.SimpleEntry<>(matcher.group("user"), matcher.group("repo"));
        } else {
            throw new MalformedURLException("Invalid GitHub URL format");
        }
    }
}