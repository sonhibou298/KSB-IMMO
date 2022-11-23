package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SituationProfessionnelle {

        private String nomEmployeur;

        private String adresseEmployeur;

        private String telephoneEmployeur;

        private String emailEmployeur;

        private String posteOccupe;

        private String salaire;

        private String dateEmbauche;

        private String dateDepart;

        private String motifDepart;

}