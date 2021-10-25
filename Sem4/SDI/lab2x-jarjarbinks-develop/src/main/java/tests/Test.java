package tests;
import domain.baseEntities.Droid;
import domain.validators.DroidValidator;
import domain.validators.IValidator;
import exceptions.ExistingDroidException;
import exceptions.ValidatorException;
import repo.IRepository;
import repo.InMemoryRepository;
import service.*;

import java.util.Collection;
import java.util.List;

public class Test {
    @org.junit.Test
    public void Test__FindOneRepository__UniqueID__FoundInTheList() {
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        assert(repository.findOne(99827L).get().equals(droid_nr1));
    }


    /*@org.junit.Test(expected = IllegalArgumentException.class)
    public void Test__FindOneRepository__NullId__NotAddedToTheList() {
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        repository.save(droid_nr1);

        //droid_nr1's id is null
        assert(repository.findOne(droid_nr1.getId()).get().equals(droid_nr1));
    }*/


    @org.junit.Test
    public void Test__FindAllRepository__AddedToTheList__CorrectLengthOfRepository(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        Droid droid_nr2 = new Droid(12, 13, 13, "hu768", true);
        droid_nr2.setId(83662L);
        repository.save(droid_nr2);

        Iterable<Droid> allDroids = repository.findAll();
        assert(((Collection<?>) allDroids).size() == 2);
    }


    /*@org.junit.Test(expected = IllegalArgumentException.class)
    public void Test__FindAllRepository__Exception(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        Droid droid_nr2 = new Droid(12, 11, 10, "xshu87", false);
        droid_nr2.setId(83662L);
        repository.save(droid_nr2);

        Iterable<Droid> allDroids = repository.findAll();

        assert(((Collection<?>) allDroids).size() == 2);
    }*/


    @org.junit.Test
    public void Test__SaveEntityRepository__UniqueID_ValidEntity__AddedToTheList__ThrowsException() {
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        assert(((Collection<?>) repository.findAll()).size() == 1);
    }


    @org.junit.Test(expected = ValidatorException.class)
    public void Test__SaveEntityRepository__UniqueID_InvalidObject__NotAddedToTheList__ThrowsException(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        Droid droid_nr2 = new Droid(-10, 11, 10, "xshu87", false);
        droid_nr2.setId(6866L);

        repository.save(droid_nr2);
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void Test__SaveEntityRepository__NullID_InvalidObject__NotAddedToTheList__ThrowsException(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        Droid droid_nr2 = null;
        repository.save(droid_nr2);
    }

    @org.junit.Test
    public void Test__DeleteEntityRepository__ExistingID__RemovedFromTheList(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        repository.save(droid_nr1);

        repository.delete(99827L);
        assert(((Collection<?>) repository.findAll()).size() == 0);
    }

    /*@org.junit.Test (expected = IllegalArgumentException.class)
    public void Test__DeleteEntityRepository__NullId__ThrowsException(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);

        repository.delete(droid_nr1.getId());
    }*/


    @org.junit.Test
    public void Test__AddDroidService__UniqueID__AddedToRepository() throws ExistingDroidException {
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Service service = new Service(repository);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        service.addDroid(droid_nr1);

        assert(service.getDroids().size() == 1);
    }

    @org.junit.Test(expected = ExistingDroidException.class)
    public void Test__AddDroidService__ExistentID__NotAddedToRepository__ThrowsException(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        Service service = new Service(repository);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        service.addDroid(droid_nr1);

        Droid droid_nr2 = new Droid(11, 13, 14, "hu768", true);
        droid_nr2.setId(99827L);
        service.addDroid(droid_nr2);

    }


    @org.junit.Test
    public void Test__FilterDroidsService__CorrectLength(){
        IValidator<Droid> droidValidator = new DroidValidator();
        IRepository<Long, Droid> repository = new InMemoryRepository<>(droidValidator);
        //added this cool comm
        Service service = new Service(repository);
        Droid droid_nr1 = new Droid(12, 13, 14, "hu768", true);
        droid_nr1.setId(99827L);
        service.addDroid(droid_nr1);

        Droid droid_nr2 = new Droid(12, 13, 14, "c34wd", true);
        droid_nr2.setId(84663L);
        service.addDroid(droid_nr2);

        List<Droid> filteredDroids = service.filterDroidsByModel("hu768");
        assert(filteredDroids.size() == 1);
    }

    public void runTests(){
        Test__FilterDroidsService__CorrectLength();
        Test__AddDroidService__ExistentID__NotAddedToRepository__ThrowsException();
        Test__AddDroidService__UniqueID__AddedToRepository();
        //Test__DeleteEntityRepository__NullId__ThrowsException();
        Test__DeleteEntityRepository__ExistingID__RemovedFromTheList();
        Test__SaveEntityRepository__NullID_InvalidObject__NotAddedToTheList__ThrowsException();
        Test__SaveEntityRepository__UniqueID_InvalidObject__NotAddedToTheList__ThrowsException();
        Test__SaveEntityRepository__UniqueID_ValidEntity__AddedToTheList__ThrowsException();
        Test__FindAllRepository__AddedToTheList__CorrectLengthOfRepository();
        //Test__FindOneRepository__NullId__NotAddedToTheList();
        Test__FindOneRepository__UniqueID__FoundInTheList();
    }
}
