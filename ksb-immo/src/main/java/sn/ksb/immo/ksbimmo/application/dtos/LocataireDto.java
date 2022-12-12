package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;
import sn.ksb.immo.ksbimmo.application.annotations.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LocataireDto {

    private String id;

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private String prenom;

    @NotNull
    @NotEmpty
    private String adresse;

    @NotNull
    @NotEmpty
    private String telephone;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String profession;

    @NotNull
    @NotEmpty
    private GarantDto garant;

    @NotNull
    @NotEmpty
    private String cni;

    @NotNull
    @NotEmpty
    private String dateDelivranceCni;

    @NotNull
    @NotEmpty
    private String numCompteBancaire;

    @NotNull
    @NotEmpty
    private String proprieteId;

    @NotNull
    @NotEmpty
    private LoyerDto loyer;

    @NotNull
    @NotEmpty
    private SituationProfessionnelleDto situationProfessionnelle;
}
