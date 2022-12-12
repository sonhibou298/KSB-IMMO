package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SituationProfessionnelleDto {
    private String nomEmployeur;

    private String adresseEmployeur;

    private String telephoneEmployeur;

    private String emailEmployeur;

    private String posteOccupe;

    private Double salaire;

    private String dateEmbauche;

    private String dateDepart;

}
