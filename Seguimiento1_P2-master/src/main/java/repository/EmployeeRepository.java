package repository;
import model.Employee;

import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import config.DatabaseConnection;
import model.Rol;

import static java.sql.DriverManager.getConnection;

public class EmployeeRepository implements Repository<Employee>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    Employee employee = new Employee();
    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirst_name(resultSet.getString("First_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setRol(new Rol(
                resultSet.getInt("id_rol"),
                resultSet.getString("role_name"),
                resultSet.getInt("role_salary")
        ));
        employee.setBirthday_date(resultSet.getDate("birthday_date").toLocalDate());
        return employee;
    }

    @Override
    public List<Employee> list() {
        List<Employee> employeeList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     """
                        
                        SELECT e.*, r.name as role_name, r.id as role_id, r.salary as role_salary
                        FROM employees AS e 
                        INNER JOIN roles AS r ON e.id_rol = r.id;
  
                      """
             ))
        {
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                employeeList.add(employee);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employeeList;
    }


    @Override
    public Employee byId(int id) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT e.*, r.name as role_name, r.id as role_id, r.salary as role_salary\n" +
                        "                                    FROM employees AS e " +
                        "                                    INNER JOIN roles AS r ON e.id_rol = r.id\n" +
                        "                                    WHERE e.id=?")
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                employee = createEmployee(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      INSERT INTO employees(first_name, last_name, id_rol, birthday_date) VALUES (?,?,?,?)
                                      """)
        ){
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setInt(3, employee.getRol().getId());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthday_date()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM employees where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                  UPDATE employees SET first_name = ?, last_name = ?, id_rol = ?, birthday_date = ? WHERE id = ?;
                                      """)
        ){
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setInt(3, employee.getRol().getId());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthday_date()));
            preparedStatement.setInt(5,employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
