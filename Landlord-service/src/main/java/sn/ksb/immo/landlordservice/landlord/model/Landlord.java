package sn.ksb.immo.landlordservice.landlord.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Landlord {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(unique = true)
    private String passportNumber;

    @Column(unique = true)
    private String idCardNumber;

    private String accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Landlord landlord = (Landlord) o;
        return id != null && Objects.equals(id, landlord.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
