package view;

import config.DatabaseConnection;
import model.Employee;
import model.Rol;
import model.Sale;
import repository.EmployeeRepository;
import repository.Repository;
import repository.SaleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainSale {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Repository<Sale> repository = new SaleRepository();
            Scanner s = new Scanner(System.in);
            String op;
            do {
                System.out.println("**** Menu ****");
                System.out.println("1. List");
                System.out.println("2. Search by Id");
                System.out.println("3. Save");
                System.out.println("4. Delete");
                System.out.println("5. Update");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                op = s.next();
                switch (op) {
                    case "1":
                        System.out.println("**** List ****");
                        repository.list().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.println("**** Search by Id ****");
                        System.out.print("Enter invoice number: ");
                        Integer id = s.nextInt();
                        System.out.println(repository.byId(id));
                        break;
                    case "3":
                        System.out.println("**** Save ****");
                        System.out.print("Enter invoice number: ");
                        Integer in_num = s.nextInt();
                        System.out.print("Enter the date the invoice was created (dd/MM/yyyy): ");
                        String date = s.next();
                        LocalDate datev = LocalDate.parse(date, dateTimeFormatter);
                        repository.save(Sale.builder()
                                .invoice_number(in_num)
                                .date(datev)
                                .build());
                        break;
                    case "4":
                        System.out.println("**** Delete ****");
                        System.out.print("Enter the number of the invoice you want to delete: ");
                        Integer del = s.nextInt();
                        repository.delete(del);
                        break;
                    case "5":
                        System.out.println("**** Update ****");
                        System.out.print("Enter the new date (dd/MM/yyyy): ");
                        String dateu = s.next();
                        LocalDate daten = LocalDate.parse(dateu, dateTimeFormatter);
                        System.out.print("Enter the invoice id to change date: ");
                        Integer ic = s.nextInt();
                        repository.update(Sale.builder()
                                .date(daten)
                                .invoice_number(ic)
                                .build());
                        break;
                    case "6":
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number from 1 to 6.");
                }
            } while (!op.equals("6"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
