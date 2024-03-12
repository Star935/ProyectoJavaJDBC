package repository;

import mapping.dtos.ToyDto;
import model.Toy;
import model.TypeToy;

import java.util.List;
import java.util.Map;

public interface ToysRepository<Toy>{
    List<Toy> listToys();
    Toy Byid(Integer id);
    void save(Toy toy);
    Integer totalToys() throws Exception;
    void delete(int id);
}
