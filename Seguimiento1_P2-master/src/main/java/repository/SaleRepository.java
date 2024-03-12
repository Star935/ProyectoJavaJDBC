package repository;

import config.DatabaseConnection;
import model.Employee;
import model.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SaleRepository implements Repository<Sale>{
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    Sale sale= new Sale();
    private Sale createSale (ResultSet resultSet) throws SQLException{
        Sale sale= new Sale();
        sale.setInvoice_number(resultSet.getInt("invoice_number"));
        sale.setDate(resultSet.getDate("date").toLocalDate());
        return sale;
    }
    @Override
    public List<Sale> list() {
        List<Sale> saleList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery
                     ("SELECT * FROM sales"))
        {
            while (resultSet.next()) {
                Sale sale = createSale(resultSet);
                saleList.add(sale);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return saleList;
    }

    @Override
    public Sale byId(int id) {
        try  (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT sales.invoice_number , sales.date FROM sales WHERE invoice_number=?;")
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                sale = createSale(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sale;
        }

    @Override
    public void save(Sale sale) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""          
                        INSERT INTO sales  (invoice_number,date)  VALUES (?,?)
                        """)
        ){
            preparedStatement.setInt(1, sale.getInvoice_number());
            preparedStatement.setDate(2, Date.valueOf(sale.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM sales where invoice_number=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Sale sale) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                     UPDATE sales SET date= ? WHERE invoice_number=?;
                                      """)
        ){
            preparedStatement.setDate(1, Date.valueOf(sale.getDate()));
            preparedStatement.setInt(2, sale.getInvoice_number());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


