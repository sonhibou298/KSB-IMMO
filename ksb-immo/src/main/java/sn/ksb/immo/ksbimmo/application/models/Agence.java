package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    private String adresse;

    private String region;

    private String departement;

    @Column(unique = true)
    private String telephone;

    private Date dateCreation;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<Employee> employees;

}
