package mk.ukim.finki.emt.hotelcatalog.xport.rest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import mk.ukim.finki.emt.hotelcatalog.domain.models.Hotel;
import mk.ukim.finki.emt.hotelcatalog.services.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@AllArgsConstructor
public class HotelResource {
    private final HotelService hotelService;

    @GetMapping
    public List<Hotel> getAll(){
        return hotelService.getAll();
    }
}
