package com.travelworld.api.controller;

import com.travelworld.api.entity.Hotel;
import com.travelworld.api.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
@CrossOrigin(origins = "http://localhost:8100")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> obtenerHoteles(){
        return ResponseEntity.ok(hotelService.obtenerHoteles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotelPorId(@PathVariable Long id){
        return hotelService.obtenerHotelporId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Hotel>> buscarPorCiudad(@PathVariable String ciudad){
        return ResponseEntity.ok(hotelService.buscarPorCiudad(ciudad));
    }

    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<Hotel>> buscarPorPais(@PathVariable String pais){
        return  ResponseEntity.ok(hotelService.buscarPorPais(pais));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Hotel>> buscarPorNombre(@PathVariable String nombre){
        return  ResponseEntity.ok(hotelService.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Hotel> guardarHotel(@RequestBody Hotel hotel){
        Hotel hotelGuardado = hotelService.guardarHotel(hotel);
        return ResponseEntity.ok(hotelGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizarHotel(@PathVariable Long id, @RequestBody Hotel hotel){
        Hotel hotelActualizado = hotelService.actualizarHotel(hotel);

        if (hotelActualizado == null){
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(hotelActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id){
        if (hotelService.obtenerHotelporId(id).isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        hotelService.eliminarHotel(id);
        return ResponseEntity.noContent().build();
    }
}
