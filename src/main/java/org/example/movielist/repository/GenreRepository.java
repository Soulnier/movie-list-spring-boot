package org.example.movielist.repository;

import org.example.movielist.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
