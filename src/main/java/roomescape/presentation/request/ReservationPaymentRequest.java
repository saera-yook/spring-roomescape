package roomescape.presentation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ReservationPaymentRequest(
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        long themeId,
        long timeId,
        String paymentKey,
        String orderId,
        long amount
) {
}
