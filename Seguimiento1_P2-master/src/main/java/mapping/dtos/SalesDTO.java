package mapping.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SalesDTO(Integer invoice_number, LocalDate date) {
}
