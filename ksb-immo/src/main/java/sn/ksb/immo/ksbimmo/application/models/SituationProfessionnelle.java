package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

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

        private Double salaire;

        private Date dateEmbauche;

        private Date dateDepart;


}