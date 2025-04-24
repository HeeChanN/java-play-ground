package ex1;

public class ExampleService {

    public void displayThreadName(){
        Runnable r = new ExampleTask();
        Thread t = new Thread(r);
        t.start();
    }
}
