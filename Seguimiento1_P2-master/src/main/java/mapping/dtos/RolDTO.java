package mapping.dtos;

import lombok.Builder;

@Builder
public record RolDTO (Integer id,String name, Integer salary){
}
