package com.example.bazarApp.controller;

import com.example.bazarApp.model.Client;
import com.example.bazarApp.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/clientes/crear")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public List<Client> clientsList() {
        return clientService.clientList();
    }

    @GetMapping("/clientes/{id_client}")
    public ResponseEntity<Client> showClient(@PathVariable Long id_client) {
        Client client = clientService.getClient(id_client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/clientes/eliminar/{id_client}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id_client) {
        clientService.deleteClient(id_client);
        return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/clientes/editar/{id_client}")
    public ResponseEntity<Client> editClient(@PathVariable Long id_client,
                                             @RequestBody Client client) {
        clientService.editClient(id_client, client);
        Client editedClient = this.showClient(id_client).getBody();
        return new ResponseEntity<>(editedClient, HttpStatus.OK);
    }

}
