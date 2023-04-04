package ru.tinkoff.edu.java.bot.telegram;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LinkTracker {
    private final Set<String> trackedLinks = new HashSet<>();

    public void addLink(String link) {
        trackedLinks.add(link);
    }

    public void removeLink(String link) {
        trackedLinks.remove(link);
    }

    public Set<String> getTrackedLinks() {
        return Collections.unmodifiableSet(trackedLinks);
    }
}
