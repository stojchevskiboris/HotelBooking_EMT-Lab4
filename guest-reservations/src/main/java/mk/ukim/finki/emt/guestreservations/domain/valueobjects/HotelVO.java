package mk.ukim.finki.emt.guestreservations.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class HotelVO implements ValueObject {
    private final HotelIdVO id;
    private final String name;
    private final Money pricePerNight;
    private final int sales;

    private HotelVO(){
        this.id = HotelIdVO.randomId(HotelIdVO.class);
        this.name = "";
        this.pricePerNight = Money.valueOf(Currency.MKD,0);
        this.sales = 0;
    }

    @JsonCreator
    public HotelVO(
            @JsonProperty("id") HotelIdVO id,
            @JsonProperty("hotelName") String name,
            @JsonProperty("pricePerNight") Money pricePerNight,
            @JsonProperty("sales") int sales
    ){
        this.id = id;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.sales = sales;
    }
}
