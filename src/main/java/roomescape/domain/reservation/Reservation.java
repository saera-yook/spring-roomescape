package roomescape.domain.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.domain.member.Member;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private GameSchedule schedule;

    public Reservation(final Long id, final Member member, final GameSchedule schedule) {
        this.id = id;
        this.member = member;
        this.schedule = schedule;
    }

    public Reservation(final Member member, final GameSchedule schedule) {
        this(null, member, schedule);
    }
}
