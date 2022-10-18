package sn.ksb.immo.propertyservice.property.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sn.ksb.immo.propertyservice.property.enums.MeterType;
import sn.ksb.immo.propertyservice.property.enums.PropertyType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Property {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    @Temporal(TemporalType.DATE)
    private Date availabilityDate;

    private Double area;

    @Enumerated(EnumType.STRING)
    private MeterType electricity;

    @Enumerated(EnumType.STRING)
    private MeterType water;

    private Integer bedroomsNo;

    private Integer bathroomsNo;

    private Integer livingRoomsNo;

    private Integer diningRoomsNo;

    private Integer toiletsNo;

    private Integer kitchensNo;

    private Integer garagesNo;

    private UUID landlordId;

    private boolean deleted;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
