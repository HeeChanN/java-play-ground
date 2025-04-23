package org.example.greeting.factory;


import org.example.greeting.EnglishGreeting;
import org.example.greeting.GreetingService;

import java.util.ServiceLoader;

public class GreetingServiceFactory {

    public static GreetingService getPreferredGreetingService() {
        ServiceLoader<GreetingService> loader = ServiceLoader.load(GreetingService.class);

        for (GreetingService service : loader) {
            if (service instanceof EnglishGreeting) {
                return service;
            }
        }

        throw new IllegalStateException("KoreanGreeting implementation not found.");
    }
}
