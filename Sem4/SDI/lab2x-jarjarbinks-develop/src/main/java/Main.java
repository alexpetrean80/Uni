import domain.baseEntities.Client;
import domain.baseEntities.Purchase;
import domain.baseEntities.Receipt;
import domain.validators.*;
import service.ClientService;
import service.PurchaseService;
import service.ReceiptService;
import service.Service;
import domain.baseEntities.Droid;
import repo.IRepository;
import repo.InMemoryRepository;
import view.View;

public class Main {
    public static void main(String[] args) {

        //in-memory repo
        IValidator<Droid> droidValidator = new DroidValidator();
        IValidator<Client> clientIValidator = new ClientValidator();
        IValidator<Receipt> receiptIValidator = new ReceiptValidator();
        IValidator<Purchase> purcheaseIValidator = new PurchaseValidator();
        IRepository<Long, Droid> droidRepository = new InMemoryRepository<>(droidValidator);
        IRepository<Long, Client> clientRepository = new InMemoryRepository<>(clientIValidator);
        IRepository<Long, Receipt> receiptRepository = new InMemoryRepository<>(receiptIValidator);
        IRepository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purcheaseIValidator);

        Service droidService = new Service(droidRepository);
        ClientService cs = new ClientService(clientRepository);
        ReceiptService rs = new ReceiptService(receiptRepository);
        PurchaseService ps = new PurchaseService(purchaseRepository);
        //View console = new View(droidService);
        View console = new View(droidService,cs,ps,rs);
        console.runView();//this is a comment for the attendance

    }
}
