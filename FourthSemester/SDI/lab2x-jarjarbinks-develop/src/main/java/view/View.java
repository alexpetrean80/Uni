package view;

import domain.baseEntities.Client;
import domain.baseEntities.Purchase;
import domain.baseEntities.Receipt;
import exceptions.*;
import service.ClientService;
import service.PurchaseService;
import service.ReceiptService;
import service.Service;
import domain.baseEntities.Droid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class View {

    private final Service ctrl;
    private  ClientService cs;
    private  PurchaseService ps;
    private  ReceiptService rs;

    public View(Service ctrl, ClientService cs, PurchaseService ps, ReceiptService rs) {
        this.ctrl = ctrl;
        this.cs = cs;
        this.ps = ps;
        this.rs = rs;
    }



    /**
     *
     * @param dr {@code Controller}
     */
    public View(Service dr) {
        this.ctrl = dr;
    }


    /**
     * Prints out the user menu
     */
    public void menu() {
        System.out.println("    Choose one: ");
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

            case (7) -> filterReceiptsTotalPrice();

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
     * shows all receipts with a total price greater than 90
     */
    private void filterReceiptsTotalPrice(){
        System.out.println("Filtered receipts (Total Price > 90)");
        List<Receipt> receipts = this.rs.filterReceiptsByTotalPrice(90);
        receipts.forEach(System.out::println);
    }

    /**
     * shows all the receipts that are stored
     */
    private void printAllReceipts() {
        List<Receipt> receipts = rs.getReceipts();
        receipts.forEach(System.out::println);
    }

    /**
     * adds a new receipt to the repo
     */
    private void addAReceipt() {
        Receipt rec= readIDPurchaseIDTotalPrice();
        try {
            this.rs.addReceipt(rec);
        } catch (ValidatorException | exceptions.ExistingPurchaseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * reads an ID, purchase ID and the Total Price and creates the new Receipt
     * @return
     */
    private Receipt readIDPurchaseIDTotalPrice() {
        System.out.println("Read purchase information {ClientId, DroidId, totalPrice}.");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give Receipt ID = ");
            Long id = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Purchase ID = ");
            Long pid = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Total Price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            Receipt newReceipt = new Receipt(pid, price);
            newReceipt.setId(id);

            return newReceipt;
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
            readIDsAndPrice();
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
            readIDsAndPrice();
        }
        return null;
    }

    /**
     * deletes a predefined existing purchase
     */
    private void deleteAPurchase(){
        Long id= 123456L;
        try {
            ps.deletePurchase(id);
        } catch (InexistentPurchaseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * prints all purchases
     */
    private void printAllPurchases() {
        List<Purchase> purchases = ps.getPurchases();
        purchases.forEach(System.out::println);
    }

    /**
     * adds a new purchase to the repo
     */
    private void addAPurchase() {
        Purchase pur= readIDsAndPrice();
        try {
            ps.addPurchase(pur);
        } catch (ValidatorException | exceptions.ExistingPurchaseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * reads the details of the purchase: the IDs of droid, client and purchase and the total price
     * @return
     */
    private Purchase readIDsAndPrice() {
        System.out.println("Read purchase information {ClientId, DroidId, totalPrice}.");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give Purchase ID = ");
            Long id = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Client ID = ");
            Long cid = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Droid ID = ");
            Long did = Long.valueOf(bufferRead.readLine());

            System.out.println("Give Total Price = ");
            double price = Double.parseDouble(bufferRead.readLine());

            Purchase newPurchase = new Purchase(cid, price, did);
            newPurchase.setId(id);

            return newPurchase;
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
            readIDsAndPrice();
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
            readIDsAndPrice();
        }
        return null;
    }


    /**
     * retrieves all clients that have an ID containing sequence 123
     */
    private void filterClientsID(){
        System.out.println("Filtered droids (name containing '123'):");
        List<Client> cli = cs.filterClientsById("123");
        cli.forEach(System.out::println);
    }

    /**
     * retrieves all clients that have the name Solo
     */
    private void filterClientsName(){
        System.out.println("Filtered clients (name containing 'Solo'):");
        List<Client> cli = cs.filterClientsByName("Solo");
        cli.forEach(System.out::println);
    }

    /**
     * retrieves all clients having the address Star
     */
    private void filterClientsAddress(){
        System.out.println("Filtered clients (address containing 'Star'):");
        List<Client> cli = cs.filterClientsByAddress("Star");
        cli.forEach(System.out::println);
    }

    /**
     * reads an updated version of a client
     * replaces the old one
     * @throws ValidatorException
     */
    private void updateAClient() throws ValidatorException {
            Client cli = readClient();
            try {
                cs.updateClient(cli);
                return;
            } catch (ValidatorException | exceptions.InexistentClientException e) {
                System.out.println(e.getMessage());
            }
    }

    /**
     * deletes a predefined existing client
     */
    private void deleteAClient(){
        Long id= 1234L;
        try {
            cs.deleteClient(id);
        } catch (InexistentClientException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * prints all registered clients.
     */
    private void printAllClients() {
        List<Client> clients = cs.getClients();
        clients.forEach(System.out::println);
    }

    /**
     * adds a new client to the repo
     */
    private void addAClient() {
            Client client= readClient();
            try {
                cs.addClient(client);
            } catch (ValidatorException | exceptions.ExistingClientException e) {
                System.out.println(e.getMessage());
            }
    }

    /**
     *
     * @return Client
     */
    private Client readClient() {
        System.out.println("Read client {id, name, address, phoneNumber}.");

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

            Client newClient = new Client(name, address, phoneNumber );
            newClient.setId(id);

            return newClient;
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
            readClient();
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
            readClient();
        }
        catch(ExistingClientException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * filters droids by model
     */
    private void filterDroids() {
        System.out.println("Filtered droids (name containing 'r2'):");
        List<Droid> droids = ctrl.filterDroidsByModel("r2");
        droids.forEach(System.out::println);
    }

    /**
     * filters droids by ID
     */
    private void filterById() {
        System.out.println("Filtered droids (name containing '123'):");
        List<Droid> droids = ctrl.filterDroidsById("123");
        droids.forEach(System.out::println);
    }

    /**
     * filter droids by the minimum power usage
     */
    private void filterDroidsByMinimumPowerUsage(){
        System.out.println("Filtered droids (power usage > 10)");
        List<Droid> droids = this.ctrl.filterDroidsByMinimumPowerUsage(10);
        droids.forEach(System.out::println);
    }

    /**
     * prints all droids
     */
    private void printAllDroids() {
        List<Droid> droids = ctrl.getDroids();
        droids.forEach(System.out::println);
    }

    /**
     *
     * @throws ValidatorException
     *          if the added droid is not valid
     * adds a new droid to the repo
     */
    private void addADroid() throws ValidatorException {
        Droid droid = readDroid();
        try {
            ctrl.addDroid(droid);
        } catch (ValidatorException | exceptions.ExistingDroidException e) {
            System.out.println(e.getMessage()); }
    }

    /**
     *
     * @return Droid
     * reads droid information and creates the droid
     */
    private Droid readDroid() {
        System.out.println("Read droid { id, powerUsage, price, batteryTime, model, driver}.");

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

            Droid newDroid = new Droid(powerUsage, price, batteryTime, model, driver);
            newDroid.setId(id);

            return newDroid;
        } catch (IOException ex) {
            System.out.println("Line does not exist.");
             readDroid();
        }catch(IllegalArgumentException e){
            System.out.println("Not a valid type.");
            readDroid();
        }
        catch(ExistingDroidException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * deletes a predefined existing droid
     */
    private void deleteADroid() {
        Long id= 123L;
            try {
                ctrl.deleteDroid(id);
            } catch (InexistentDroidException e) {
                System.out.println(e.getMessage());
            }
        }

    /**
     * reads a new droid with an existing ID
     * replaces the old droid with that given ID
      * @throws ValidatorException
     */
    private void updateADroid() throws ValidatorException {
            Droid droid = readDroid();
            try {
                ctrl.updateDroid(droid);
            } catch (ValidatorException | exceptions.InexistentDroidException e) {
                System.out.println(e.getMessage());
            }
    }

}
