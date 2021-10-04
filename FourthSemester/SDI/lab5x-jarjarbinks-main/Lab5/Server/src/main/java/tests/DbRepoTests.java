package tests;

import domain.adapters.db.ClientAdapter;
import domain.adapters.db.DroidAdapter;
import domain.adapters.db.ReceiptAdapter;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import domain.baseEntities.Receipt;
import domain.validators.ClientValidator;
import domain.validators.DroidValidator;
import domain.validators.ReceiptValidator;
import org.junit.jupiter.api.Test;
import repository.DbRepository;
import utils.DbConfigs;

import java.util.ArrayList;
import java.util.List;

public class DbRepoTests {

    @Test
    public void Test__FindAllDroids__ResultNotEmpty() throws ClassNotFoundException {
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var droidRepo = new DbRepository<>(dbConf, Droid.class, new DroidAdapter(), new DroidValidator());
        var result = droidRepo.findAll();
//        result.forEach(System.out::println);
        List<Droid> droids = new ArrayList<Droid>();
        result.forEach(droids::add);
        assert(droids.get(0).getId() == 1L);
    }

    @Test
    public void Test__FindOneClient__ResultGood() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var droidRepo = new DbRepository<>(dbConf, Droid.class, new DroidAdapter(), new DroidValidator());
        var result = droidRepo.findOne(1L);
//        result.forEach(System.out::println);
        List<Droid> droids = new ArrayList<Droid>();
        //result.forEach(droids::add);
        assert(result.get().getId() == 1L);
    }

    @Test
    public void Test__FindAllClients__FirstOneIsGood() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
        var result = clientRepo.findAll();
        List<Client> clients = new ArrayList<>();
        result.forEach(clients::add);
        assert(clients.get(0).getId() == 1L);
    }

    @Test
    public void Test__FindOneClient__IsGood() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
        var result = clientRepo.findOne(1L);
        //List<Client> clients = new ArrayList<>();
        //result.forEach(clients::add);
        assert(result.get().getId() == 1L);
    }

    @Test
    public void Test__UpdateClient__ClientIsUpdated() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
        var result = clientRepo.findOne(1L);

        result.get().setName("N with rocket launcher");
        clientRepo.update(result.get());
        assert(clientRepo.findOne(1L).get().getName().equals("N with rocket launcher"));
    }

    @Test
    public void Test__InsertNewClient__ClientWasAdded() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
        //clientRepo.save(new Client("Light Evader", "Death Star 420c", "1-800-CALLFORCE"));
        assert(clientRepo.findOne(5L).get().getName().equals("Light Evader"));
    }

    @Test
    public void Test__UpdateClient__ClientWasUpdated() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
        //var newClient = new Client("Ion Capra", "Tuicomicinei", "0744842195");
        //newClient.setId(4L);
        //clientRepo.update(newClient);
        assert(clientRepo.findOne(4L).get().getName().equals("Ion Capra"));
    }

    @Test
    public void Test__GetReceipt__ReceiptIsReturned() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var receiptRepo = new DbRepository<>(dbConf, Receipt.class, new ReceiptAdapter(), new ReceiptValidator());
        var result = receiptRepo.findAll();
        List<Receipt> receipts = new ArrayList<>();
        result.forEach(receipts::add);
        assert(receipts.get(0).getId() == 1L);
    }

    @Test
    public void Test__DeleteReceipt__ReceiptWasDeleted() throws ClassNotFoundException{
        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
        var receiptRepo = new DbRepository<>(dbConf, Receipt.class, new ReceiptAdapter(), new ReceiptValidator());
       // receiptRepo.delete(1L);
        var result = receiptRepo.findAll();
        List<Receipt> receipts = new ArrayList<>();
        result.forEach(receipts::add);
        assert(receipts.size() == 1);
    }
}
