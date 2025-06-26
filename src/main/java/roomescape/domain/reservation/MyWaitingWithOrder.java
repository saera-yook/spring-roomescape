package roomescape.domain.reservation;

public record MyWaitingWithOrder(
        MyWaiting waiting,
        long order
) {
}
