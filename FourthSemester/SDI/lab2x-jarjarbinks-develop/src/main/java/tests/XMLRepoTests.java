package tests;

import domain.baseEntities.Droid;
import domain.validators.DroidValidator;
import domain.validators.IValidator;
import org.junit.Test;
import repo.IRepository;
import repo.XMLRepository;

public class XMLRepoTests {

    @Test
    public void Test__FindOneXMLRepository__UniqueID__FoundInTheList() {
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new XMLRepository<>("C:\\Users\\Katherine\\Desktop\\Work2\\SEM II\\MPP\\lab2x-jarjarbinks\\src\\main\\java\\tests\\data\\droids.xml",droidValidator,Droid.class);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        assert(repository.findOne(99827L).get().equals(droid_nr1));
    }


}
