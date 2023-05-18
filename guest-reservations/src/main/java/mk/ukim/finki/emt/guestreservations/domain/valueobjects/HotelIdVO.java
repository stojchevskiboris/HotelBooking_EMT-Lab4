package mk.ukim.finki.emt.guestreservations.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class HotelIdVO extends DomainObjectId {
    private HotelIdVO(){
        super(HotelIdVO.randomId(HotelIdVO.class).getId());
    }

    public HotelIdVO(String uuid){
        super(uuid);
    }
}
