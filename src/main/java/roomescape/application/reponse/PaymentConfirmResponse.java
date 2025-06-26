package roomescape.application.reponse;

public record PaymentConfirmResponse(
        String paymentKey,
        String orderId,
        Long totalAmount
) {
}
