package converter;

import domain.baseEntities.Droid;
import dto.DroidDto;

public class DroidConverter implements Converter<Long, Droid, DroidDto> {


    @Override
    public DroidDto toDto(Droid model) {
        var d = new DroidDto();
        d.setModel(model.getModel());
        d.setBatteryTime(model.getBatteryTime());
        d.setDriver(model.getDriver());
        d.setPrice(model.getPrice());
        d.setPowerUsage(model.getPowerUsage());
        d.setId(model.getId());
        return d;
    }
}
