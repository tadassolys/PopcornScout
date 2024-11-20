package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class TMDbResponse {
    private int page;

    @JsonProperty("results")
    private List<TMDbMovie> movies;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;

    public TMDbResponse() {
    }

    public TMDbResponse(int page, List<TMDbMovie> movies, int totalPages, int totalResults) {
        this.page = page;
        this.movies = movies;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public boolean hasMorePages() {
        return page < totalPages;
    }

    public int getNextPage() {
        return page + 1;
    }

    public boolean isEmpty() {
        return movies == null || movies.isEmpty();
    }

    public int getTotalMovies() {
        return totalResults;
    }

}