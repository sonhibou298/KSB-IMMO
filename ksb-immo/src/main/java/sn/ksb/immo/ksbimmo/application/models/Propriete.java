package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import sn.ksb.immo.ksbimmo.application.enums.TypePropriete;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Propriete {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    private String description;

    private String adresse;

    @Enumerated(EnumType.STRING)
    private TypePropriete type;

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

    @Column(columnDefinition = "boolean default false")
    private Boolean status;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private Apporteur apporteurAffaire;

    @JsonIgnore
    @ManyToOne(targetEntity = Proprietaire.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Proprietaire proprietaire;

    @ManyToOne
    private Agence agence;

    private Date dateDisponibilite;

    private Date dateCreation;

    private Date dateModification;

    private String createdBy;

    private String modifiedBy;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;


}
