package com.example.bazarApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_client;
    private String name;
    private String lastname;
    private String dni;

    public Client() {
    }

    public Client(Long id_client, String name, String lastname, String dni) {
        this.id_client = id_client;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
    }

}
