package mk.ukim.finki.emt.guestreservations.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.guestreservations.domain.valueobjects.HotelIdVO;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
@Getter
public class Reservation extends AbstractEntity<ReservationId> {
    private Money pricePerNight;

    @Column(name = "numOfNights", nullable = false)
    private int numOfNights;

    @AttributeOverride(name = "id", column = @Column(name = "hotel_id", nullable = false))
    private HotelIdVO hotelId;

    private Reservation(){
        super(DomainObjectId.randomId(ReservationId.class));
    }

    public Reservation(@NonNull HotelIdVO hotelId, @NonNull Money pricePerNight, int numOfNights){
        super(DomainObjectId.randomId(ReservationId.class));
        this.hotelId = hotelId;
        this.pricePerNight = pricePerNight;
        this.numOfNights = numOfNights;
    }

    public Money subtotal() {
        return pricePerNight.multiply(numOfNights);
    }

}
