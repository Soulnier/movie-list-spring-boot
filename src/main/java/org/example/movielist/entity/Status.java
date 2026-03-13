package org.example.movielist.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    NO_STATUS("Нет статуса"),
    PLANNED("Запланировано"),
    DROPPED("Брошено"),
    WATCHED("Просмотрено"),
    WATCHING("Просматривается");

    private final String displayName;
}
