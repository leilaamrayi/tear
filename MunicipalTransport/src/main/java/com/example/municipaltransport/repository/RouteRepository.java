package com.example.municipaltransport.repository;


import com.example.municipaltransport.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {


    List<Route> findRouteByStartLocation(String startLocation);
    List<Route> findRouteByendLocation(String endLocation);


}