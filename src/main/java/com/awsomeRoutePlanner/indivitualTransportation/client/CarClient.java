package com.awsomeRoutePlanner.indivitualTransportation.client;

import com.awsomeRoutePlanner.indivitualTransportation.model.LatLon;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class CarClient {


    //https://api.tomtom.com/routing/1/calculateRoute/52.50931%2C13.42936%3A52.50274%2C13.43872/json?maxAlternatives=2&instructionsType=text&routeRepresentation=summaryOnly&traffic=true&travelMode=car&key=lKtlfqUEMBX8ulI6Ok2YqEYxFmfW6vDp
    private final String baseUrlForCar = " https://api.tomtom.com";

    public String getCarRoute(LatLon origin, LatLon destination) {
        String waypoints = createWayPointString(origin, destination);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrlForCar + "/routing/1/calculateRoute/" + waypoints + "/json?maxAlternatives=2&instructionsType=text&routeRepresentation=summaryOnly&traffic=true&travelMode=car&key=lKtlfqUEMBX8ulI6Ok2YqEYxFmfW6vDp")
                .method("GET", null)
                .build();
        String responseBody = null;
        try {
            System.out.println("Sending request to tomtom API: " + request.url());
            Response response = client.newCall(request).execute();
            responseBody = response.body().string();
            System.out.println("Response from tomtom API: " + responseBody);
        } catch (Exception e) {
        }
        return responseBody;
    }

    public String createWayPointString(LatLon origin, LatLon destination) {
        String wayPointString = String.format("%s.%s.%s.%s",
                origin.getLat(), origin.getLon(),
                destination.getLat(), destination.getLon());
        return wayPointString;
    }
}

