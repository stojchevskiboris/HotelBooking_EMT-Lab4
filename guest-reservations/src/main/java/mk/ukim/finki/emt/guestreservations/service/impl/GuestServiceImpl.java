package mk.ukim.finki.emt.guestreservations.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.guestreservations.domain.exceptions.GuestIdNotExistException;
import mk.ukim.finki.emt.guestreservations.domain.exceptions.ReservationIdNotExistsException;
import mk.ukim.finki.emt.guestreservations.domain.models.Guest;
import mk.ukim.finki.emt.guestreservations.domain.models.GuestId;
import mk.ukim.finki.emt.guestreservations.domain.models.ReservationId;
import mk.ukim.finki.emt.guestreservations.domain.repository.GuestRepository;
import mk.ukim.finki.emt.guestreservations.service.GuestService;
import mk.ukim.finki.emt.guestreservations.service.forms.GuestForm;
import mk.ukim.finki.emt.guestreservations.service.forms.ReservationForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationCreated;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;
    @Override
    public GuestId addReservationToGuest(GuestForm guestForm) {
        Objects.requireNonNull(guestForm,"Guest must not be null.");
        var constraintViolations = validator.validate(guestForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The guest form is not valid", constraintViolations);
        }
        var newGuest = guestRepository.saveAndFlush(toDomainObject(guestForm));
        newGuest.getReservations().forEach(i->domainEventPublisher.publish(new ReservationCreated(i.getHotelId().getId(), i.getNumOfNights())));
        return newGuest.getId();
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> findById(GuestId id) {
        return guestRepository.findById(id);
    }

    @Override
    public void addReservation(GuestId guestId, ReservationForm reservationForm) throws GuestIdNotExistException {
        Guest guest = guestRepository.findById(guestId).orElseThrow(GuestIdNotExistException::new);
        guest.addReservation(reservationForm.getHotel(),reservationForm.getNumOfNights());
        guestRepository.saveAndFlush(guest);
        domainEventPublisher.publish(new ReservationCreated(reservationForm.getHotel().getId().getId(),reservationForm.getNumOfNights()));
    }

    @Override
    public void deleteReservation(GuestId guestId, ReservationId reservationId) throws GuestIdNotExistException, ReservationIdNotExistsException {
        Guest guest = guestRepository.findById(guestId).orElseThrow(GuestIdNotExistException::new);
        guest.removeReservation(reservationId);
        guestRepository.saveAndFlush(guest);
    }

    private Guest toDomainObject(GuestForm guestForm){
        var guest = new Guest(Instant.now(),guestForm.getCurrency());
        guestForm.getReservations().forEach(i->guest.addReservation(i.getHotel(),i.getNumOfNights()));
        return guest;
    }
}
