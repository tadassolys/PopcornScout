package com.example.auth.service;

import com.example.auth.model.TMDbMovie;
import com.example.auth.model.TMDbResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    public List<TMDbMovie> fetchMovies(String yearFrom, String yearTo, String genre, double rating) {
        // Build the API URL dynamically based on filters
        String url = TMDB_BASE_URL + "/discover/movie"
                + "?api_key=" + apiKey
                + "&primary_release_date.gte=" + yearFrom
                + "&primary_release_date.lte=" + yearTo
                + "&vote_average.gte=" + rating
                + "&with_genres=" + genre;

        // Use RestTemplate to fetch data
        RestTemplate restTemplate = new RestTemplate();
        TMDbResponse response = restTemplate.getForObject(url, TMDbResponse.class);

        // Return the list of movies from the API response
        return response.getMovies();
    }
}
