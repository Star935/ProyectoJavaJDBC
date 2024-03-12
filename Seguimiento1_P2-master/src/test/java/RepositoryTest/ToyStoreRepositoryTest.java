package RepositoryTest;
import model.Toy;
import model.TypeToy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.ToyStoreRepository;

import java.sql.SQLException;
import java.util.List;

public class ToyStoreRepositoryTest {
    @Test
    void listToys() throws SQLException {
        ToyStoreRepository toyStoreRepository = new ToyStoreRepository();
        List<Toy> toys = toyStoreRepository.listToys();
        Assertions.assertEquals(3, toys.size());
    }

    @Test
    void ById() throws SQLException {
        ToyStoreRepository toyStoreRepository = new ToyStoreRepository();
        Toy toy = toyStoreRepository.Byid(1);
        Assertions.assertEquals("Ball", toy.getName());
    }

    @Test
    void save() throws SQLException {
        ToyStoreRepository toyStoreRepository = new ToyStoreRepository();
        Toy toy = new Toy();
        toy.setName("Car");
        TypeToy typeToy = new TypeToy();
        typeToy.setId(1);
        typeToy.setType("Car");
        toy.setType(typeToy);
        toy.setAmount(10);
        toy.setPrice_unit(100);
        toy.setPrice_total(1000);
        Toy savedToy = toyStoreRepository.Byid(toy.getId());
        Assertions.assertEquals(toy, savedToy);
    }

    @Test
    void totalToys() throws SQLException, Exception {
        ToyStoreRepository toyStoreRepository = new ToyStoreRepository();
        Integer totalToys = toyStoreRepository.totalToys();
        Assertions.assertEquals(30, totalToys);
    }

    @Test
    void delete() throws SQLException {
        ToyStoreRepository toyStoreRepository = new ToyStoreRepository();
        toyStoreRepository.delete(1);
        Toy toy = toyStoreRepository.Byid(1);
        Assertions.assertNull(toy);
    }
}
