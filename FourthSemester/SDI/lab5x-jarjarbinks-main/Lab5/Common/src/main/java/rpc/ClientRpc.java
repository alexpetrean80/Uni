package rpc;

import domain.baseEntities.Client;

import java.util.concurrent.CompletableFuture;

public interface ClientRpc {
    int PORT = 3000;
    String HOST = "localhost";

    String ADD_CLIENT = "addClient";
    String GET_CLIENTS = "getClients";
    String DELETE_CLIENT = "deleteClient";
    String FILTER_CLIENTS_BY_NAME = "filterClientsByName";
    String FILTER_CLIENTS_BY_ADDRESS = "filterClientsByAddress";
    String UPDATE_CLIENT = "updateClient";
    String FILTER_CLIENT_BY_ID = "filterClientById";

    CompletableFuture<String> addClient(String name, String addr, String phoneNr);

    CompletableFuture<String> deleteClient(Long id);

    CompletableFuture<String> updateClient(Long id, String name, String addr, String phoneNr);

    CompletableFuture<String> filterClientsByName(String name);

    CompletableFuture<String> filterClientsById(Long id);

    CompletableFuture<String> filterClientsByAddress(String addr);

    CompletableFuture<String> getAllClients();
}
