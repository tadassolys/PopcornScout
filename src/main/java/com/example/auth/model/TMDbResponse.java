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
}
