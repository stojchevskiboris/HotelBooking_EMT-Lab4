package mk.ukim.finki.emt.hotelcatalog.services.form;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class HotelForm {
    private String hotelName;
    private Money pricePerNight;
    private int sales;
}
