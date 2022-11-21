package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apporteur {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String nom;

    private String prenom;

    private String adresse;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    private String profession;

    private Date dateDelivranceCni;

    private Date dateNaissance;

    @Column(unique = true)
    private String cni;

    @Column(unique = true)
    private String numCompteBancaire;

    @OneToMany
    private List<Propriete> proprietes;

}
