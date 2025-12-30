package com.example.clientservice;

import com.example.clientservice.entities.Client;
import com.example.clientservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

    @Bean
    CommandLineRunner initialiserBaseH2(ClientRepository clientRepository) {
        return args -> {
            // Vérifier si la base de données est déjà peuplée
            if (clientRepository.count() == 0) {
                clientRepository.save(new Client(null, "Amine SAFI", 23f));
                clientRepository.save(new Client(null, "Amal ALAOUI", 22f));
                clientRepository.save(new Client(null, "Samir RAMI", 22f));
            } 
        };
    }
}
