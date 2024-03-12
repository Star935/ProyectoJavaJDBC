package mapping.mappers;

import mapping.dtos.DetailsDTO;
import model.Details;

public class DetailsMapper {
    public static DetailsDTO mapFromModel(Details details){
        return new DetailsDTO(details.getId(), details.getClient(),details.getEmployee(),details.getToy(),details.getSales());
    }
    public static  Details mapfromDTO(DetailsDTO detailsDTO){
        return Details.builder()
                .id(detailsDTO.id())
                .client(detailsDTO.client())
                .employee(detailsDTO.employee())
                .toy(detailsDTO.toy())
                .sales(detailsDTO.sales())
                .build();
    }
}
