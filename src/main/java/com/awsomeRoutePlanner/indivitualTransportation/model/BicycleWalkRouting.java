package com.awsomeRoutePlanner.indivitualTransportation.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class BicycleWalkRouting {
    private List<String> errors = new ArrayList<>();
    private JsonNode route;
    private JsonNode weather;

    public BicycleWalkRouting addError(String errorMessage){
        errors.add(errorMessage);
        return this;
    }

}
