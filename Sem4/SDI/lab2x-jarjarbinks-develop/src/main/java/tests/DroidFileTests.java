package tests;

import domain.baseEntities.Droid;
import domain.validators.DroidValidator;
import domain.validators.IValidator;
import org.junit.Test;
import repo.DroidFileRepository;
import repo.IRepository;

public class DroidFileTests {

    @Test
    public void Test__WriteDroidToFile__IsReadBack(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> droidRepository = new DroidFileRepository(droidValidator, "D:\\Facultate\\UBB\\SDI\\src\\main\\java\\repo\\droids.txt");
        Droid newDroid = new Droid(1.1,1.1,2,"c3po",true);
        newDroid.setId(1L);
        droidRepository.save(newDroid);
        IRepository<Long, Droid> droidRepository2 = new DroidFileRepository(droidValidator, "D:\\Facultate\\UBB\\SDI\\src\\main\\java\\repo\\droids.txt");
        assert(droidRepository2.findOne(1L).get().equals(newDroid));
    }


}
