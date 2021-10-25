package client.service;

import client.TcpClient;
import domain.baseEntities.Client;
import rpc.ClientRpc;
import rpc.Message;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientRpcService implements ClientRpc {
    private final ExecutorService execsrv;
    private final TcpClient tcli;

    public ClientRpcService(ExecutorService execsrv, TcpClient tcli) {
        this.execsrv = execsrv;
        this.tcli = tcli;
    }

    @Override
    public CompletableFuture<String> addClient(String name, String addr, String phoneNr) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.ADD_CLIENT, name + "," + addr + "," + phoneNr);
            return tcli.sendAndReceive(req).getBody();
        }, execsrv);
    }

    @Override
    public CompletableFuture<String> deleteClient(Long id) {
        return CompletableFuture.supplyAsync(()->{
            var req = new Message(ClientRpc.DELETE_CLIENT,id.toString());
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> updateClient(Long id, String name, String addr, String phoneNr) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.UPDATE_CLIENT,id.toString()+","+name+","+addr+","+phoneNr);
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> filterClientsByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.FILTER_CLIENTS_BY_NAME, name);
            return tcli.sendAndReceive(req).getBody();
            }, execsrv);
    }

    @Override
    public CompletableFuture<String> filterClientsById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.FILTER_CLIENT_BY_ID,id.toString());
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> filterClientsByAddress(String addr) {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.FILTER_CLIENTS_BY_ADDRESS,addr);
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }

    @Override
    public CompletableFuture<String> getAllClients() {
        return CompletableFuture.supplyAsync(() -> {
            var req = new Message(ClientRpc.GET_CLIENTS,"");
            return tcli.sendAndReceive(req).getBody();
        },execsrv);
    }
}
