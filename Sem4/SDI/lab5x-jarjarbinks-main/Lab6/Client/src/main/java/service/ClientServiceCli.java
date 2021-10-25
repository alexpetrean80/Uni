package service;

import org.springframework.beans.factory.annotation.Autowired;
import domain.baseEntities.Client;

import java.util.List;

public class ClientServiceCli implements ClientService{
    @Autowired
    ClientService clientService;

    @Override
    public String addClient(String name, String addr, String phoneNr){return clientService.addClient(name,addr,phoneNr);}

    @Override
    public String deleteClient(Long id){return clientService.deleteClient(id);}

    @Override
    public String updateClient(Long id, String name, String addr, String phoneNr){return clientService.updateClient(id,name,addr,phoneNr);}

    @Override
    public List<Client> filterClientsByName(String name){return clientService.filterClientsByName(name);}

    @Override
    public List<Client> filterClientsById(Long id){return clientService.filterClientsById(id);}

    @Override
    public List<Client> filterClientsByAddress(String addr){return clientService.filterClientsByAddress(addr);}

    @Override
    public List<Client> getAllClients(){return clientService.getAllClients();}
}