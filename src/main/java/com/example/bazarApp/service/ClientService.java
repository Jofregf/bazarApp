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
    public Client editClient(Client client) {
        return this.addClient(client);
    }

    @Override
    public Client editClient(Long id_client, Long new_id, String new_name, String new_lastname, String new_dni) {
        Client client = this.getClient(id_client);
        client.setId_client(new_id);
        client.setName(new_name);
        client.setLastname(new_lastname);
        client.setDni(new_dni);
        this.addClient(client);
        return client;
    }
}
