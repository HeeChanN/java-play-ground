package org.example.greeting;

public class EnglishGreeting implements GreetingService {

    @Override
    public void greet() {
        System.out.println("Hello!");
    }
}
