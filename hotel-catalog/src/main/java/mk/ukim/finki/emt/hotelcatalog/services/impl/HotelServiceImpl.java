package mk.ukim.finki.emt.hotelcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.hotelcatalog.domain.exceptions.HotelNotFoundException;
import mk.ukim.finki.emt.hotelcatalog.domain.models.Hotel;
import mk.ukim.finki.emt.hotelcatalog.domain.models.HotelId;
import mk.ukim.finki.emt.hotelcatalog.domain.repository.HotelRepository;
import mk.ukim.finki.emt.hotelcatalog.services.HotelService;
import mk.ukim.finki.emt.hotelcatalog.services.form.HotelForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel findById(HotelId id) {
        return hotelRepository.findById(id).orElseThrow(HotelNotFoundException::new);
    }

    @Override
    public Hotel addHotel(HotelForm form) {
        Hotel h = Hotel.build(form.getHotelName(), form.getPricePerNight(),form.getSales());
        hotelRepository.save(h);
        return h;
    }

    @Override
    public Hotel reservationCreated(HotelId hotelId, int quantity) {
        Hotel h = hotelRepository.findById(hotelId).orElseThrow(HotelNotFoundException::new);
        h.addSales(quantity);
        hotelRepository.saveAndFlush(h);
        return h;
    }

    @Override
    public Hotel reservationRemoved(HotelId hotelId, int quantity) {
        Hotel h = hotelRepository.findById(hotelId).orElseThrow(HotelNotFoundException::new);
        h.removeSales(quantity);
        hotelRepository.saveAndFlush(h);
        return h;
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
