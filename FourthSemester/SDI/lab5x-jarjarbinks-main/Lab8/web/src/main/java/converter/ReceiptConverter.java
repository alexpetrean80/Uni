package converter;

import domain.baseEntities.Receipt;
import dto.ReceiptDto;

public class ReceiptConverter implements Converter<Long, Receipt, ReceiptDto> {
    @Override
    public ReceiptDto toDto(Receipt model) {
        var dto = new ReceiptDto();
        dto.setId(model.getId());
        dto.setPurchaseID(model.getPurchaseID());
        dto.setTotalPrice(model.getTotalPrice());
        return dto;
    }
}
