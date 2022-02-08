package com.eojjeol.dev.room.dto;

import com.eojjeol.dev.entity.Room;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;

    private String sessionId;

    private String token;

    private String title;

    private String description;

    public static RoomDto from(Room room) {
        if(room == null) return null;
        return RoomDto.builder()
                .id(room.getId())
                .sessionId(room.getSessionId())
                .token(room.getToken())
                .title(room.getTitle())
                .description(room.getDescription())
                .build();
    }
}
