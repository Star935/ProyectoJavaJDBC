package view;

import config.DatabaseConnection;
import model.*;
import repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
            try (Connection connection = DatabaseConnection.getInstance()) {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("**** Main Menu ****");
                    System.out.println("1. Employee Management");
                    System.out.println("2. Client Management");
                    System.out.println("3. Toy Management");
                    System.out.println("4. Sale Management");
                    System.out.println("5. Details Management");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            manageEmployees();
                            break;
                        case 2:
                            manageClients();
                            break;
                        case 3:
                            manageToys();
                            break;
                        case 4:
                            manageSales();
                            break;
                        case 5:
                            manageDetails();
                            break;
                        case 6:
                            System.out.println("Exiting...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error establishing connection with the database.", e);
            } catch (Exception e) {
                throw new RuntimeException("An error occurred during program execution.", e);
            }
        }

    private static void manageEmployees() {
        Scanner scanner = new Scanner(System.in);
        EmployeeRepository repository = new EmployeeRepository(); // Reemplaza EmployeeRepository con tu clase de repositorio real

        while (true) {
            System.out.println("**** Employee Management ****");
            System.out.println("1. List Employees");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. Add Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Update Employee");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("**** List of Employees ****");
                    repository.list().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("**** Search Employee by ID ****");
                    System.out.print("Enter the employee ID: ");
                    int id = scanner.nextInt();
                    Employee employee = repository.byId(id);
                    if (employee != null) {
                        System.out.println("Employee found:");
                        System.out.println(employee);
                    } else {
                        System.out.println("No employee found with the provided ID.");
                    }
                    break;
                case 3:
                    // Add Employee functionality - implement as needed
                    break;
                case 4:
                    System.out.println("**** Delete Employee ****");
                    System.out.print("Enter the employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    repository.delete(deleteId);
                    System.out.println("Employee deleted successfully.");
                    break;
                case 5:
                    // Update Employee functionality - implement as needed
                    break;
                case 6:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }


    private static void manageClients() {
        Scanner scanner = new Scanner(System.in);
        ClientRepository repository = new ClientRepository(); // Reemplaza ClientRepository con tu clase de repositorio real

        while (true) {
            System.out.println("**** Client Management ****");
            System.out.println("1. List Clients");
            System.out.println("2. Search Client by ID");
            System.out.println("3. Add Client");
            System.out.println("4. Delete Client");
            System.out.println("5. Update Client");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("**** List of Clients ****");
                    repository.list().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("**** Search Client by ID ****");
                    System.out.print("Enter the client ID: ");
                    int id = scanner.nextInt();
                    Client client = repository.byId(id);
                    if (client != null) {
                        System.out.println("Client found:");
                        System.out.println(client);
                    } else {
                        System.out.println("No client found with the provided ID.");
                    }
                    break;
                case 3:
                    // Add Client functionality - implement as needed
                    break;
                case 4:
                    System.out.println("**** Delete Client ****");
                    System.out.print("Enter the client ID to delete: ");
                    int deleteId = scanner.nextInt();
                    repository.delete(deleteId);
                    System.out.println("Client deleted successfully.");
                    break;
                case 5:
                    // Update Client functionality - implement as needed
                    break;
                case 6:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }


    private static void manageToys() {
        Scanner scanner = new Scanner(System.in);
        ToysRepository repository = new ToyStoreRepository();
        List<TypeToy> types = new ArrayList<>();

        while (true) {
            System.out.println("**** Toy Management ****");
            System.out.println("1. List Toys");
            System.out.println("2. Search Toy by ID");
            System.out.println("3. Add Toy");
            System.out.println("4. Delete Toy");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("**** List of Toys ****");
                    repository.listToys().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Search by ID");
                    System.out.println("Enter the ID of the toy you want to search for: ");
                    int toyId = scanner.nextInt();
                    Toy toy = null;
                    try {
                        toy = (Toy) repository.Byid(toyId);
                    } catch (Exception e) {
                        System.out.println("No toy found with the provided ID.");
                    }
                    if (toy!= null) {
                        System.out.println("Toy found:");
                        System.out.println(toy);
                    }
                    break;
                case 3:
                    // Add Toy functionality - implement as needed
                    break;
                case 4:
                    System.out.println("**** Delete Toy ****");
                    System.out.print("Enter the toy ID to delete: ");
                    int deleteId = scanner.nextInt();
                    repository.delete(deleteId);
                    System.out.println("Toy deleted successfully.");
                    break;
                case 5:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }


    private static void manageSales() {
        Scanner scanner = new Scanner(System.in);
        SaleRepository repository = new SaleRepository(); // Reemplaza SaleRepository con tu clase de repositorio real

        while (true) {
            System.out.println("**** Sale Management ****");
            System.out.println("1. List Sales");
            System.out.println("2. Search Sale by Invoice Number");
            System.out.println("3. Add Sale");
            System.out.println("4. Delete Sale");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("**** List of Sales ****");
                    repository.list().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("**** Search Sale by Invoice Number ****");
                    System.out.print("Enter the invoice number: ");
                    int invoiceNumber = scanner.nextInt();
                    Sale sale = repository.byId(invoiceNumber);
                    if (sale != null) {
                        System.out.println("Sale found:");
                        System.out.println(sale);
                    } else {
                        System.out.println("No sale found with the provided invoice number.");
                    }
                    break;
                case 3:
                    // Add Sale functionality - implement as needed
                    break;
                case 4:
                    System.out.println("**** Delete Sale ****");
                    System.out.print("Enter the invoice number of the sale to delete: ");
                    int deleteInvoiceNumber = scanner.nextInt();
                    repository.delete(deleteInvoiceNumber);
                    System.out.println("Sale deleted successfully.");
                    break;
                case 5:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }


    private static void manageDetails() {
        Scanner scanner = new Scanner(System.in);
        DetailsRepository repository = new DetailsRepository();

        while (true) {
            System.out.println("**** Details Management ****");
            System.out.println("1. List Details");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("**** List of Details ****");
                    repository.list().forEach(System.out::println);
                    break;
            }
        }
    }
}

