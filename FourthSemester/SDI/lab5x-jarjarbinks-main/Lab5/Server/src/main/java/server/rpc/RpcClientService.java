package server.rpc;

import domain.baseEntities.Client;
import exceptions.ExistingException;
import exceptions.NotFoundException;
import rpc.ClientRpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RpcClientService implements ClientRpc {
    private final ExecutorService execSrv;
    private final service.ClientService srv;

    public RpcClientService(ExecutorService execSrv, service.ClientService srv) {
        this.execSrv = execSrv;
        this.srv = srv;
    }


    public CompletableFuture<String> addClient(String name, String addr, String phoneNr) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.addClient(new Client(name, addr, phoneNr));
                return "Client added successfully.";
            } catch (ExistingException | ClassNotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> deleteClient(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                srv.deleteClient(id);
                return "Client deleted successfully";
            } catch (NotFoundException e) {
                return e.getMessage();
            }
        }, execSrv);
    }

    public CompletableFuture<String> updateClient(Long id, String name, String addr, String phoneNr) {
        return CompletableFuture.supplyAsync(() -> {
            var cl = new Client(name, addr, phoneNr);
            cl.setId(id);
            srv.updateClient(cl);
            return "Client updated successfully";
        }, execSrv);
    }

    public CompletableFuture<String> filterClientsByName(String name) {
        return CompletableFuture.supplyAsync(() -> srv.filterClientsByName(name).toString(), execSrv);
    }

    public CompletableFuture<String> filterClientsById(Long id) {
        return CompletableFuture.supplyAsync(() -> srv.filterClientsById(id.toString()).toString(), execSrv);
    }

    public CompletableFuture<String> filterClientsByAddress(String addr) {
        return CompletableFuture.supplyAsync(() -> srv.filterClientsByAddress(addr).toString(), execSrv);
    }

    public CompletableFuture<String> getAllClients() {
//        return new CompletableFuture<String>();
          return CompletableFuture.supplyAsync(() -> srv.getClients().toString(), execSrv);
    }
}
