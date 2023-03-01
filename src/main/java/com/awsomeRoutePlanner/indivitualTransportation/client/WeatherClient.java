package com.awsomeRoutePlanner.indivitualTransportation.client;

import com.awsomeRoutePlanner.indivitualTransportation.model.LatLon;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Component;

@Component
public class WeatherClient {

    private final String baseUrl = "https://api.openweathermap.org";

    public String getWeatherInfo(LatLon location) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl+"/data/2.5/weather?lat="+location.getLat()+"&lon="+location.getLon()+"&appid=e29a7cd5bf9d9e13a251fc10168d0daf")
                .method("GET", null)
                .build();

        String responseBody = null;
        try {
            System.out.println("Sending request to weather API: "+request.url());
            Response response = client.newCall(request).execute();
            responseBody = response.body().string();
            System.out.println("Response from weather API: "+responseBody);
        } catch (Exception e) {}
        return responseBody;
    }

}
