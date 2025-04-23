package org.example.greeting;

public class KoreanGreeting implements GreetingService{

    @Override
    public void greet() {
        System.out.println("안녕하세요!");
    }
}
