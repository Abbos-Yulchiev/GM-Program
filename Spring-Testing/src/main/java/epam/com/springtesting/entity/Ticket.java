package epam.com.springtesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ticket implements Serializable {

    public enum Categories {STANDARD, PREMIUM, BAR}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int userId;

    private long eventId;

    @Enumerated(EnumType.STRING)
    private Categories categories;

    private int addressId;

    private double price;

    private boolean sold = false;

    public Ticket(long eventId, Categories categories, int addressId, double price) {
        this.eventId = eventId;
        this.categories = categories;
        this.addressId = addressId;
        this.price = price;
    }
}
