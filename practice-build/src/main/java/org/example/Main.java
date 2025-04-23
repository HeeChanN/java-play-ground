package org.example;


import com.google.gson.Gson;
import org.example.greeting.GreetingService;
import org.example.greeting.factory.GreetingServiceFactory;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        GreetingService service = GreetingServiceFactory.getPreferredGreetingService();
        String json = "{\"name\": \"Alice\", \"age\": 25}";

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        service.greet();

        ServiceLoader<GreetingService> loader = ServiceLoader.load(GreetingService.class);
        for (GreetingService service1 : loader) {
            service1.greet();
        }
    }
}