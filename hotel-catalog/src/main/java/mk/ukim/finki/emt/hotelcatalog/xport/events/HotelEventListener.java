package mk.ukim.finki.emt.hotelcatalog.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.hotelcatalog.domain.models.HotelId;
import mk.ukim.finki.emt.hotelcatalog.services.HotelService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.reservations.ReservationRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HotelEventListener {
    private final HotelService hotelService;

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_CREATED, groupId = "hotelcatalog")
    public void useReservationCreatedEvent(String jsonMessage){
        try {
            ReservationCreated event = DomainEvent.fromJson(jsonMessage, ReservationCreated.class);
            hotelService.reservationCreated(HotelId.of(event.getHotelId()),event.getQuantity());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_RESERVATION_REMOVED,groupId = "hotelCatalog")
    public void useReservationRemovedEvent(String jsonMessage){
        try {
            ReservationRemoved event = DomainEvent.fromJson(jsonMessage, ReservationRemoved.class);
            hotelService.reservationRemoved(HotelId.of(event.getHotelId()),event.getQuantity());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
