package roomescape.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.payment.OrderId;
import roomescape.domain.payment.Payment;
import roomescape.domain.payment.PaymentAmount;
import roomescape.domain.payment.PaymentClient;
import roomescape.domain.payment.PaymentKey;
import roomescape.domain.payment.PaymentRepository;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentClient paymentClient;

    public Payment pay(final PaymentKey paymentKey, final OrderId orderId, final PaymentAmount amount) {
        var payment = paymentClient.confirm(new PaymentConfirmRequest(paymentKey.value(), orderId.value(), amount.value()));
        paymentRepository.save(payment);
        return payment;
    }
}
