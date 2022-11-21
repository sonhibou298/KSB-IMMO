package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Employee extends Utilisateur{

    @Column(unique = true)
    private String matricule;

    private String nom;

    private String prenom;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(unique = true)
    private String numCompteBancaire;

    @Column(columnDefinition = "boolean default false")
    private Boolean manager;

    @ManyToOne
    @JsonIgnore
    private Agence agence;

}