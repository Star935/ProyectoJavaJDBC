package view;

import config.DatabaseConnection;
import model.Toy;
import model.TypeToy;
import repository.ToyStoreRepository;
import repository.ToysRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainToy {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance()) {
            ToysRepository<Toy> repository = new ToyStoreRepository();
            Scanner scanner = new Scanner(System.in);
            List<TypeToy> types = new ArrayList<>();
            types.add(TypeToy.builder().id(0).type("FEMALE").build());
            types.add(TypeToy.builder().id(1).type("MALE").build());
            types.add(TypeToy.builder().id(2).type("UNISEX").build());

            while (true) {
                System.out.println("**** Menu ****");
                System.out.println("1. List toys");
                System.out.println("2. Search toy by ID");
                System.out.println("3. Create a new toy");
                System.out.println("4. Delete a toy");
                System.out.println("5. Show total number of toys");
                System.out.println("6. Exit");
                System.out.println("Enter your choice: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("**** List ****");
                        repository.listToys().forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Search by ID");
                        System.out.println("Enter the ID of the toy you want to search for: ");
                        int toyId = scanner.nextInt();
                        Toy toy = repository.Byid(toyId);
                        if (toy != null) {
                            System.out.println("Toy found:");
                            System.out.println(toy);
                        } else {
                            System.out.println("No toy found with the provided ID.");
                        }
                        break;
                    case 3:
                        System.out.println("**** Create a new toy ****");
                        System.out.println("Enter the name of the toy: ");
                        String name = scanner.next();
                        System.out.println("Enter the gender of the toy (0: Female, 1: Male, 2: Unisex): ");
                        int gender = scanner.nextInt();
                        System.out.println("Enter the quantity of the toy: ");
                        int amount = scanner.nextInt();
                        System.out.println("Enter the unit price of the toy: ");
                        int price = scanner.nextInt();
                        repository.save(Toy.builder()
                                .name(name)
                                .type(types.get(gender))
                                .amount(amount)
                                .price_unit(price)
                                .price_total(amount * price)
                                .build());
                        System.out.println("The toy has been created successfully!");
                        break;
                    case 4:
                        System.out.println("**** Delete a toy ****");
                        System.out.println("Enter the ID of the toy you want to delete: ");
                        int toyIdToDelete = scanner.nextInt();
                        repository.delete(toyIdToDelete);
                        System.out.println("The toy has been deleted successfully.");
                        break;
                    case 5:
                        System.out.println("**** TOTAL TOYS ****");
                        System.out.println("The total number of toys is: " + repository.totalToys());
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing connection with the database.", e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during program execution.", e);
        }
    }
}

