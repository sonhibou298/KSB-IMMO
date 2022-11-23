package sn.ksb.immo.ksbimmo.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AgenceDto {
    private String id;
    private String nom;
    private String region;
    private String departement;
    private String telephone;
    private String adresse;
    private List<EmployeeDto> employees;
}
