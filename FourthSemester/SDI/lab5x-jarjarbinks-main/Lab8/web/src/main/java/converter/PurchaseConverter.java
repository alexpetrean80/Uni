package converter;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Purchase;
import dto.PurchaseDto;

public class PurchaseConverter implements Converter<ClientDroidPrimaryKey, Purchase, PurchaseDto> {

    @Override
    public PurchaseDto toDto(Purchase model) {
        var d = new PurchaseDto();
        d.setDroid(model.getDroid());
        d.setClient(model.getClient());
        d.setTotalPrice(model.getTotalPrice());
        d.setId(model.getId());
        return d;
    }
}
