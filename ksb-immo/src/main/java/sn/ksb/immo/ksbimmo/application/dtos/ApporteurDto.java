package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Data;

@Data
public class ApporteurDto {
    private String id;
    private String cni;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String adresse;
    private String profession;
    private String dateDelivranceCni;
    private String dateNaissance;
    private String numCompteBancaire;
}
