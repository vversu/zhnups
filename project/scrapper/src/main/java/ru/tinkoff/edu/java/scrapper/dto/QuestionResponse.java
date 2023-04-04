package ru.tinkoff.edu.java.scrapper.dto;

import java.net.URI;
import java.time.OffsetDateTime;

public record QuestionResponse(long questionId,String title,String body,URI link,OffsetDateTime creationDate,OffsetDateTime lastActivityDate,String[] tags) {}