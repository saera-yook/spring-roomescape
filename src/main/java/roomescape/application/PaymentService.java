package roomescape.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.payment.OrderId;
import roomescape.domain.payment.Payment;
import roomescape.domain.payment.PaymentAmount;
import roomescape.domain.payment.PaymentClient;
import roomescape.domain.payment.PaymentKey;
import roomescape.domain.payment.PaymentRepository;
import roomescape.domain.reservation.Reservation;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentClient paymentClient;

    public Payment pay(final PaymentConfirmRequest request, final Reservation reservation) {
        var response = paymentClient.confirm(request);
        return register(response.paymentKey(), response.orderId(), response.totalAmount(), reservation);
    }

    private Payment register(final String paymentKey, final String orderId, final Long amount, final Reservation reservation) {
        var payment = new Payment(
                new PaymentKey(paymentKey),
                new OrderId(orderId),
                new PaymentAmount(amount),
                reservation
        );
        return paymentRepository.save(payment);
    }
}
