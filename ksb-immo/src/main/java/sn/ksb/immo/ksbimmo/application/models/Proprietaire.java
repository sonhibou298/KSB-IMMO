package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Proprietaire extends Utilisateur {


    private String nom;

    private String prenom;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cni;

    private String profession;

    private Date dateDelivrance;

    private Date dateNaissance;

    private String adresse;

    @Column(unique = true)
    private String numCompteBancaire;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Propriete> proprietes;

    private Date dateCreation;

    private Date dateModification;

    private String createdBy;

    private String modifiedBy;

    private Boolean deleted;

    private String deletedBy;

    private Date dateDeleted;

}