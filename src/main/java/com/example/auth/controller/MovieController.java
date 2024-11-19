package com.example.auth.controller;

import com.example.auth.model.TMDbMovie;
import com.example.auth.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public String showHomePage() {
        return "movies"; // Displays the search form
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam String yearFrom,
                               @RequestParam String yearTo,
                               @RequestParam String genre,
                               @RequestParam double rating,
                               Model model) {
        // Fetch filtered movies
        List<TMDbMovie> movies = movieService.fetchMovies(yearFrom, yearTo, genre, rating);
        model.addAttribute("movies", movies);
        return "movies"; // Displays results on the same page
    }
}
