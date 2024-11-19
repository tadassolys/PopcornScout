package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TMDbMovie {
    private String title;

    @JsonProperty("vote_average")
    private double rating;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("genre_ids")
    private List<Integer> genreIds; // Genre IDs returned by TMDb API
}
