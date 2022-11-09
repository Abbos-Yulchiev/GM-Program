import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    private UUID id;
    private String homeNumber;
    private String streetName;
    private String city;
    private String district;
    private String country;
}
