package mk.ukim.finki.emt.guestreservations.domain.repository;

import mk.ukim.finki.emt.guestreservations.domain.models.Guest;
import mk.ukim.finki.emt.guestreservations.domain.models.GuestId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, GuestId> {
}
