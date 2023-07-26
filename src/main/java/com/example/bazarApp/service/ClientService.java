package com.example.bazarApp.service;

import com.example.bazarApp.model.Client;
import com.example.bazarApp.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;
    @Override
    public Client addClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient;
    }

    @Override
    public List<Client> clientList() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id_client) {
        Client clientById = clientRepository.findById(id_client)
                .orElseThrow(() -> new NoSuchElementException("No se encuentra el cliente con id " + id_client));
        return clientById;
    }

    @Override
    public void deleteClient(Long id_client) {
        Client client = this.getClient(id_client);
        clientRepository.delete(client);
    }

    @Override
    public Client editClient(Long id_client, Client client) {
        Client clientToEdit = this.getClient(id_client);
        System.out.println(clientToEdit);
        clientToEdit.setId_client(id_client);
        clientToEdit.setName(client.getName());
        clientToEdit.setLastname(client.getLastname());
        clientToEdit.setDni(client.getDni());

        this.addClient(clientToEdit);
        System.out.println(clientToEdit);
        return clientToEdit;
    }
}
