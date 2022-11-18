package sn.ksb.immo.ksbimmo.application.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Agence {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;


    private String nom;

    @NotNull
    @NotBlank
    @NotEmpty
    private String adresse;

    private String region;

    private String departement;

    private String ville;

    @Column(unique = true)
    @NotNull
    @NotBlank
    @NotEmpty
    private String telephone;

    private Date dateCreation;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;

}
