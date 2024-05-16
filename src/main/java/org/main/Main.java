package org.main;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManagerFactory;
import org.main.config.HibernateConfig;
import org.main.config.ApplicationConfig;

import static org.main.Routes.Routes.*;


public class Main {


    public static void main(String[] args) {
        Main.startServer(7000);
    }

    public static void startServer(int port) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig
                .initiateServer()
                .startServer(port)
                .setExceptionHandling()
                .checkSecurityRoles()
                .setRoute(getSecurityRoutes())
                .setRoute(getUserRoutes(emf))
                .setRoute(getEventRoutes(emf))
                .setRoute(getRegistrationRoutes(emf));

    }
}
