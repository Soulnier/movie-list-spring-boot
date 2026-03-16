package org.example.movielist.repository;

import org.example.movielist.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    List<Movie> findByUserId(UUID userId);
}
