import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.View;

public class ClientApp {
    public static void main(String[] args) {

        System.out.println("hello");

        AnnotationConfigApplicationContext appctxt = new AnnotationConfigApplicationContext("config");

        View console = appctxt.getBean(View.class);
        console.runView();

        System.out.println("bye");
    }
}
