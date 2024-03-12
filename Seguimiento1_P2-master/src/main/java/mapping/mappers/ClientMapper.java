package mapping.mappers;

import mapping.dtos.ClientDTO;
import model.Client;

public class ClientMapper {
    public static ClientDTO mapFromModel(Client client){
        return new ClientDTO(client.getId(), client.getFirst_name(), client.getLast_name(), client.getUser(), client.getEmail(), client.getBirthday_date());
    }
    public static Client mapFromDTO(ClientDTO clientDTO){
        return Client.builder()
                .first_name(clientDTO.firs_name())
                .last_name(clientDTO.last_name())
                .user(clientDTO.user())
                .email(clientDTO.email())
                .birthday_date(clientDTO.birthday_date())
                .build();
    }
}
