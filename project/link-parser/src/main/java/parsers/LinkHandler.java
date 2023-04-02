package parsers;

import java.net.MalformedURLException;

public sealed interface LinkHandler permits GitHubLinkHandler, StackOverflowLinkHandler {
	  boolean canHandle(String url);
	  Object handle(String url) throws MalformedURLException;
	}