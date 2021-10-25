package service;

import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.JPASpringRepo.IClientRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.lang.Math.max;

@Service
public class ClientServiceSrv implements ClientService {


    @Autowired
    private IClientRepo repository;

    @Override
    public String addClient(String name, String addr, String phoneNr) {
        var newClient = new Client(name, addr,phoneNr);
        long id = 0;
        for(Client d : this.repository.findAll())
            id = max(id, d.getId() + 1);
        newClient.setId(id);
        repository.save(newClient);
        AtomicReference<String> resMsg = new AtomicReference<>("Client saved successfully.");
        return resMsg.get();
    }

    @Override
    public String deleteClient(Long id) {
        repository.findById(id)
                .ifPresentOrElse((client) -> repository.deleteById(client.getId()),
                        () -> {
                            throw new NotFoundException("Client does not exist!");
                        }
                );
        AtomicReference<String> resMsg = new AtomicReference<>("Client deleted successfully.");
        return resMsg.get();
    }

    @Override
    public String updateClient(Long id, String name, String addr, String phoneNr) {

        AtomicReference<String> resMsg = new AtomicReference<>("Client updated successfully.");
        repository.findById(id)
                .ifPresentOrElse((client) -> {
                            client.setName(name);
                            client.setAddress(addr);
                            client.setPhoneNumber(phoneNr);
                        },
                        ()->{
                            throw new NotFoundException("Client does not exist");
                        }
                );
        return resMsg.get();

    }

    @Override
    public List<Client> filterClientsByName(String name) {
        var clients = new ArrayList<Client>();
        try {
            repository.findAll().forEach(clients::add);
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return clients.stream()
                .filter(s -> s.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsById(Long id) {
        var clients = new ArrayList<Client>();
        try {
            clients.addAll(this.repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return clients.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByAddress(String addr) {
        var clients = new ArrayList<Client>();
        try {
            this.repository.findAll().forEach(clients::add);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return clients.stream()
                .filter(c -> c.getAddress().contains(addr))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllClients() {
        var clients = new ArrayList<Client>();
        try {
            clients.addAll(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return clients;
    }
}
