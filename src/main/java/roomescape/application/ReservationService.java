package roomescape.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.ReservationRepository;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public void removeById(final long id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("해당하는 예약이 존재하지 않습니다. id: " + id);
        }

        reservationRepository.deleteById(id);
    }
}
