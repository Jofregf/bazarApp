package com.example.bazarApp.service;

import com.example.bazarApp.model.Client;

import java.util.List;

public interface IClientService {

    public Client addClient(Client client);

    public List<Client> clientList();

    public Client getClient(Long id_client);

    public void deleteClient(Long id_client);

    public Client editClient(Client client);

    public Client editClient(Long id_client, Long new_id, String new_name, String new_lastname, String new_dni);
}
