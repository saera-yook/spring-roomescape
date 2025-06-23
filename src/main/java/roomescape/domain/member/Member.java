package roomescape.domain.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private MemberName name;
    @Embedded
    private Email email;
    @Embedded
    private Password password;

    public Member(final Long id, final MemberName name, final Email email, final Password password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member(final MemberName name, final Email email, final Password password) {
        this(null, name, email, password);
    }
}
