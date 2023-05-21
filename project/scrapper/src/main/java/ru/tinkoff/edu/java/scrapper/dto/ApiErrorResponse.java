package ru.tinkoff.edu.java.scrapper.dto;

import lombok.Builder;

import java.util.ArrayList;


@Builder
public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage,
    ArrayList<String> stacktrace
) {
}
