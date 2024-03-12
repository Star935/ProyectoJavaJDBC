package mapping.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientDTO (Integer id, String firs_name, String last_name, String email, String user, LocalDate birthday_date){
}
