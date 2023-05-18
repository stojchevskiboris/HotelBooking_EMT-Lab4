package mk.ukim.finki.emt.hotelcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

public class Hotel extends AbstractEntity<HotelId> {
    private String hotelName;
    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="pricePerNight_amount")),
            @AttributeOverride(name="currency", column = @Column(name="pricePerNight_currency"))
    })
    private Money pricePerNight;

    private Hotel(){
        super(HotelId.randomId(HotelId.class));
    }

    public static Hotel build(String hotelName, Money pricePerNight, int sales){
        Hotel h = new Hotel();
        h.pricePerNight = pricePerNight;
        h.sales = sales;
        return h;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }

}
