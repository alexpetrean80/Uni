package ui;

import exceptions.*;
import org.springframework.stereotype.Component;
import service.interfaces.ClientService;
import service.interfaces.DroidService;
import service.interfaces.PurchaseService;
import service.interfaces.ReceiptService;
import ui.controllers.ClientController;
import ui.controllers.DroidController;
import ui.controllers.PurchaseController;
import ui.controllers.ReceiptController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

@Component
public class View {

    private final DroidController ctrl;
    private final ClientController cs;
    private final PurchaseController ps;
    private final ReceiptController rs;



    /**
     *
     * @param dr {@code Controller}
     * @param rs receipt service
     */
    public View(DroidController dr, ClientController cs, PurchaseController ps, ReceiptController rs) {
        this.cs = cs;
        this.ctrl = dr;
        this.rs = rs;
        this.ps = ps;
    }


    /**
     * Prints out the user menu
     */
    public void menu() {
        System.out.println("   Choose one: ");
        System.out.println("1. Add Menu.");
        System.out.println("2. Update Menu.");
        System.out.println("3. Delete Menu.");
        System.out.println("4. Filter Menu.");
        System.out.println("5. Print Menu.");
        System.out.println("0. Exit");
    }

    /**
     * the add choices for the entities
     */
    private void addsMenu(){
        System.out.println("1. Add a Droid.");
        System.out.println("2. Add a Client.");
        System.out.println("3. Add a Purchase.");
        System.out.println("4. Add a Receipt.");
        System.out.println("0. Back to Main Menu.");
    }


    /**
     * calls the add functions based on the users choice
     */
    private void runAddMenu(){
        Scanner keyboard = new Scanner(System.in);
        this.addsMenu();
        int command;
        System.out.println("Insert choice:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> addADroid();

            case (2) -> addAClient();

            case (3) -> addAPurchase();

            case (4) -> addAReceipt();

            default -> runAddMenu();
        }
        runAddMenu();
    }

    /**
     * update choices for the entities
     */
    private void updatesMenu(){
        System.out.println("1. Update a Droid.");
        System.out.println("2. Update a Client.");
        System.out.println("0. Back to Main Menu.");
    }

    /**
     * calls the selected update methode
     */
    private void runUpdateMenu(){
        Scanner keyboard = new Scanner(System.in);
        this.updatesMenu();
        int command;
        System.out.println("Insert choice:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> updateADroid();

            case (2) -> updateAClient();

            default -> runUpdateMenu();
        }
        runUpdateMenu();
    }

    /**
     * the delete choices for each entity
     */
    private void deleteMenu(){
        System.out.println("1. Delete a Droid.");
        System.out.println("2. Delete a Client.");
        System.out.println("3. Delete a Purchase.");
        System.out.println("0. Back to Main Menu.");
    }

    /**
     * calls the selected delete methode
     */
    private void runDeleteMenu(){
        Scanner keyboard = new Scanner(System.in);
        this.deleteMenu();
        int command;
        System.out.println("Insert choice:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> deleteADroid();

            case (2) -> deleteAClient();

            case (3) -> deleteAPurchase();

            default -> runDeleteMenu();
        }
        runDeleteMenu();
    }

    /**
     * available filter choices
     */
    private void filterMenu(){
        System.out.println("1. Filter droids by a predefined ID.");
        System.out.println("2. Filter droids by a predefined model.");
        System.out.println("3. Filter droids by the minimum power usage.");
        System.out.println("4. Filter clients by a predefined ID.");
        System.out.println("5. Filter clients by a predefined name.");
        System.out.println("6. Filter clients by a predefined address.");
        System.out.println("7. Filter receipts by total price.");
        System.out.println("0. Back to Main Menu.");
    }

    /**
     * calls the selected filter methode
     */
    private void runFilterMenu(){
        Scanner keyboard = new Scanner(System.in);
        this.filterMenu();
        int command;
        System.out.println("Insert choice:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> filterById();

            case (2) -> filterDroids();

            case (3) -> filterDroidsByMinimumPowerUsage();

            case (4) -> filterClientsID();

            case (5) -> filterClientsName();

            case (6) -> filterClientsAddress();

            default -> runFilterMenu();
        }
        runFilterMenu();
    }

    /**
     * available print choices
     */
    private void printMenu(){
        System.out.println("1. Show all Droids.");
        System.out.println("2. Show all Clients.");
        System.out.println("3. Show all Purchases.");
        System.out.println("4. Show all Receipts.");
        System.out.println("0. Back to Main Menu.");
    }

    /**
     * calls the selected print choice
     */
    private void runPrintMenu(){
        Scanner keyboard = new Scanner(System.in);
        this.printMenu();
        int command;
        System.out.println("Insert choice:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> printAllDroids();

            case (2) -> printAllClients();

            case (3) -> printAllPurchases();

            case (4) -> printAllReceipts();

            default -> runPrintMenu();
        }
        runPrintMenu();
    }

    /**
     *
     * @throws ValidatorException
     *          if an added droid is not valid
     * runs the menu until the user chooses to stop the application
     */
    public void runView() throws ValidatorException {
        Scanner keyboard = new Scanner(System.in);
        this.menu();
        int command;
        System.out.println("Insert command:");
        command = keyboard.nextInt();
        switch (command) {
            case (0) -> {
                return;
            }

            case (1) -> runAddMenu();

            case (2) -> runUpdateMenu();

            case (3) -> runDeleteMenu();

            case (4) -> runFilterMenu();

            case (5) -> runPrintMenu();

            default -> runView();
        }
        runView();
    }


    /**
     * shows all the receipts that are stored
     */
    private void printAllReceipts() {
        System.out.println(rs.getAllReceipts());
    }

    /**
     * adds a new receipt to the repo
     */
    private void addAReceipt() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give Purchase ID = ");
            Long pid = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Total Price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            System.out.println(rs.addReceipt(pid,price));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e) {
            System.out.println("Not a valid type.");
        }catch (ExistingException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * deletes a predefined existing purchase
     */
    private void deleteAPurchase(){
        Long id= 1L;
        System.out.println(ps.deletePurchase(id));
    }

    /**
     * prints all purchases
     */
    private void printAllPurchases() {
        System.out.println(ps.getAllPurchases());
    }

    /**
     * adds a new purchase to the repo
     */
    private void addAPurchase() {
        System.out.println("Read purchase information {ClientId, DroidId, totalPrice}.");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.println("Give Client ID = ");
            Long cid = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Droid ID = ");
            Long did = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Total Price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            System.out.println(ps.addPurchase(cid,did,price));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
        }catch (ExistingException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * retrieves all clients that have an ID containing sequence 123
     */
    private void filterClientsID(){
        System.out.println(cs.filterClientsById(1L));
    }

    /**
     * retrieves all clients that have the name Solo
     */
    private void filterClientsName(){
        System.out.println(cs.filterClientsByName("Ion"));
    }

    /**
     * retrieves all clients having the address Star
     */
    private void filterClientsAddress(){
        System.out.println(cs.filterClientsByAddress("Star"));
    }

    /**
     * reads an updated version of a client
     * replaces the old one
     * @throws ValidatorException if data is invalid
     */
    private void updateAClient() throws ValidatorException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give ID = ");
            Long id = Long.parseLong(bufferRead.readLine());

            System.out.println("Give name = ");
            String name = bufferRead.readLine();

            System.out.println("Give address = ");
            String address = bufferRead.readLine();

            System.out.println("Give phoneNumber = ");
            String phoneNumber = bufferRead.readLine();

            System.out.println(cs.updateClient(id,name,address,phoneNumber));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
        }
        catch(NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * deletes a predefined existing client
     */
    private void deleteAClient(){
        Long id= 2L;
        System.out.println(cs.deleteClient(id));
    }

    /**
     * prints all registered clients.
     */
    private void printAllClients() {
        System.out.println(cs.getAllClients());
    }

    /**
     * adds a new client to the repo
     */
    private void addAClient() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give name = ");
            String name = bufferRead.readLine();

            System.out.println("Give address = ");
            String address = bufferRead.readLine();

            System.out.println("Give phoneNumber = ");
            String phoneNumber = bufferRead.readLine();

            System.out.println(cs.addClient(name,address,phoneNumber));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
        }
        catch(ExistingException ex){
            System.out.println(ex.getMessage());
        }
    }


    /**
     * filters droids by model
     */
    private void filterDroids() {
        System.out.println(ctrl.getDroidsByModel("Luke S"));
    }

    /**
     * filters droids by ID
     */
    private void filterById() {
        System.out.println(ctrl.getDroidsById(7L));
    }

    /**
     * filter droids by the minimum power usage
     */
    private void filterDroidsByMinimumPowerUsage(){
        System.out.println(ctrl.getDroidsByMinimumPowerUsage(3));
    }

    /**
     * prints all droids
     */
    private void printAllDroids() {
        System.out.println(ctrl.getAllDroids());
    }

    /**
     *
     * @throws ValidatorException
     *          if the added droid is not valid
     * adds a new droid to the repo
     */
    private void addADroid() throws ValidatorException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give powerUsage = ");
            double powerUsage = Double.parseDouble(bufferRead.readLine());

            System.out.println("Give price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            System.out.println("Give batteryTime = ");
            int batteryTime = Integer.parseInt(bufferRead.readLine());

            System.out.println("Give model = ");
            String model = bufferRead.readLine();

            System.out.println("Give driver = ");
            boolean driver = Boolean.parseBoolean(bufferRead.readLine());

            System.out.println(ctrl.addDroid(powerUsage,price,batteryTime,model,driver));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
        }
        catch(ExistingException ex){
            System.out.println(ex.getMessage());
        }
    }


    /**
     * deletes a predefined existing droid
     */
    private void deleteADroid() {
        Long id= 1L;
        System.out.println(ctrl.deleteDroid(id));
    }

    /**
     * reads a new droid with an existing ID
     * replaces the old droid with that given ID
     * @throws ValidatorException if data is invalid
     */
    private void updateADroid() throws ValidatorException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give ID = ");
            Long id = Long.parseLong(bufferRead.readLine());

            System.out.println("Give powerUsage = ");
            double powerUsage = Double.parseDouble(bufferRead.readLine());

            System.out.println("Give price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            System.out.println("Give batteryTime = ");
            int batteryTime = Integer.parseInt(bufferRead.readLine());

            System.out.println("Give model = ");
            String model = bufferRead.readLine();

            System.out.println("Give driver = ");
            boolean driver = Boolean.parseBoolean(bufferRead.readLine());

            System.out.println(ctrl.updateDroid(id,powerUsage,price,batteryTime,model,driver));
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
        }
        catch(NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
