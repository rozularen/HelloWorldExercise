package com.marcos.helloworldexercise.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by markc on 13/01/2018.
 */
class APIClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {
        String baseUrl = "http://hello-world.innocv.com/api/user/";

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

        }

        return retrofit;
    }
}
