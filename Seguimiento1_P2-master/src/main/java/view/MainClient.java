package view;

import config.DatabaseConnection;
import model.Client;
import repository.ClientRepository;
import repository.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try(Connection connection = DatabaseConnection.getInstance()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Repository<Client> repository = new ClientRepository();
            Scanner s = new Scanner(System.in);
            String choice;

            do {
                System.out.println("**** Client Management Menu ****");
                System.out.println("1. List Clients");
                System.out.println("2. Search Client by ID");
                System.out.println("3. Save Client");
                System.out.println("4. Delete Client");
                System.out.println("5. Update Client");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = s.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("**** List of clients from the database ****");
                        repository.list().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.println("**** Search Client by ID ****");
                        System.out.println("Enter the client ID: ");
                        int id = s.nextInt();
                        System.out.println(repository.byId(id));
                        break;
                    case "3":
                        System.out.println("**** Create a client ****");
                        System.out.println("Ingrese id: ");
                        Integer iden= s.nextInt();
                        System.out.println("Enter the first name: ");
                        String firstName = s.next();
                        System.out.println("Enter the last name: ");
                        String lastName = s.next();
                        System.out.println("Enter the email: ");
                        String email = s.next();
                        System.out.println("Enter the user: ");
                        String user = s.next();
                        System.out.println("Enter the birthday date (dd/MM/yyyy): ");
                        String birthdayDateStr = s.next();
                        LocalDate birthdayDate = LocalDate.parse(birthdayDateStr, dateTimeFormatter);
                        repository.save(Client.builder()
                                .id(iden)
                                .first_name(firstName)
                                .last_name(lastName)
                                .email(email)
                                .user(user)
                                .birthday_date(birthdayDate)
                                .build());
                        break;
                    case "4":
                        System.out.println("**** Delete Client ****");
                        System.out.println("Enter the client ID to delete: ");
                        int deleteId = s.nextInt();
                        repository.delete(deleteId);
                        break;
                    case "5":
                        System.out.println("**** Update Client ****");
                        System.out.println("Enter the client ID to update: ");
                        int updateId = s.nextInt();
                        System.out.println("Enter the new first name: ");
                        String newFirstName = s.next();
                        System.out.println("Enter the new last name: ");
                        String newLastName = s.next();
                        System.out.println("Enter the new email: ");
                        String newEmail = s.next();
                        System.out.println("Enter the new user: ");
                        String newUser = s.next();
                        System.out.println("Enter the new birthday date (dd/MM/yyyy): ");
                        String newBirthdayDateStr = s.next();
                        LocalDate newBirthdayDate = LocalDate.parse(newBirthdayDateStr, dateTimeFormatter);
                        repository.update(Client.builder()
                                .id(updateId)
                                .first_name(newFirstName)
                                .last_name(newLastName)
                                .email(newEmail)
                                .user(newUser)
                                .birthday_date(newBirthdayDate)
                                .build());
                        break;
                    case "6":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                }
            } while (!choice.equals("6"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}