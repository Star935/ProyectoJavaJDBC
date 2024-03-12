package mapping.dtos;

import lombok.Builder;
import model.Client;
import model.Employee;
import model.Sale;
import model.Toy;

@Builder
public record DetailsDTO(Integer id, Client client, Employee employee, Toy toy, Sale sales) {
}
