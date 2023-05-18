package mk.ukim.finki.emt.hotelcatalog.domain.repository;

import mk.ukim.finki.emt.hotelcatalog.domain.models.Hotel;
import mk.ukim.finki.emt.hotelcatalog.domain.models.HotelId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, HotelId> {
}
