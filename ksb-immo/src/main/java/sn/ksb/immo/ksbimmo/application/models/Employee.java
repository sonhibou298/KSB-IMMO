package sn.ksb.immo.ksbimmo.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Employee extends Utilisateur{

    @Column(unique = true)
    private String matricule;

    private String nom;

    private String prenom;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(unique = true)
    private String numCompteBancaire;

    @Column(columnDefinition = "boolean default false")
    private Boolean manager;

    @ManyToOne
    @JsonIgnore
    private Agence agence;

    //generer le matricule
    public void generateMatricule(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        this.matricule = "EMP-"+generatedString.toUpperCase();
    }
}