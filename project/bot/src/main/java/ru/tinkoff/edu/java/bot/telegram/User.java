package ru.tinkoff.edu.java.bot.telegram;

import java.util.HashSet;
import java.util.Set;

public record User(int userId, Set<String> trackedLinks) {
    public User {
        if (trackedLinks == null) {
            trackedLinks = new HashSet<>();
        }
    }
    
    public void addTrackedLink(String link) {
        trackedLinks.add(link);
    }
    
    public boolean removeTrackedLink(String link) {
        return trackedLinks.remove(link);
    }
    
    public Set<String> getTrackedLinks() {
        return new HashSet<>(trackedLinks);
    }
}