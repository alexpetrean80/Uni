package core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.*;
import ui.View;

@Configuration
@ComponentScan("java")
public class ClientConfig {
    @Bean
    RmiProxyFactoryBean rmiProxyFactoryDroidBean() {
        RmiProxyFactoryBean rmiProxyFactoryDroidBean = new RmiProxyFactoryBean();
        rmiProxyFactoryDroidBean.setServiceUrl("rmi://localhost:1099/DroidService");
        rmiProxyFactoryDroidBean.setServiceInterface(DroidService.class);
        return rmiProxyFactoryDroidBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryClientBean() {
        RmiProxyFactoryBean rmiProxyFactoryClientBean = new RmiProxyFactoryBean();
        rmiProxyFactoryClientBean.setServiceUrl("rmi://localhost:1099/ClientService");
        rmiProxyFactoryClientBean.setServiceInterface(ClientService.class);
        return rmiProxyFactoryClientBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryPurchaseBean() {
        RmiProxyFactoryBean rmiProxyFactoryPurchaseBean = new RmiProxyFactoryBean();
        rmiProxyFactoryPurchaseBean.setServiceUrl("rmi://localhost:1099/PurchaseService");
        rmiProxyFactoryPurchaseBean.setServiceInterface(PurchaseService.class);
        return rmiProxyFactoryPurchaseBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryReceiptBean() {
        RmiProxyFactoryBean rmiProxyFactoryReceiptBean = new RmiProxyFactoryBean();
        rmiProxyFactoryReceiptBean.setServiceUrl("rmi://localhost:1099/ReceiptService");
        rmiProxyFactoryReceiptBean.setServiceInterface(ReceiptService.class);
        return rmiProxyFactoryReceiptBean;
    }

    @Bean
    View view() {
        return new View(droidServiceCli(), clientServiceCli(), purchaseServiceCli(), receiptServiceCli());
    }

    @Bean
    ClientService clientServiceCli() {
        return new ClientServiceCli();
    }

    @Bean
    DroidService droidServiceCli() {
        return new DroidServiceCli();
    }

    @Bean
    PurchaseService purchaseServiceCli() {
        return new PurchaseServiceCli();
    }

    @Bean
    ReceiptService receiptServiceCli() {
        return new ReceiptServiceCli();
    }


}
