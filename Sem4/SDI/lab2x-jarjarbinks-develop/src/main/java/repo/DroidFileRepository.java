package repo;

import domain.baseEntities.Droid;
import domain.validators.IValidator;
import exceptions.ExistingDroidException;
import exceptions.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DroidFileRepository extends InMemoryRepository<Long, Droid>{
    private String fileName;
    public DroidFileRepository(IValidator<Droid> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        this.loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try{
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));
                Long id = Long.valueOf(items.get(0));
                double powerUsage = Double.parseDouble(items.get(1));
                double price = Double.parseDouble(items.get(2));
                int batteryTime = Integer.parseInt(items.get(3));
                String model = items.get(4);
                boolean driver = Boolean.parseBoolean(items.get(5));

                Droid droid = new Droid(powerUsage,price,batteryTime,model,driver);
                droid.setId(id);

                try{
                    super.save(droid);
                }catch(ValidatorException e){
                    e.printStackTrace();
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Droid> save(Droid entity) throws ValidatorException{
        Optional<Droid> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        this.saveToFile(entity);
        return Optional.empty();

    }

    private void saveToFile(Droid entity){
        Path path = Paths.get(this.fileName);

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            bufferedWriter.write(
                    entity.getId() + "," + entity.getPowerUsage() + "," + entity.getPrice() + "," + entity.getBatteryTime() + "," +  entity.getModel() + "," + entity.isDriver());
            bufferedWriter.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
