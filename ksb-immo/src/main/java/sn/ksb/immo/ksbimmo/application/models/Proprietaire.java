package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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


    private String profession;


    private Date dateNaissance;

    private String adresse;

    @Column(unique = true)
    private String numCompteBancaire;

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Propriete> proprietes;

    private Date dateCreation;

    private Date dateModification;

    private String createdBy;

    private String modifiedBy;

    private Boolean deleted;

    private String deletedBy;

    private Date dateDeleted;

}
