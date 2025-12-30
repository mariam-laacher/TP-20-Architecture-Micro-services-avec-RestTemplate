package com.example.car;

import com.example.car.entities.Car;
import com.example.car.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  // Active l'enregistrement auprès d'Eureka
public class CarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

    /**
     * Configure RestTemplate pour la communication inter-services
     * @return Instance configurée de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Configuration des timeouts
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);  // 5 secondes pour la connexion
        requestFactory.setReadTimeout(5000);     // 5 secondes pour la lecture

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    @Bean
    CommandLineRunner initializeDatabase(CarRepository carRepository) {
        return args -> {
            // Vérifier si la base de données est déjà peuplée
            if (carRepository.count() == 0) {
                // Création des voitures
                carRepository.save(new Car(null, "Ford", "2022", "12 A 2345", 13L));
                carRepository.save(new Car(null, "Renaut", "2000", "14 R 5245", 14L));
                carRepository.save(new Car(null, "Toyota", "1990", "34 T 6755", 15L));
                carRepository.save(new Car(null, "Ford", "2021", "44 R 6756", 14L));
            }
        };
    }

}

