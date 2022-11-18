package sn.ksb.immo.ksbimmo.application.dtos;


import lombok.Data;

@Data
public class EmployeeDto {
    private String id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String cni;
    private String adresse;
    private String numCompteBancaire;
    private Boolean manager;
    private String agenceId;
}
