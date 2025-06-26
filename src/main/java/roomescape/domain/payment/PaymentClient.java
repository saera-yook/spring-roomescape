package roomescape.domain.payment;

import roomescape.application.PaymentConfirmRequest;
import roomescape.application.reponse.PaymentConfirmResponse;

public interface PaymentClient {
    PaymentConfirmResponse confirm(final PaymentConfirmRequest request);
}
