import controller.*;
import model.Car;
import model.Vehicle;
import model.VehicleType;
import repository.*;
import view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IRepository repo = new Repository();
        IController ctrl = new Controller(repo);
        ctrl.addVehicle(VehicleType.BICYCLE, "white", "orbea", "carpe");
        ctrl.addVehicle(VehicleType.BICYCLE, "red", "orbea", "vector");
        ctrl.addVehicle(VehicleType.BICYCLE, "red", "elops", "speed");
        ctrl.addVehicle(VehicleType.BICYCLE, "green", "umf", "hardy");
        ctrl.addVehicle(VehicleType.CAR, "red", "mazda", "mx5");
        ctrl.addVehicle(VehicleType.CAR, "red", "ferrari", "f40");
        ctrl.addVehicle(VehicleType.CAR, "black", "bmw", "3series");
        ctrl.addVehicle(VehicleType.CAR, "white", "mitsubishi", "lancer");
        ctrl.addVehicle(VehicleType.MOTORCYCLE, "red", "ducati", "panigale");
        ctrl.addVehicle(VehicleType.BICYCLE, "green", "kawasaki", "ninja");
        ctrl.addVehicle(VehicleType.BICYCLE, "orange", "honda", "cbr1000");
        IView view = new View(ctrl, new Scanner(System.in));

        view.run();
    }
}
