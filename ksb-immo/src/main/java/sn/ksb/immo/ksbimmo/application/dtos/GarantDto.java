package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import sn.ksb.immo.ksbimmo.application.models.SituationProfessionnelle;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@ToString
public class GarantDto {

    private String id;

    private String cni;

    private String dateDelivranceCni;
    private String nom;

    private String prenom;

    private String adresse;

    private String telephone;

    private String email;

    private String profession;

    private SituationProfessionnelleDto situationProfessionnelle;

}
