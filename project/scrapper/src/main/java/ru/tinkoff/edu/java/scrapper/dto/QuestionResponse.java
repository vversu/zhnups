package ru.tinkoff.edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record QuestionResponse(long questionId,String title,String body,String link,OffsetDateTime creationDate,OffsetDateTime lastActivityDate,String[] tags) {}