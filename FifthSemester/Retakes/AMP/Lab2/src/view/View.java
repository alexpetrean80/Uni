package view;

import controller.IController;
import model.VehicleType;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class View implements IView {
    private final IController ctrl;
    private final Scanner scanner;

    public View(IController ctrl, Scanner scanner) {
        this.ctrl = ctrl;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (true) {
            printGeneralMenu();
            try {
                switch (getUserOption()) {
                    case ADD_VEHICLE -> addVehicle();
                    case REMOVE_VEHICLE -> deleteVehicle();
                    case SEE_RED_VEHICLES -> getRedVehicles();
                    case QUIT -> {
                        System.out.println("Goodbye!");
                        System.exit(0);
                    }
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void printGeneralMenu() {
        System.out.flush();
        System.out.println("Welcome to the parking lot!\n\n");
        System.out.println("1. Park a vehicle.");
        System.out.println("2. Retrieve a vehicle from the parking.");
        System.out.println("3. See all red vehicles(\"Special feature because we are tifosi.\").");
        System.out.println("\n0. Quit.");
    }

    private void addVehicle() {
        VehicleType type = null;
        String colour = "";
        String model = "";
        String brand = "";
        while (type == null ||
                Objects.equals(colour, "") ||
                Objects.equals(model, "") ||
                Objects.equals(brand, "")) {
            System.out.printf("Vehicle type: %s\n", type != null ? type.toString() : "");
            System.out.printf("Vehicle brand: %s\n", brand);
            System.out.printf("Vehicle model: %s\n", model);
            System.out.printf("Vehicle colour: %s\n", colour);

            printAddVehicleMenu();
            switch (getAddVehicleUserOption()) {
                case SET_BRAND -> brand = scanner.next();
                case SET_COLOUR -> colour = scanner.next();
                case SET_MODEL -> model = scanner.next();
                case SET_TYPE -> {
                    printTypes();
                    try {
                        type = getType();
                    } catch (RuntimeException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case BACK -> {
                    return;
                }
            }
        }

        try {
            ctrl.addVehicle(type, colour, brand, model);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printTypes() {
        System.out.println("Types of vehicles:");
        System.out.println("1. Car.");
        System.out.println("2. Bicycle.");
        System.out.println("3. Motorcycle.");
    }

    private VehicleType getType() {
        return switch (scanner.nextInt()) {
            case 1 -> VehicleType.CAR;
            case 2 -> VehicleType.BICYCLE;
            case 3 -> VehicleType.MOTORCYCLE;
            default -> throw new RuntimeException("Invalid vehicle type.");
        };
    }

    private static void printAddVehicleMenu() {
        System.out.println("Add vehicle menu\n\n");
        System.out.println("1. Set the type of the vehicle.");
        System.out.println("2. Set the colour of the vehicle.");
        System.out.println("3. Set the model of the vehicle.");
        System.out.println("4. Set the brand of the vehicle.");
        System.out.println("\n0. Back.");
    }

    private AddVehicleMenuOption getAddVehicleUserOption() {
        return switch (scanner.nextInt()) {
            case 1 -> AddVehicleMenuOption.SET_TYPE;
            case 2 -> AddVehicleMenuOption.SET_COLOUR;
            case 3 -> AddVehicleMenuOption.SET_MODEL;
            case 4 -> AddVehicleMenuOption.SET_BRAND;
            case 0 -> AddVehicleMenuOption.BACK;
            default -> throw new RuntimeException("Invalid option.");
        };
    }

    private void deleteVehicle() {
        try {
            ctrl.deleteVehicle(scanner.nextInt());
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getRedVehicles() {
        Arrays.stream(ctrl.getAllRedVehicles())
                .forEach(v -> System.out.println(v.toString()));
    }


    private MenuOption getUserOption() {
        return switch (scanner.nextInt()) {
            case 1 -> MenuOption.ADD_VEHICLE;
            case 2 -> MenuOption.REMOVE_VEHICLE;
            case 3 -> MenuOption.SEE_RED_VEHICLES;
            case 0 -> MenuOption.QUIT;
            default -> throw new RuntimeException("Invalid option.");
        };
    }
}
