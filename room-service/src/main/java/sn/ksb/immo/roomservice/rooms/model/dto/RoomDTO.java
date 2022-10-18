package sn.ksb.immo.roomservice.rooms.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDTO {
    private String propertyId;

    private String type;
}
