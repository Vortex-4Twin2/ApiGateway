package tn.esprit.twin.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(
                        "Produits", r -> r.path("/products/**").uri("lb://MicroserviceProduits"))
                .route("livraison",r -> r.path("/livraisons/**").uri("lb://MicroService-Livraison"))
                .route("Panier",r->r.path("/paniers/**").uri("lb://Panier"))
                .route("offre",r->r.path("/offres/**").uri("lb://MicroServiceDouaa"))
                .route("user", r -> r.path("/users/**")
                        .uri("lb://UserMS"))
                .route("auth", r -> r.path("/auth/**")
                        .uri("lb://UserMS"))
                .route("commande", r->r.path("/api/commandes/**").uri("lb://Commande"))
                .route("avis", r -> r.path("/avis/**")
                        .uri("lb://AVIS-RECLAMATION-SERVICE"))
                .build();
    }

}
