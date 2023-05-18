package mk.ukim.finki.emt.guestreservations.service.forms;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Value;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class GuestForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<ReservationForm> reservations = new ArrayList<>();
}
