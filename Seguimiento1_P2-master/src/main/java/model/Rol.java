package model;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Rol {
    private Integer id;
    private String name;
    private Integer salary;
}
