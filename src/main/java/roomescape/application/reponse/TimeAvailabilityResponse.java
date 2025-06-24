package roomescape.application.reponse;

import java.time.LocalTime;

public record TimeAvailabilityResponse(
        long id,
        LocalTime startAt,
        boolean alreadyBooked
) {
}
