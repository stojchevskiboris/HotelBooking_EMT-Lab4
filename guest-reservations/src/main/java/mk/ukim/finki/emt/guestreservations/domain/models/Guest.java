package mk.ukim.finki.emt.guestreservations.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.guestreservations.domain.valueobjects.HotelVO;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "guests")
@Getter
public class Guest extends AbstractEntity<GuestId> {

    private Instant bookedOn;

    @Enumerated(EnumType.STRING)
    private ReservationState reservationState;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

    public Guest(Instant now, Currency currency){
        super(GuestId.randomId(GuestId.class));
        this.bookedOn = now;
        this.currency = currency;
    }

    public Money total(){
        return reservations.stream().map(Reservation::subtotal).reduce(new Money(currency,0),Money::add);
    }

    public Reservation addReservation(@NonNull HotelVO hotel, int numOfNights){
        Objects.requireNonNull(hotel,"A hotel cannot be null");
        Reservation reservation = new Reservation(hotel.getId(), hotel.getPricePerNight(),numOfNights);
        reservations.add(reservation);
        return reservation;
    }

    public void removeReservation(@NonNull ReservationId reservationId){
        Objects.requireNonNull(reservationId, "Reservation must not be null");
        reservations.removeIf(r->r.getId().equals(reservationId));
    }


}
