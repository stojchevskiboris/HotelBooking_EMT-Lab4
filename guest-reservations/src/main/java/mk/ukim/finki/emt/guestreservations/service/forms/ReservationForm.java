package mk.ukim.finki.emt.guestreservations.service.forms;

import lombok.Data;
import lombok.NonNull;
import mk.ukim.finki.emt.guestreservations.domain.valueobjects.HotelVO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReservationForm {
    @NotNull
    private HotelVO hotel;

    @Min(1)
    private int numOfNights = 1;
}
