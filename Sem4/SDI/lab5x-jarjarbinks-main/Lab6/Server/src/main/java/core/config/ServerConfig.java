package core.config;

import domain.baseEntities.*;
import domain.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import repository.JPASpringRepo.*;
import service.*;
import utils.DbConfigs;

@Configuration
@ComponentScan({"repository", "service", "domain.baseEntities"})
public class ServerConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    RmiServiceExporter rmiClientServiceExporter() {
        var rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(ClientService.class);
        rmiServiceExporter.setService(context.getBean(ClientService.class));
        rmiServiceExporter.setServiceName("ClientService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiDroidServiceExporter() {
        var rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(DroidService.class);
        rmiServiceExporter.setService(context.getBean(DroidService.class));
        rmiServiceExporter.setServiceName("DroidService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiPurchaseServiceExporter() {
        var rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(PurchaseService.class);
        rmiServiceExporter.setService(context.getBean(PurchaseService.class));
        rmiServiceExporter.setServiceName("PurchaseService");
        return rmiServiceExporter;
    }

    @Bean
    RmiServiceExporter rmiReceiptServiceExporter() {
        var rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceInterface(ReceiptService.class);
        rmiServiceExporter.setService(context.getBean(ReceiptService.class));
        rmiServiceExporter.setServiceName("ReceiptService");
        return rmiServiceExporter;
    }

//    @Bean
//    IRepository droidRepo() {
//        return new IDroidRepo;
//    }
//
//    @Bean
//    Repository<Long, Client> clientRepo() {
//        return new ClientRepo(new ClientValidator());
//    }
//
//    @Bean
//    Repository<Long, Receipt> receiptRepo() {
//        return new ReceiptRepo(new ReceiptValidator());
//    }
//
//    @Bean
//    Repository<Long, Purchase> purchaseRepo() {
//        return new PurchaseRepo(new PurchaseValidator());
//    }

    @Bean
    Class<IDroidRepo> droidRepo(){
        return IDroidRepo.class;
    }
    @Bean
    Class<IClientRepo> clientRepo(){
        return IClientRepo.class;
    }
    @Bean
    Class<IPurchaseRepo> purchaseRepo(){
        return IPurchaseRepo.class;
    }
    @Bean
    Class<IReceiptRepo> receiptRepo(){
        return  IReceiptRepo.class;
    }



}
