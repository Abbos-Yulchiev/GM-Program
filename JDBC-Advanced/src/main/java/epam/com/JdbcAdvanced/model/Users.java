
package epam.com.JdbcAdvanced.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthday;
}
