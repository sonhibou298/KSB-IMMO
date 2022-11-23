package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Data;

@Data
public class LocataireDto {

    private String nom;

    private String prenom;

    private String adresse;

    private String telephone;

    private String email;

    private String profession;

    private LocataireDto conjoint;

    private String agenceId;

    private String cni;

    private String numCompteBancaire;

    private String proprieteId;
}
