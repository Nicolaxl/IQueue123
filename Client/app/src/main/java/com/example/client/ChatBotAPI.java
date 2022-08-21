package com.example.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChatBotAPI {

    @GET
    Call<ChatBotMsg> getMessage(@Url String url);
}
