package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Locataire extends Utilisateur{

    private String nom;

    private String prenom;

    private String adresse;

    private String telephone;

    private String email;

    private String profession;

    private String cni;

    @OneToOne
    private Locataire conjoint;

}
