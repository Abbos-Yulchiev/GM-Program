package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
public class Ticket {

    public enum Categories {STANDARD, PREMIUM, BAR}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int userId;

    private long eventId;

    @Enumerated(EnumType.STRING)
    private Categories categories;

    private String place;

    private boolean sold;

    public Ticket() {
    }
}
