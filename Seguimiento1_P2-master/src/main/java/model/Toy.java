package model;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Toy {
    private Integer id;
    private String name;
    private TypeToy type;
    private Integer price_unit;
    private Integer price_total;
    private Integer amount;
}
