package com.travelworld.api.repository;

import com.travelworld.api.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCiudadContainingIgnoreCase(String ciudad);

    List<Hotel> findByPaisContainingIgnoreCase(String pais);

    List<Hotel> findByNombreContainingIgnoreCase(String nombre);
}
