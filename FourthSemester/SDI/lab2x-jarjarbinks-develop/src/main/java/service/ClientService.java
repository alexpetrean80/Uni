package service;

import domain.baseEntities.Client;
import exceptions.*;
import repo.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService {
    IRepository<Long, Client> repository;

    /**
     * @param repository
     */
    public ClientService(IRepository<Long, Client> repository) {
        this.repository = repository;
    }


    public void addClient(Client newClient) throws ExistingClientException {
        var result = repository.save(newClient);
        result.ifPresent(client -> {
            throw new ExistingClientException("client exists");
        });
    }

    /**
     * @return List<Client>
     * returns all clients in the repo
     */
    public List<Client> getClients() {
        var clients = new ArrayList<Client>();
        repository.findAll().forEach(clients::add);
        return clients;
    }

    /**
     *
     * @param id - the id to find the client by
     * @throws InexistentClientException - if the client doesn't exists an exception will be thrown
     */
    public void deleteClient(Long id) throws InexistentClientException {
        var result = repository.delete(id);
        result.ifPresentOrElse(client -> {
        }, () -> {
            throw new InexistentClientException("Client does not exist.");
        });

    }


    /**
     *
     * @param msg - model type we are filtering by
     * @return List<Client> - the filtered list of droids
     */
    public List<Client> filterClientsByName(String msg) {
        var clients = new ArrayList<Client>();
        repository.findAll().forEach(clients::add);
        return clients.stream()
                .filter(s -> s.getName().contains(msg))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param msg - the message to filter by
     * @return - the list of clients that have that name
     */
    public List<Client> filterClientsByAddress(String msg){
        var clients = new ArrayList<Client>();
        this.repository.findAll().forEach(clients::add);
        return clients.stream()
                .filter(c -> c.getAddress().contains(msg))
                .collect(Collectors.toList());
    }

    /**
     *
     * @param newClient - the new client that will replace the old
     */
    public void updateClient(Client newClient) {
        var result = this.repository.update(newClient);
        result.ifPresent(c -> {
            throw new DealershipException("update failed");
        });
    }

    /**
     *
     * @param id - id of the client
     * @return - the list of clients with the given id
     */
    public List<Client> filterClientsById(String id) {
        var clients = new ArrayList<Client>();
        repository.findAll().forEach(clients::add);
        return clients.stream()
                .filter(s -> s.getId().toString().contains(id))
                .collect(Collectors.toList());
    }
}
