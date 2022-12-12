package sn.ksb.immo.ksbimmo.application.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import sn.ksb.immo.ksbimmo.application.dtos.*;
import sn.ksb.immo.ksbimmo.application.models.Agence;
import sn.ksb.immo.ksbimmo.application.models.Locataire;
import sn.ksb.immo.ksbimmo.application.models.Proprietaire;
import sn.ksb.immo.ksbimmo.application.services.*;

import java.util.List;


@Configuration
public class InitDatas implements CommandLineRunner {
    @Autowired
    private AgenceService agenceService;

    @Autowired
    private ProprietaireService proprietaireService;

    @Autowired
    private LocataireService locataireService;

    @Autowired
    private MensualiteService mensualiteService;


    @Override
    public void run(String... args) throws Exception {
        AgenceDto agenceDto1 = AgenceDto.builder()
                .nom("KSB IMMO 1")
                .adresse("Dakar")
                .region("Dakar")
                .departement("Dakar")
                .telephone("77 000 00 00")
                .employees(List.of(
                        EmployeeDto.builder()
                                .nom("Doe")
                                .prenom("John")
                                .cni("123456789")
                                .adresse("Dakar")
                                .email("john@ksb.sn")
                                .dateDelivranceCni("01/01/2020")
                                .telephone("77 000 00 00")
                                .numCompteBancaire("123456789")
                                .manager(true)
                                .build(),
                        EmployeeDto.builder()
                                .nom("Doe")
                                .prenom("Jane")
                                .cni("987654321")
                                .adresse("Dakar")
                                .email("jane@ksb.sn")
                                .telephone("77 111 11 11")
                                .dateDelivranceCni("23/10/2018")
                                .numCompteBancaire("987654321")
                                .manager(false)
                                .build()
                ))
                .build();

        AgenceDto agenceDto2 = AgenceDto.builder()
                .nom("KSB IMMO 2")
                .adresse("Thies")
                .region("Thies")
                .departement("Thies")
                .telephone("77 333 33 33")
                .employees(List.of(
                        EmployeeDto.builder()
                                .nom("Doe")
                                .prenom("Josh")
                                .cni("654789321")
                                .adresse("Thies")
                                .email("josh@ksb.sn")
                                .dateDelivranceCni("01/01/2020")
                                .telephone("77 444 44 44")
                                .numCompteBancaire("147852369")
                                .manager(true)
                                .build(),
                        EmployeeDto.builder()
                                .nom("Doe")
                                .prenom("Jess")
                                .cni("369852147")
                                .adresse("Thies")
                                .email("jess@ksb.sn")
                                .telephone("77 555 55 55")
                                .dateDelivranceCni("23/10/2018")
                                .numCompteBancaire("258741369")
                                .manager(false)
                                .build()
                ))
                .build();
        Agence agence1 = agenceService.addAgence(agenceDto1);
        Agence agence2 = agenceService.addAgence(agenceDto2);

        //creer deux proprietaires
        ProprietaireDto proprietaireDto1 = ProprietaireDto.builder()
                .nom("Diop")
                .prenom("Moussa")
                .cni("235489315")
                .adresse("Dakar")
                .email("moussa@ksb.sn")
                .dateDelivranceCni("01/01/2020")
                .telephone("77 666 66 66")
                .numCompteBancaire("234567891")
                .dateNaissance("01/01/1990")
                .profession("Developpeur")
                .proprietes(List.of(
                        ProprieteDto.builder()
                                .adresse("Dakar")
                                .description("Maison de 3 pieces")
                                .prix(175000.0)
                                .agenceId(String.valueOf(agence1.getId()))
                                .garage(false)
                                .jardin(false)
                                .type("Maison")
                                .piscine(false)
                                .meuble(false)
                                .nbreEtage(0)
                                .dateDisponibilite("01/01/2023")
                                .nbrePiece(3)
                                .nbreChambre(2)
                                .nbreSalleDeBain(1)
                                .nbreToilette(1)
                                .surface(100.0)
                                .ascenseur(false)
                                .build(),
                        ProprieteDto.builder()
                                .adresse("Dakar")
                                .description("Appartement de 4 pieces")
                                .prix(250000.0)
                                .agenceId(String.valueOf(agence1.getId()))
                                .garage(false)
                                .jardin(false)
                                .type("Appartement")
                                .piscine(false)
                                .meuble(false)
                                .dateDisponibilite("01/01/2023")
                                .nbreEtage(0)
                                .nbrePiece(4)
                                .nbreChambre(2)
                                .nbreSalleDeBain(1)
                                .nbreToilette(1)
                                .surface(100.0)
                                .ascenseur(false)
                                .build()
                ))
                .build();

        ProprietaireDto proprietaireDto2 = ProprietaireDto.builder()
                .nom("Diop")
                .prenom("Mamadou")
                .cni("4236584234")
                .adresse("Dakar")
                .email("mamadou@ksb.sn")
                .dateDelivranceCni("01/01/2020")
                .telephone("77 777 77 77")
                .numCompteBancaire("694869486")
                .dateNaissance("01/01/1990")
                .profession("Boulanger")
                .proprietes(List.of(
                        ProprieteDto.builder()
                                .adresse("Thies")
                                .description("Maison de 3 pieces")
                                .prix(175000.0)
                                .agenceId(String.valueOf(agence2.getId()))
                                .garage(false)
                                .jardin(false)
                                .type("Maison")
                                .piscine(false)
                                .meuble(false)
                                .nbreEtage(0)
                                .nbrePiece(3)
                                .nbreChambre(2)
                                .nbreSalleDeBain(1)
                                .dateDisponibilite("01/01/2023")
                                .nbreToilette(1)
                                .surface(100.0)
                                .ascenseur(false)
                                .build(),
                        ProprieteDto.builder()
                                .adresse("Thies")
                                .description("Appartement de 4 pieces")
                                .prix(250000.0)
                                .agenceId(String.valueOf(agence2.getId()))
                                .garage(false)
                                .jardin(false)
                                .type("Appartement")
                                .piscine(false)
                                .meuble(false)
                                .nbreEtage(0)
                                .dateDisponibilite("01/01/2023")
                                .nbrePiece(4)
                                .nbreChambre(2)
                                .nbreSalleDeBain(1)
                                .nbreToilette(1)
                                .surface(100.0)
                                .ascenseur(false)
                                .build()
                )).build();
        Proprietaire proprietaire1 = proprietaireService.add(proprietaireDto1);
        Proprietaire proprietaire2 = proprietaireService.add(proprietaireDto2);

        //creer deux locataires
        LocataireDto locataireDto1 = LocataireDto.builder()
                .nom("Diop")
                .prenom("Macoumba")
                .adresse("Dakar")
                .cni("569542839540")
                .dateDelivranceCni("01/01/2020")
                .telephone("77 685 74 85")
                .numCompteBancaire("745896321")
                .garant(
                        GarantDto.builder()
                                .adresse("Dakar")
                                .nom("Diop")
                                .prenom("Alioune")
                                .cni("2354893158")
                                .dateDelivranceCni("01/01/2020")
                                .telephone("77 666 66 88")
                                .profession("Developpeur")
                                .situationProfessionnelle(
                                        SituationProfessionnelleDto.builder()
                                                .nomEmployeur("KSB")
                                                .adresseEmployeur("Dakar")
                                                .telephoneEmployeur("77 666 66 11")
                                                .salaire(100000.0)
                                                .dateEmbauche("01/01/2020")
                                                .dateDepart("01/01/2021")
                                                .emailEmployeur("employeur1@ksb.com")
                                                .posteOccupe("Developpeur")
                                                .build()
                                )
                                .build()
                )
                .profession("Developpeur")
                .situationProfessionnelle(
                        SituationProfessionnelleDto.builder()
                                .nomEmployeur("KSB")
                                .adresseEmployeur("Dakar")
                                .telephoneEmployeur("77 666 66 11")
                                .salaire(100000.0)
                                .dateEmbauche("01/01/2020")
                                .dateDepart("01/01/2021")
                                .emailEmployeur("employeur2@ksb.com")
                                .posteOccupe("Developpeur")
                                .build()
                )
                .proprieteId(proprietaire1.getProprietes().get(0).getId().toString())
                .loyer(
                        LoyerDto.builder()
                                .mensualite(100000.0)
                                .dateDebut("01/01/2021")
                                .dureeBail(48)
                                .caution(200000.0)
                                .build()
                )
                .build();

        LocataireDto locataireDto2 = LocataireDto.builder()
                .nom("Diop")
                .prenom("Elhadji")
                .adresse("Dakar")
                .cni("56954283954084")
                .dateDelivranceCni("01/01/2020")
                .telephone("77 666 66 36")
                .numCompteBancaire("74589632231")
                .garant(
                        GarantDto.builder()
                                .adresse("Dakar")
                                .nom("Diop")
                                .prenom("Alioune")
                                .cni("23548931587")
                                .dateDelivranceCni("01/01/2020")
                                .telephone("77 666 66 77")
                                .profession("Developpeur")
                                .situationProfessionnelle(
                                        SituationProfessionnelleDto.builder()
                                                .nomEmployeur("KSB")
                                                .adresseEmployeur("Dakar")
                                                .telephoneEmployeur("77 666 66 22")
                                                .salaire(100000.0)
                                                .dateEmbauche("01/01/2020")
                                                .dateDepart("01/01/2021")
                                                .emailEmployeur("employeur1@ksb.com")
                                                .posteOccupe("Developpeur")
                                                .build()
                                )
                                .build()
                )
                .profession("Developpeur")
                .situationProfessionnelle(
                        SituationProfessionnelleDto.builder()
                                .nomEmployeur("KSB")
                                .adresseEmployeur("Dakar")
                                .telephoneEmployeur("77 666 66 22")
                                .salaire(100000.0)
                                .dateEmbauche("01/01/2020")
                                .dateDepart("01/01/2021")
                                .emailEmployeur("employeur2@ksb.com")
                                .posteOccupe("Developpeur")
                                .build()
                )
                .proprieteId(proprietaire2.getProprietes().get(0).getId().toString())
                .loyer(
                        LoyerDto.builder()
                                .mensualite(100000.0)
                                .dateDebut("01/01/2021")
                                .dureeBail(48)
                                .caution(200000.0)
                                .build()
                )
                .build();

        Locataire locataire1 = locataireService.createLocataire(locataireDto1);
        Locataire locataire2 = locataireService.createLocataire(locataireDto2);

        //enregitrer deux mois de mensualités pour chaque locataire
        MensualiteDto mensualiteDto1 = MensualiteDto.builder()
                .locataireId(locataire1.getId().toString())
                .montant(100000.0)
                .nombreMois(1)
                .build();

        MensualiteDto mensualiteDto2 = MensualiteDto.builder()
                .locataireId(locataire2.getId().toString())
                .montant(100000.0)
                .nombreMois(1)
                .build();

        mensualiteService.createMensualite(mensualiteDto1);
        mensualiteService.createMensualite(mensualiteDto2);

    }
}
