package mk.ukim.finki.emt.guestreservations.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ReservationId extends DomainObjectId {
    private ReservationId(){
        super(ReservationId.randomId(ReservationId.class).getId());
    }

    public ReservationId(String uuid){
        super(uuid);

    }
}
