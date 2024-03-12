package mapping.dtos;

import lombok.Builder;
import model.Rol;

import java.time.LocalDate;

@Builder
public record EmployeeDTO (Integer id, String first_name, String last_name, Rol rol, LocalDate birthday_date){
}
