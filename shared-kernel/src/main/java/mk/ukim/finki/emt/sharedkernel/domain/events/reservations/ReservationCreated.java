package mk.ukim.finki.emt.sharedkernel.domain.events.reservations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ReservationCreated extends DomainEvent {

    private String hotelId;
    private int quantity;

    public ReservationCreated(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
    }

    public ReservationCreated(String hotelId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CREATED);
        this.hotelId = hotelId;
        this.quantity = quantity;
    }
}
