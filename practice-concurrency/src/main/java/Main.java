import ex1.ExampleService;

public class Main {

    private static final ExampleService exampleService = new ExampleService();


    public static void main(String[] args) {
        exampleService.displayThreadName();
        System.out.println("main thread");
    }
}