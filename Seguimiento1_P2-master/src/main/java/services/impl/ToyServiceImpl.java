package services.impl;

import mapping.dtos.ToyDto;

import mapping.mappers.ToyMapper;
import model.Toy;
import model.TypeToy;
import services.ToyStoreImpl;
import utilis.Constants;
import utilis.FileUtilis;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;

public class ToyServiceImpl implements ToyStoreImpl {
    private List<Toy> listToy;
    public ToyServiceImpl() {
        // Inicializa la lista listToy
        listToy = new ArrayList<>();
        // Carga los juguetes desde el archivo si existe
        File file = new File(Constants.PATH_TOYS);
        if (file.exists()) {
            listToy = FileUtilis.getToys(file);
        }
    }
    @Override
    public List<ToyDto> listToys() {
        return listToy.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public List<ToyDto> addToy(ToyDto toyDto) throws Exception {
        if(!verifyExist(toyDto.name())){
            listToy.add(ToyMapper.mapFrom(toyDto));
            FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
            return listToy.stream().map(ToyMapper::mapFrom).toList();
        }
        throw new Exception("This toy is in the list");
    }

    @Override
    public  Map.Entry<TypeToy,Integer> maxToy() throws Exception {
        return sort().entrySet().stream().reduce((first,second)-> second).orElse(null);
    }

    @Override
    public Map.Entry<TypeToy, Integer> minToy() throws Exception {
        return sort().entrySet().stream().findFirst().orElse(null);
    }

    @Override
    public Map<TypeToy, Integer> sort() throws Exception {
        return showByType().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    @Override
    public ToyDto search(String name) throws Exception {
        if(verifyExist(name)){
            List<ToyDto> list = listToy.stream().filter(toyList-> Objects.equals(toyList.getName(), name))
                    .findFirst().stream().map(ToyMapper::mapFrom).toList();
            return list.get(0);
        }
        throw new Exception("Dont found");
    }

    @Override
    public List<ToyDto> increase(ToyDto toyDto, int amount) throws Exception {
        listToy.stream().filter(toy1 -> Objects.equals(toy1.getName(),toyDto.name()))
                .peek(toy -> toy.setAmount(toy.getAmount()+amount))
                .findFirst();
        FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
        return listToy.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public List<ToyDto> decrease(ToyDto toyDto, int amount) throws Exception {
        listToy.stream().filter(toy1 -> Objects.equals(toy1.getName(),toyDto.name()))
                .peek(toy -> {
                    if(toy.getAmount()>0){
                        toy.setAmount(toy.getAmount() - amount);
                    } else if (toy.getAmount()==0) {
                        listToy.remove(toy);
                    }
                })
                .findFirst();
        FileUtilis.saveToys(new File(Constants.PATH_TOYS), listToy);
        return listToy.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public Map<TypeToy, Integer> showByType() throws Exception {
        Map<TypeToy,Integer> showByType = new TreeMap<>();
        for(Toy toy : listToy){
            TypeToy type = toy.getType();
            showByType.put(type,showByType.getOrDefault(type,0)+1);
        }
        return showByType;
    }

    @Override
    public List<ToyDto> showLargerThan(double value) throws Exception {
        return listToy.stream()
                .filter(toy -> toy.getPrice_unit() >= value)
                .toList().stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public Boolean verifyExist(String name) {
        return listToy.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }

    @Override
    public Integer totalToys() throws Exception {
        Integer count = 0;
        for (Toy toy : listToy){
            count += toy.getAmount();
        }
        return count;
    }
}
