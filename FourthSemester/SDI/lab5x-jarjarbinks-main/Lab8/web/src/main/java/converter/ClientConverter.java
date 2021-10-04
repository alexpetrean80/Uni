package converter;

import domain.baseEntities.BaseEntity;
import domain.baseEntities.Client;
import dto.ClientDto;
import dto.Dto;

public class ClientConverter implements Converter<Long, Client, ClientDto> {

    @Override
    public ClientDto toDto(Client model) {
        var dto = new ClientDto();
        dto.setAddress(model.getAddress());
        dto.setName(model.getName());
        dto.setPhoneNumber(model.getPhoneNumber());
        dto.setId(model.getId());
        return dto;
    }
}
