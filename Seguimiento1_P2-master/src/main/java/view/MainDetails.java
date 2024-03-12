package view;

import config.DatabaseConnection;
import model.Details;
import model.Toy;
import repository.DetailsRepository;
import repository.Repository;
import repository.ToyStoreRepository;
import repository.ToysRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainDetails {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance()) {
            Repository<Details> repository = new DetailsRepository();

            System.out.println("List");
            repository.list().stream().forEach(System.out::println);


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
