import controller.*;
import repository.*;
import view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IRepository repo = new Repository();
        IController ctrl = new Controller(repo);
        IView view = new View(ctrl, new Scanner(System.in));

        view.run();
    }
}
