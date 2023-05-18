package mk.ukim.finki.emt.hotelcatalog.services;

import mk.ukim.finki.emt.hotelcatalog.domain.models.Hotel;
import mk.ukim.finki.emt.hotelcatalog.domain.models.HotelId;
import mk.ukim.finki.emt.hotelcatalog.services.form.HotelForm;

import java.util.List;

public interface HotelService {
    Hotel findById(HotelId id);
    Hotel addHotel(HotelForm form);
    Hotel reservationCreated(HotelId hotelId, int quantity);
    Hotel reservationRemoved(HotelId hotelId, int quantity);
    List<Hotel> getAll();
}
