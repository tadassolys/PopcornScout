package com.example.auth.service;

import com.example.auth.model.TMDbMovie;
import com.example.auth.model.TMDbResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Value("${tmdb.api.key}")
    private String apiKey;

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";
    private static final int MAX_PAGES = 5;

    public List<TMDbMovie> fetchMovies(String yearFrom, String yearTo, String genre, double rating) {
        // Convert years to proper date format, api works that way.
        String dateFrom = yearFrom + "-01-01";  // January 1st of start year
        String dateTo = yearTo + "-12-31";      // December 31st of end year

        List<TMDbMovie> allMovies = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        int currentPage = 1;

        try {
            while (currentPage <= MAX_PAGES) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(TMDB_BASE_URL + "/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("primary_release_date.gte", dateFrom)
                        .queryParam("primary_release_date.lte", dateTo)
                        .queryParam("vote_average.gte", rating)
                        .queryParam("with_genres", genre)
                        .queryParam("page", currentPage)
                        .queryParam("sort_by", "primary_release_date.desc"); // Sort by release date

                String url = builder.toUriString();
                logger.info("TMDb API Request URL: {}", url);

                TMDbResponse response = restTemplate.getForObject(url, TMDbResponse.class);

                if (response == null || response.getMovies() == null || response.getMovies().isEmpty()) {
                    break;
                }

                allMovies.addAll(response.getMovies());

                if (currentPage >= response.getTotalPages()) {
                    break;
                }

                currentPage++;
            }

            return allMovies;

        } catch (RestClientException e) {
            logger.error("Error fetching movies from TMDb API", e);
            return Collections.emptyList();
        }
    }
}