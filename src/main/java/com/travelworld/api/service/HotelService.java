package com.travelworld.api.service;

import com.travelworld.api.entity.Hotel;
import com.travelworld.api.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public  HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> obtenerHoteles(){
        return hotelRepository.findAll();
    }

    public Optional<Hotel> obtenerHotelporId(Long id){
        return hotelRepository.findById(id);
    }

    public List<Hotel> buscarPorCiudad(String ciudad){
        return hotelRepository.findByCiudadContainingIgnoreCase(ciudad);
    }

    public List<Hotel> buscarPorPais(String pais){
        return hotelRepository.findByPaisContainingIgnoreCase(pais);
    }

    public List<Hotel> buscarPorNombre(String nombre){
        return  hotelRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Hotel guardarHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public Hotel actualizarHotel(Hotel hotel){
        Optional<Hotel> hotelExistente = hotelRepository.findById(hotel.getId());

        if (hotelExistente.isPresent()){
            hotel.setId(hotel.getId());
            return  hotelRepository.save(hotel);
        }

        return null;
    }

    public void eliminarHotel(Long id){
        hotelRepository.deleteById(id);
    }
}

