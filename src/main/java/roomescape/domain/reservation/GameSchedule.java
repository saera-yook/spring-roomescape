package roomescape.domain.reservation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.domain.theme.Theme;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class GameSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private GameTime time;

    public GameSchedule(final Long id, final Theme theme, final LocalDate date, final GameTime time) {
        this.id = id;
        this.theme = theme;
        this.date = date;
        this.time = time;
    }

    public GameSchedule(final Theme theme, final LocalDate date, final GameTime time) {
        this(null, theme, date, time);
    }
}
