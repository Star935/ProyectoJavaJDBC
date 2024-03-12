package mapping.mappers;

import mapping.dtos.RolDTO;
import model.Rol;

public class RolMapper {
    public static RolDTO mapFromModel(Rol rol){
        return new RolDTO(rol.getId(), rol.getName(), rol.getSalary());
    }
    public static Rol mapFromDTO(RolDTO rolDTO){
        return Rol.builder()
                .id(rolDTO.id())
                .name(rolDTO.name())
                .salary(rolDTO.salary())
                .build();
    }
}
