package demo.com.entity.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    String title;
    String place;
    String speaker;
    String eventType;
    LocalDateTime dateTime;
}
