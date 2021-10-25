import domain.adapters.db.ClientAdapter;
import domain.adapters.db.DroidAdapter;
import domain.adapters.db.PurchaseAdapter;
import domain.adapters.db.ReceiptAdapter;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import domain.baseEntities.Purchase;
import domain.baseEntities.Receipt;
import domain.validators.ClientValidator;
import domain.validators.DroidValidator;
import domain.validators.PurchaseValidator;
import domain.validators.ReceiptValidator;
import repository.DbRepository;
import rpc.*;
import server.TcpServer;
import server.rpc.RpcClientService;
import server.rpc.RpcDroidService;
import server.rpc.RpcPurchaseService;
import server.rpc.RpcReceiptService;
import service.ClientService;
import service.DroidService;
import service.PurchaseService;
import service.ReceiptService;
import utils.DbConfigs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {
//        var dbConf = new DbConfigs("jdbc:postgresql://tai.db.elephantsql.com:5432/nngzqljx", "nngzqljx", "xfOFBlofdjxoV3HnKxRDKLCyHcKQKhBd");
//
//        var droidRepo = new DbRepository<>(dbConf, Droid.class, new DroidAdapter(), new DroidValidator());
//        var clientRepo = new DbRepository<>(dbConf, Client.class, new ClientAdapter(), new ClientValidator());
//        var receiptRepo = new DbRepository<>(dbConf, Receipt.class, new ReceiptAdapter(), new ReceiptValidator());
//        var purchaseRepo = new DbRepository<>(dbConf, Purchase.class, new PurchaseAdapter(), new PurchaseValidator());
//
//        var droidSrv = new DroidService(droidRepo);
//        var clientSrv = new ClientService(clientRepo);
//        var receiptSrv = new ReceiptService(receiptRepo);
//        var purchaseSrv = new PurchaseService(purchaseRepo);
//
//        var execSrv = Executors.newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors()
//        );
//
//        var server = new TcpServer(execSrv, 3000);
//
//        var droidRpc = new RpcDroidService(execSrv, droidSrv);
//        var clientRpc = new RpcClientService(execSrv, clientSrv);
//        var receiptRpc = new RpcReceiptService(execSrv, receiptSrv);
//        var purchaseRpc = new RpcPurchaseService(execSrv, purchaseSrv);
//
//        server.addHandler(DroidRpc.ADD_DROID, (req) -> {
//            var request = req.getBody().split(",");
//            try {
//                var response = droidRpc.addDroid(Double.parseDouble(request[0]), Double.parseDouble(request[1]), Integer.parseInt(request[2]), request[3], Boolean.parseBoolean(request[4])).get();
//                return new Message(Message.OK, response);
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(DroidRpc.DELETE_DROID, (req) -> {
//            try {
//                var status = droidRpc.deleteDroid(Long.parseLong(req.getBody())).get();
//                return new Message(Message.OK, status);
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//
//
//            //return null;
//        });
//        server.addHandler(DroidRpc.UPDATE_DROID, (req) -> {
//            var request = req.getBody().split(",");//id powerUsage price batteryTime model driver
//            try {
//                var response = droidRpc.updateDroid(Long.parseLong(request[0]), Double.parseDouble(request[1]), Double.parseDouble(request[2]), Integer.parseInt(request[3]), request[4], Boolean.parseBoolean(request[5])).get();
//                return new Message(Message.OK, response);
//
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(DroidRpc.GET_ALL_DROIDS, (req) -> {
//
//            try {
//                return new Message(Message.OK, droidRpc.getAllDroids().get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(DroidRpc.GET_DROIDS_BY_ID, (req) -> {
//
//            try {
//                return new Message(Message.OK, droidRpc.getDroidsById(Long.parseLong(req.getBody())).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(DroidRpc.GET_DROIDS_BY_FILTER, (req) -> {
//
//            try {
//                return new Message(Message.OK, droidRpc.getDroidsByFilter(Integer.parseInt(req.getBody())).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(DroidRpc.GET_DROIDS_BY_MODEL, (req) -> {
//
//            try {
//                return new Message(Message.OK, droidRpc.getDroidsByModel(req.getBody()).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.ADD_CLIENT, (req) -> {
//
//            var request = req.getBody().split(",");
//            try {//name address phoneNumber
//                var response = clientRpc.addClient(request[0], request[1], request[2]).get();
//                return new Message(Message.OK, response);
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.DELETE_CLIENT, (req) -> {
//            try {
//                return new Message(Message.OK, clientRpc.deleteClient(Long.parseLong(req.getBody())).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.UPDATE_CLIENT, (req) -> {
//            var request = req.getBody().split(",");
//            try {
//                return new Message(Message.OK, clientRpc.updateClient(Long.parseLong(request[0]), request[1], request[2], request[3]).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.GET_CLIENTS, (req) -> {
//            try {
//                return new Message(Message.OK, clientRpc.getAllClients().get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.FILTER_CLIENT_BY_ID, (req) -> {
//            try {
//                return new Message(Message.OK, clientRpc.filterClientsById(Long.parseLong(req.getBody())).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.FILTER_CLIENTS_BY_NAME, (req) -> {
//            try {
//                return new Message(Message.OK, clientRpc.filterClientsByName(req.getBody()).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ClientRpc.FILTER_CLIENTS_BY_ADDRESS, (req) -> {
//            try {
//                return new Message(Message.OK, clientRpc.filterClientsByAddress(req.getBody()).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(PurchaseRpc.ADD_PURCHASE, (req) -> {
//            var request = req.getBody().split(",");
//            try {
//                var response = purchaseRpc.addPurchase(Long.parseLong(request[0]), Long.parseLong(request[1]), Double.parseDouble(request[2])).get();
//                return new Message(Message.OK, response);
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(PurchaseRpc.DELETE_PURCHASE, (req) -> {
//            try {
//                return new Message(Message.OK, purchaseRpc.deletePurchase(Long.parseLong(req.getBody())).get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(PurchaseRpc.GET_ALL_PURCHASES, (req) -> {
//            try {
//                return new Message(Message.OK, purchaseRpc.getAllPurchases().get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ReceiptRpc.ADD_RECEIPT, (req) -> {
//            var request = req.getBody().split(",");
//            try {
//                var response = receiptRpc.addReceipt(Long.parseLong(request[0]), Double.parseDouble(request[1])).get();
//                return new Message(Message.OK, response);
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });
//        server.addHandler(ReceiptRpc.GET_ALL_RECEIPTS, (req) -> {
//            try {
//                return new Message(Message.OK, receiptRpc.getAllReceipts().get());
//            } catch (InterruptedException | ExecutionException e) {
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        });

        //server.start();
    }
}
