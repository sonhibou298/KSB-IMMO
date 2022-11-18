package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProprietaireDto {
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String profession;
    private String dateDelivrance;
    private String dateNaissance;
    private String cni;
    private String numCompteBancaire;
    List<ProprieteDto> proprietes;

}
