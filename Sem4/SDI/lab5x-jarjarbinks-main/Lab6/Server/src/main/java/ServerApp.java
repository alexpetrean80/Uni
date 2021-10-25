import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {
    public static void main(String[] args) {
        System.out.println("Server started...");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("config");
    }
}
