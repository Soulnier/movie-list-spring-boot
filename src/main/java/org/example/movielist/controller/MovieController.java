package org.example.movielist.controller;

import org.example.movielist.entity.Movie;
import org.example.movielist.entity.Genre;
import org.example.movielist.entity.Status;
import org.example.movielist.entity.User;
import org.example.movielist.repository.MovieRepository;
import org.example.movielist.repository.GenreRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieController(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String listMovies(@AuthenticationPrincipal User user, Model model) {
        List<Movie> movies = movieRepository.findByUserId(user.getId()); // нужно добавить метод в репозиторий
        model.addAttribute("movies", movies);
        return "movies/list";
    }

    @GetMapping("/new")
    public String newMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("allGenres", genreRepository.findAll());
        model.addAttribute("statuses", Status.values());
        return "movies/form";
    }

    @PostMapping
    public String saveMovie(@AuthenticationPrincipal User user,
                            @ModelAttribute Movie movie,
                            @RequestParam(value = "genreIds", required = false) List<Integer> genreIds) {
        movie.setUser(user);
        if (genreIds != null && !genreIds.isEmpty()) {
            List<Genre> genres = genreRepository.findAllById(genreIds);
            movie.setGenres(genres);
        }
        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@AuthenticationPrincipal User user,
                                @PathVariable UUID id,
                                Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null || !movie.getUser().getId().equals(user.getId())) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("allGenres", genreRepository.findAll());
        model.addAttribute("statuses", Status.values());
        return "movies/form";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@AuthenticationPrincipal User user,
                              @PathVariable UUID id,
                              @ModelAttribute Movie movie,
                              @RequestParam(value = "genreIds", required = false) List<Integer> genreIds) {
        Movie existing = movieRepository.findById(id).orElse(null);
        if (existing == null || !existing.getUser().getId().equals(user.getId())) {
            return "redirect:/movies";
        }
        existing.setName(movie.getName());
        existing.setReleaseYear(movie.getReleaseYear());
        existing.setUserRating(movie.getUserRating());
        existing.setUserReview(movie.getUserReview());
        existing.setWatchStatus(movie.getWatchStatus());
        if (genreIds != null && !genreIds.isEmpty()) {
            existing.setGenres(genreRepository.findAllById(genreIds));
        } else {
            existing.setGenres(null);
        }
        movieRepository.save(existing);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@AuthenticationPrincipal User user,
                              @PathVariable UUID id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null && movie.getUser().getId().equals(user.getId())) {
            movieRepository.delete(movie);
        }
        return "redirect:/movies";
    }
}