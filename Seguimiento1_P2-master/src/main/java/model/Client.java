package model;

import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Client {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String user;
    private LocalDate birthday_date;
}
