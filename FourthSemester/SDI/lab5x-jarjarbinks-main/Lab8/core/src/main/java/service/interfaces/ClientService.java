package service.interfaces;

import domain.baseEntities.Client;

import java.util.List;

public interface ClientService {
    String addClient(String name, String addr, String phoneNr);// returns message

    String deleteClient(Long id);

    String updateClient(Long id, String name, String addr, String phoneNr);

    List<Client> filterClientsByName(String name);

    List<Client> filterClientsById(Long id);

    List<Client> filterClientsByAddress(String addr);

    List<Client> getAllClients();
}
