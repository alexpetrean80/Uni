import client.TcpClient;

import java.util.concurrent.Executors;

import client.service.ClientRpcService;
import client.service.DroidRpcService;
import client.service.PurchaseRpcService;
import client.service.ReceiptRpcService;
import ui.View;

public class ClientApp {
    public static void main(String[] args) {
        var execSrv = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        var client = new TcpClient(execSrv);

        System.out.println("hello");

        ClientRpcService cli=new ClientRpcService(execSrv,client);
        DroidRpcService dro=new DroidRpcService(execSrv,client);
        PurchaseRpcService pur=new PurchaseRpcService(execSrv,client);
        ReceiptRpcService rec=new ReceiptRpcService(execSrv,client);

        View console = new View(dro,cli,pur,rec);
        console.runView();

        System.out.println("bye");
        execSrv.shutdown();
    }
}
