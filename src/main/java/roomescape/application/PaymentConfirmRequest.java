package roomescape.application;

public record PaymentConfirmRequest(
        String paymentKey,
        String orderId,
        long amount
) {
}
