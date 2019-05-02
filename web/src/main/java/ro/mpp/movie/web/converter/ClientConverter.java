package ro.mpp.movie.web.converter;

import org.springframework.stereotype.Component;
import ro.mpp.movie.core.model.Client;
import ro.mpp.movie.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientdto = ClientDto.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .build();
        clientdto.setId(client.getId());
        return clientdto;
    }

}
