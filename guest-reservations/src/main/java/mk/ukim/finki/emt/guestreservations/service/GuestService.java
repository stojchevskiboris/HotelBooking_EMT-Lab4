package mk.ukim.finki.emt.guestreservations.service;

import mk.ukim.finki.emt.guestreservations.domain.exceptions.GuestIdNotExistException;
import mk.ukim.finki.emt.guestreservations.domain.exceptions.ReservationIdNotExistsException;
import mk.ukim.finki.emt.guestreservations.domain.models.Guest;
import mk.ukim.finki.emt.guestreservations.domain.models.GuestId;
import mk.ukim.finki.emt.guestreservations.domain.models.ReservationId;
import mk.ukim.finki.emt.guestreservations.service.forms.GuestForm;
import mk.ukim.finki.emt.guestreservations.service.forms.ReservationForm;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    GuestId addReservationToGuest(GuestForm guestForm);

    List<Guest> findAll();

    Optional<Guest> findById(GuestId id);

    void addReservation(GuestId guestId, ReservationForm reservationForm) throws GuestIdNotExistException;

    void deleteReservation(GuestId guestId, ReservationId reservationId) throws GuestIdNotExistException, ReservationIdNotExistsException;
}
