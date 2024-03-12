package mapping.mappers;

import mapping.dtos.SalesDTO;
import model.Sale;

public class SalesMapper {
    public static SalesDTO mapFromodel(Sale sales){
        return new  SalesDTO(sales.getInvoice_number(), sales.getDate());
    }
    public static Sale mapFromDTO(SalesDTO salesDTO){
        return Sale.builder()
                .invoice_number(salesDTO.invoice_number())
                .date(salesDTO.date())
                .build();
    }
}
