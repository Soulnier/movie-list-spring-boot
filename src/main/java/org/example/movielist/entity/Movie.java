package org.example.movielist.entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Digits(integer = 4, fraction = 0)
    private Integer releaseYear;

    @Min(0)
    @Max(10)
    private Integer userRating;

    private String userReview;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status watchStatus;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "genres_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();
}
