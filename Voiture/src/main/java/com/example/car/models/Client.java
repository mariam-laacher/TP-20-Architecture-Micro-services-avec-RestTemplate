package com.example.car.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long id;

    private String nom;  // Correspond au champ "nom" du service client

    private Float age;   // Correspond au type Float du service client

}

