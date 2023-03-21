package parsers;

import java.net.MalformedURLException;
import java.util.List;

public class LinkParser {

    List<LinkHandler> handlers;

    public LinkParser() {
        handlers = List.of(new GitHubLinkHandler(), new StackOverflowLinkHandler());
    }

    public Object parse(String url) throws MalformedURLException {
        for (LinkHandler handler : handlers) {
            if (handler.canHandle(url)) {
                return handler.handle(url);
            }
        }
        return null;
    }
}