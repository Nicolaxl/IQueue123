package com.example.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BingNewsAPI {
    String BaseURL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<financeNews> getCategoriesNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apiKey") String apikey
    );
}
