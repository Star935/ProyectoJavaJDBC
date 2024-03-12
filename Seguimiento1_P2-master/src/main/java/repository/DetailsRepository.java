package repository;

import config.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailsRepository implements Repository<Details>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Details createDetails(ResultSet resultSet) throws SQLException {
        Details details = new Details();
        Client client = new Client();
        client.setId(resultSet.getInt("ID_Client"));
        client.setFirst_name(resultSet.getString("Client_First_Name"));
        client.setLast_name(resultSet.getString("Client_Last_Name"));
        // Completa la configuración del cliente según las columnas seleccionadas en tu consulta SQL
        // client.setEmail(resultSet.getString("email"));
        // client.setUser(resultSet.getString("user"));
        // client.setBirthday_date(resultSet.getDate("birthday_date").toLocalDate());
        details.setClient(client);

        Employee employee = new Employee();
        employee.setId(resultSet.getInt("ID_Employee"));
        employee.setFirst_name(resultSet.getString("First_Name"));
        employee.setLast_name(resultSet.getString("Last_Name"));
        // Completa la configuración del empleado según las columnas seleccionadas en tu consulta SQL
        // employee.setRol(new Rol(resultSet.getInt("role_id"), resultSet.getString("role_name"), resultSet.getInt("role_salary")));
        details.setEmployee(employee);

        Toy toy = new Toy();
        toy.setId(resultSet.getInt("ID_Toy"));
        toy.setName(resultSet.getString("Toy"));
        toy.setAmount(resultSet.getInt("Amount"));
        toy.setPrice_unit(resultSet.getInt("Price_Unit"));
        toy.setPrice_total(resultSet.getInt("Price_Total"));
        // No necesitas asignar el tipo de juguete aquí, ya que no parece estar incluido en tu consulta SQL
        details.setToy(toy);

        Sale sale = new Sale();
        sale.setInvoice_number(resultSet.getInt("Invoice_Number"));
        sale.setDate(resultSet.getDate("Date").toLocalDate());
        details.setSales(sale);

        return details;
    }
    @Override
    public List<Details> list() {
        List<Details> detailsList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """
                       SELECT details.sales_id AS Invoice_Number,
                        sales.date AS Date, 
                        employees.id AS ID_Employee, 
                        employees.first_name AS First_Name,
                        employees.last_name AS Last_Name,
                        clients.id AS ID_Client,
                        clients.first_name AS Client_First_Name,
                        clients.last_name AS Client_Last_Name,
                        toys.id AS ID_Toy,
                        toys.name AS Toy,
                        toys.amount AS Amount,
                        toys.price_unit AS Price_Unit,
                        toys.price_total AS Price_Total
                 FROM details
                 INNER JOIN sales ON details.sales_id = sales.invoice_number
                 INNER JOIN employees ON details.employee_id = employees.id
                 INNER JOIN clients ON details.client_id = clients.id
                 INNER JOIN toys ON details.toy_id = toys.id;
                 """
             ))
        {
            while (resultSet.next()) {
                Details details = createDetails(resultSet);
                detailsList.add(details);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return detailsList;
    }

    @Override
    public Details byId(int id) {
        return null;
    }

    @Override
    public void save(Details details) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Details details) {

    }

}
