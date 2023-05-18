package mk.ukim.finki.emt.hotelcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class HotelId extends DomainObjectId {
    private HotelId(){
        super(HotelId.randomId(HotelId.class).getId());
    }

    public HotelId(@NonNull String uuid){
        super(uuid);
    }

    public static HotelId of (String uuid){
        HotelId h = new HotelId(uuid);
        return h;
    }
}
