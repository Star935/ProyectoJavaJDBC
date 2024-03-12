package services;

import mapping.dtos.ToyDto;
import model.TypeToy;

import java.util.List;
import java.util.Map;

public interface ToyStoreImpl {
    List<ToyDto> listToys();
    List<ToyDto> addToy (ToyDto toyDto) throws Exception;
    Map.Entry<TypeToy,Integer> maxToy() throws Exception;
    Map.Entry<TypeToy,Integer> minToy() throws Exception;
    Map<TypeToy,Integer> sort() throws Exception;
    ToyDto search(String name) throws Exception;
    List<ToyDto> increase(ToyDto toyDto, int amount) throws Exception;
    List<ToyDto> decrease(ToyDto toyDto, int amount) throws Exception;
    Map<TypeToy,Integer> showByType() throws Exception;
    List<ToyDto> showLargerThan(double value) throws Exception;
    Boolean verifyExist(String name);
    Integer totalToys() throws Exception;

}
