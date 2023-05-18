package mk.ukim.finki.emt.sharedkernel.domain.events.reservations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ReservationRemoved extends DomainEvent {
    private String hotelId;
    private int quantity;

    public ReservationRemoved(String topic){
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
    }
    public ReservationRemoved(String topic, String hotelId, int quantity){
        super(TopicHolder.TOPIC_RESERVATION_REMOVED);
        this.hotelId = hotelId;
        this.quantity = quantity;
    }
}
