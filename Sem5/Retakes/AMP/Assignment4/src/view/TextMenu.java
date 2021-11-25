package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {

    private final Map<String, Command> commandsMap;

    public TextMenu() {
        commandsMap = new HashMap<>();
    }

    public void addCommand(Command command) {
        commandsMap.put(command.getKey(), command);
    }

    private void printMenu() {
        for (Command command : commandsMap.values()) {
            String line = String.format("%4s:%s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Input the option");
            String key = scanner.nextLine();
            Command command = commandsMap.get(key);
            if (command == null) {
                System.out.println("Invalid option!");
                continue;
            }
            command.execute();
        }
    }
}
