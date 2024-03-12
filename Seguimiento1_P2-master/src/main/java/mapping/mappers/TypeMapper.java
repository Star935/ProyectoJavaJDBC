package mapping.mappers;

import mapping.dtos.TypeDTO;
import model.TypeToy;

public class TypeMapper {
    public static TypeDTO mapFromModel(TypeToy type){
        return new TypeDTO(type.getId(), type.getType());
    }
    public static TypeToy mapFromDTO (TypeDTO typeDTO){
        return TypeToy.builder()
                .id(typeDTO.id())
                .type(typeDTO.type())
                .build();
    }
}
