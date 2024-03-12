package model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Details {
    private Integer id;
    private Client client;
    private Employee employee;
    private Toy toy;
    private Sale sales;
}
