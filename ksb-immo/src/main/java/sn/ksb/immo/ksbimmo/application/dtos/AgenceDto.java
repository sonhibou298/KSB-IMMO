package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class AgenceDto {
    private String id;
    private String nom;
    private String region;
    private String departement;
    private String ville;
    private String telephone;
    private Date dateCreation;
}
