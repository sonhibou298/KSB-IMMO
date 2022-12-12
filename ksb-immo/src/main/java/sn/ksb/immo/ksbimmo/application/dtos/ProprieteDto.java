package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProprieteDto {
    private String id;

    private String type;
    private String description;
    private String adresse;
    private Double prix;
    private Double surface;
    private Integer nbrePiece;
    private Integer nbreChambre;
    private Integer nbreSalleDeBain;
    private Integer nbreToilette;
    private Integer nbreEtage;
    private Integer nbreAppartementParEtage;
    private Boolean meuble;
    private Boolean garage;
    private Boolean piscine;
    private Boolean jardin;
    private Boolean ascenseur;
    private ApporteurDto apporteurAffaire;
    private String agenceId;

    private String dateDisponibilite;
    private String proprietaireId;
}
