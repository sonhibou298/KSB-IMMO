package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MensualiteDto {
    private String locataireId;

    private Double montant;

    private Integer nombreMois;
}
