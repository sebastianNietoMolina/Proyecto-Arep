package com.prueba.apache.kafka.service;

import com.prueba.apache.kafka.model.City;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.google.gson.Gson;
import java.io.IOException;

public class HttpService {

    public City doGet(String url) {
        City city;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        OkHttpClient httpClient = new OkHttpClient();
        String datos = null;
        Response res = null;
        try {
            res = httpClient.newCall(request).execute();
            if (res.isSuccessful()) {
                datos = res.body().string();
            }
        } catch (IOException e) {
            System.out.println("Fallo");
        }
        Gson gson = new Gson();
        city = gson.fromJson(datos, City.class);
        return city;
    }
}
