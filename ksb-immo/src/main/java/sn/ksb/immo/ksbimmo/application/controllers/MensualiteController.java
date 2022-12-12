package sn.ksb.immo.ksbimmo.application.controllers;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.ksbimmo.application.dtos.MensualiteDto;
import sn.ksb.immo.ksbimmo.application.models.Mensualite;
import sn.ksb.immo.ksbimmo.application.services.MensualiteService;

@RestController
@Slf4j
@OpenAPIDefinition(tags = {@Tag(name = "Mensualité", description = "Mensualité API")})
@RequestMapping("/api/mensualite")
public class MensualiteController {

    private final MensualiteService mensualiteService;

    public MensualiteController(MensualiteService mensualiteService) {
        this.mensualiteService = mensualiteService;
    }

    //enregister un paiement
    @PostMapping
    public Mensualite createMensualite(@RequestBody MensualiteDto dto) {
        log.info("Entrée dans la méthode createMensualite du controller MensualiteController");
        Mensualite mensualite = null;
        try {
            mensualite = mensualiteService.createMensualite(dto);
        }catch (Exception e) {
            log.error("Erreur lors la création de l'objet : {}", e.getMessage());
        }
        if (mensualite == null) {
            log.error("Erreur lors la création de l'objet");
        }
        log.info("Sortie de la méthode createMensualite du controller MensualiteController");
        return mensualite;
    }
}
