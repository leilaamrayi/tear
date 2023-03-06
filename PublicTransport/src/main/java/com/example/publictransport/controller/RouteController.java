package com.example.publictransport.controller;

import com.example.publictransport.model.Route;
import com.example.publictransport.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/routes/*")
public class RouteController {

    @Autowired
    private RouteService routeService;

    private List<Route> routeList;

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routeList = routeService.getAllRoutes();
        if (routeList == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(routeList);
    }
    
        @GetMapping("traffic/")
    public ResponseEntity<TrafficDetails> getGeoCooding(RestTemplate restTemplate) {
        StringBuilder builder = new StringBuilder("https://api.resrobot.se/v2.1/trip?");
        builder
                .append("format=").append("json")
                .append("&originId=").append("740000001")
                .append("&destId=").append("740000003")
                .append("&accessId=").append("<api.resrobot.se/v2.1/trip>");

        ResponseEntity<TrafficDetails> traffic = restTemplate
                .getForEntity(builder.toString(), TrafficDetails.class);


        return ResponseEntity.ok(traffic.getBody());
    }
    
    @PostMapping
    public ResponseEntity<List<Route>> createRoute(@RequestBody Route route) {
        routeService.save(route);
        return ResponseEntity.status(201).body(routeList);
    }

    @GetMapping("/route/{id}")
    public List<Route> getRouteByStartLocation(@PathVariable String startLocation) {
        List<Route> routes = routeService.findByStartLocatin(startLocation);
        return routes;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<List<Route>> addVehicle(@RequestBody Route route) {
        List<Route> vehicles = new ArrayList<>();
        vehicles.add(new Route());
        routeService.addRoute(route);
        return new ResponseEntity<>(vehicles, HttpStatus.CREATED);
    }

    
    @DeleteMapping("/delete/{id}")
    public void deleteRoute(@PathVariable("id") long id) {
        routeService.deleteRoute(id);
    }

}
