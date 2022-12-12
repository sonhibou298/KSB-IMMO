package sn.ksb.immo.ksbimmo.application.dtos;


import lombok.Builder;
import lombok.Data;
import sn.ksb.immo.ksbimmo.application.annotations.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class EmployeeDto {
    private String id;

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private String prenom;

    @NotNull
    @NotEmpty
    private String telephone;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String cni;

    private String dateDelivranceCni;

    @NotNull
    @NotEmpty
    private String adresse;

    @NotNull
    @NotEmpty
    private String numCompteBancaire;
    private Boolean manager;

    @NotNull
    @NotEmpty
    private String agenceId;
}
