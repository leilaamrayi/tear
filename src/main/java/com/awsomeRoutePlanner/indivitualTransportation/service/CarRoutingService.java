package com.awsomeRoutePlanner.indivitualTransportation.service;

import com.awsomeRoutePlanner.indivitualTransportation.model.BicycleWalkRouting;
import com.awsomeRoutePlanner.indivitualTransportation.model.CarRouting;
import com.awsomeRoutePlanner.indivitualTransportation.model.LatLon;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class CarRoutingService {
    @Autowired
    CarRouting carRouting;
    public CarRouting getRoute(String originAddress, String destinationAddress){

        BicycleWalkRouting bicycleWalkRouting = new BicycleWalkRouting();

        // get latlon by street address
        LatLon origin = routingClient.getLatLon(originAddress);
        LatLon destination = routingClient.getLatLon(destinationAddress);
        if (origin == null || destination == null){
            return new CarRouting().setErrors(List.of("Could not fetch address details."));
        }

        // get routing info
        String route = routingClient.getRoute(origin, destination, navigateMode);
        if (route == null){
            bicycleWalkRouting.addError("Could not fetch routing information.");
        } else {
            JsonNode routeNode = extractRouteInfo(route);
            bicycleWalkRouting.setRoute(routeNode);
        }

        // get weather info
        String weatherResponse = weatherClient.getWeatherInfo(destination);
        if (weatherResponse == null){
            bicycleWalkRouting.addError("Could not fetch weather information.");
        } else {
            JsonNode weatherNode = extractWeatherInfo(weatherResponse);
            bicycleWalkRouting.setWeather(weatherNode);
        }

        // prepare service response
        if (bicycleWalkRouting.getRoute() == null || bicycleWalkRouting.getWeather() == null){
            bicycleWalkRouting.setErrors(List.of("Failed to process third party API response."));
        }

        return CarRouting;
    }
}
