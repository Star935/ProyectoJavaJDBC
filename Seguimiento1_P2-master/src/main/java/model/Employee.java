package model;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    private Integer id;
    private String first_name;
    private String last_name;
    private Rol rol;
    private LocalDate birthday_date;
}
