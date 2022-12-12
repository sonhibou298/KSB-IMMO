package sn.ksb.immo.ksbimmo.application.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class LoyerDto {
    private Double mensualite;
    private Double caution;
    private String dateDebut;
    private Integer dureeBail;
}
