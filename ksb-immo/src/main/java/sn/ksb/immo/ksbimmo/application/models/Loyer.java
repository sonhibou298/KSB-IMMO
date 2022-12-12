package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loyer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Loyer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private Double montant;

    private Date dateDebut;

    private Date dateFin;

    private Date dernierPaiement;

    private Date dateProchainPaiement;

    private Double caution;

    private Double mensualite;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Propriete propriete;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locataire_id", referencedColumnName = "id")
    @JsonIgnore
    private Locataire locataire;

    @OneToMany(cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Mensualite> mensualites;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Employee employe;

}