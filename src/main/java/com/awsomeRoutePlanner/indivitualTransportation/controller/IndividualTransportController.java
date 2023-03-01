package com.awsomeRoutePlanner.indivitualTransportation.controller;

import com.awsomeRoutePlanner.indivitualTransportation.model.BicycleWalkRouting;
import com.awsomeRoutePlanner.indivitualTransportation.service.BicycleWalkRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndividualTransportController {

    @Autowired
    private BicycleWalkRoutingService bicycleWalkRoutingService;

    @GetMapping("/route/{mode}")
    public ResponseEntity<BicycleWalkRouting> showBicycleAndWalkRoute(@PathVariable String mode,
                                                                      @RequestParam String origin, @RequestParam String destination) {
        BicycleWalkRouting details = bicycleWalkRoutingService.getRoute(mode, origin, destination);
        return ResponseEntity.ok(details);
    }

   /* @GetMapping("/car/{waypoints}")
    public ResponseEntity<BicycleWalkRouting> showCarRoute(@PathVariable String waypoints) {
        BicycleWalkRouting details = bicycleWalkRoutingService.getRoute(mode, origin, destination);
        return ResponseEntity.ok(details);
    }*/

}
