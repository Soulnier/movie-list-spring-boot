package org.example.movielist.entity;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Status {
    NO_STATUS("Нет статуса"),
    PLANNED("Запланировано"),
    DROPPED("Брошено"),
    WATCHED("Просмотрено"),
    WATCHING("Просматривается");

    private final String displayName;
}
