package com.example.bazarApp.service;

import com.example.bazarApp.model.Client;

import java.util.List;

public interface IClientService {

    public Client addClient(Client client);

    public List<Client> clientList();

    public Client getClient(Long id_client);

    public void deleteClient(Long id_client);

    public Client editClient(Long id_client, Client client);
}
