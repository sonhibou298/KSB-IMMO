package sn.ksb.immo.propertyservice.property.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {
    @NotEmpty(message = "Property type cannot be empty")
    @NotNull(message = "Property type cannot be null")
    @NotBlank(message = "Property type cannot be blank")
    private String type;

    @NotEmpty(message = "Property address cannot be empty")
    @NotNull(message = "Property address cannot be null")
    @NotBlank(message = "Property address cannot be blank")
    private String address;

    @NotEmpty(message = "Property description cannot be empty")
    @NotNull(message = "Property description cannot be null")
    @NotBlank(message = "Property description cannot be blank")
    private String description;

    @NotEmpty(message = "Property price cannot be empty")
    @NotNull(message = "Property price cannot be null")
    @NotBlank(message = "Property price cannot be blank")
    private String price;

    @NotEmpty(message = "Property Date cannot be empty")
    @NotNull(message = "Property Date cannot be null")
    @NotBlank(message = "Property Date cannot be blank")
    private String availabilityDate;

    @NotEmpty(message = "Property area cannot be empty")
    @NotNull(message = "Property area cannot be null")
    @NotBlank(message = "Property area cannot be blank")
    private Double area;

    @NotEmpty(message = "Property electricity cannot be empty")
    @NotNull(message = "Property electricity cannot be null")
    @NotBlank(message = "Property electricity cannot be blank")
    private String electricity;

    @NotEmpty(message = "Property water cannot be empty")
    @NotNull(message = "Property water cannot be null")
    @NotBlank(message = "Property water cannot be blank")
    private String water;

    @NotEmpty(message = "Property bedrooms cannot be empty")
    @NotNull(message = "Property bedrooms cannot be null")
    @NotBlank(message = "Property bedrooms cannot be blank")
    private Integer bedroomsNo;

    @NotEmpty(message = "Property bathrooms cannot be empty")
    @NotNull(message = "Property bathrooms cannot be null")
    @NotBlank(message = "Property bathrooms cannot be blank")
    private Integer bathroomsNo;

    @NotEmpty(message = "Property toilets cannot be empty")
    @NotNull(message = "Property toilets cannot be null")
    @NotBlank(message = "Property toilets cannot be blank")
    private Integer toiletsNo;

    @NotEmpty(message = "Property kitchens cannot be empty")
    @NotNull(message = "Property kitchens cannot be null")
    @NotBlank(message = "Property kitchens cannot be blank")
    private Integer kitchensNo;

    @NotEmpty(message = "Property garage cannot be empty")
    @NotNull(message = "Property garage cannot be null")
    @NotBlank(message = "Property garage cannot be blank")
    private Integer garagesNo;
}
