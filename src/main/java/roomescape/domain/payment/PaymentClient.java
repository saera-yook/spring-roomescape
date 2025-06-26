package roomescape.domain.payment;

import roomescape.application.PaymentConfirmRequest;

public interface PaymentClient {
    Payment confirm(final PaymentConfirmRequest request);
}
