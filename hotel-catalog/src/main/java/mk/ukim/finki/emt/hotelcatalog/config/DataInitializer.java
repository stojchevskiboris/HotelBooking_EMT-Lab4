package mk.ukim.finki.emt.hotelcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.hotelcatalog.domain.models.Hotel;
import mk.ukim.finki.emt.hotelcatalog.domain.repository.HotelRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final HotelRepository hotelRepository;

    @PostConstruct
    public void initData(){
        Hotel h1 = Hotel.build("Alexandar Palace", Money.valueOf(Currency.MKD,4500),25);
        Hotel h2 = Hotel.build("Bridge Stone", Money.valueOf(Currency.MKD,5500),15);
        if (hotelRepository.findAll().isEmpty()){
            hotelRepository.saveAll(Arrays.asList(h1,h2));
        }
    }
}
