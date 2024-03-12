package services.impl;
import mapping.dtos.ToyDto;
import mapping.mappers.ToyMapper;
import model.Toy;
import model.TypeToy;
import org.junit.jupiter.api.*;
import utilis.Constants;
import utilis.FileUtilis;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ToyServiceImplTest {

   /* private ToyServiceImpl service;

    @Test
    public void listTest(){
        // Propósito: Verificar si el método listToys() de la clase ToyServiceImpl
        // devuelve una lista con un solo juguete después de la inicialización.
        service = new ToyServiceImpl();
        ToyDto toy1 =  new ToyDto("test", TypeToy.FEMALE,23,10);
        List<ToyDto> expect = List.of(toy1);
        List<ToyDto> result = service.listToys();
        assertEquals(expect.size(),result.size());
    }
    @Test
    public void addTest() throws Exception {
        // Propósito: Verificar si el método addToy() de la clase ToyServiceImpl
        // agrega correctamente un nuevo juguete a la lista de juguetes.
        service = new ToyServiceImpl();
        File file = new File(Constants.PATH_TOYS);
        List<Toy> expected = new ArrayList<>();
        if (file.exists()) {
            expected = FileUtilis.getToys(file);
        }
        ToyDto newToy =  new ToyDto("Tamaras", TypeToy.FEMALE,23,10);

        assertEquals(expected.size(),service.listToys().size());
        expected.add(ToyMapper.mapFrom(newToy));
        List<ToyDto> result = service.addToy(newToy);
        assertEquals(expected.size(),service.listToys().size());
    }
    @Test
    public void maxTest() throws Exception{
        // Propósito: Verificar si el método maxToy() de la clase ToyServiceImpl
        // devuelve el tipo de juguete más común y su cantidad asociada.
        service = new ToyServiceImpl();

        Map.Entry<TypeToy, Integer> maxToyEntry = service.maxToy();
        assertNotNull(maxToyEntry);
        TypeToy actualType = maxToyEntry.getKey();
        int actualCount = maxToyEntry.getValue();

        List<ToyDto> toyList = service.listToys();
        Map<TypeToy, Integer> toyCounts = new HashMap<>();
        for (ToyDto toy : toyList) {
            toyCounts.put(toy.type(), toyCounts.getOrDefault(toy.type(), 0) + 1);
        }

        TypeToy expectedType = null;
        int expectedCount = 0;
        for (Map.Entry<TypeToy, Integer> entry : toyCounts.entrySet()) {
            if (entry.getValue() > expectedCount) {
                expectedType = entry.getKey();
                expectedCount = entry.getValue();
            }
        }
        assertEquals(expectedType, actualType);
        assertEquals(expectedCount, actualCount);
    }
    @Test
    public void minTest() throws Exception{
        // Propósito: Verificar si el método minToy() de la clase ToyServiceImpl
        // devuelve el tipo de juguete menos común y su cantidad asociada.
        service = new ToyServiceImpl();


        Map.Entry<TypeToy, Integer> minToyEntry = service.minToy();
        assertNotNull(minToyEntry);
        TypeToy actualType = minToyEntry.getKey();
        int actualCount = minToyEntry.getValue();


        List<ToyDto> toyList = service.listToys();
        Map<TypeToy, Integer> toyCounts = new HashMap<>();
        for (ToyDto toy : toyList) {
            toyCounts.put(toy.type(), toyCounts.getOrDefault(toy.type(), 0) + 1);
        }

        TypeToy expectedType = null;
        int expectedCount = Integer.MAX_VALUE;

        for (Map.Entry<TypeToy, Integer> entry : toyCounts.entrySet()) {
            if (entry.getValue() < expectedCount) {
                expectedType = entry.getKey();
                expectedCount = entry.getValue();
            }
        }

        assertEquals(expectedType, actualType);
        assertEquals(expectedCount, actualCount);
    }
    @Test
    public void sortTest() throws Exception{
        // Propósito: Verificar si el método sort() de la clase ToyServiceImpl
        // ordena correctamente los tipos de juguetes por su cantidad asociada.

        service = new ToyServiceImpl();
        Map<TypeToy, Integer> sortedToyMap = service.sort();
        assertNotNull(sortedToyMap);

        List<ToyDto> toyList = service.listToys();
        Map<TypeToy, Integer> toyCounts = new HashMap<>();
        for (ToyDto toy : toyList) {
            toyCounts.put(toy.type(), toyCounts.getOrDefault(toy.type(), 0) + 1);
        }

        LinkedHashMap<TypeToy, Integer> expectedSortedMap = toyCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        assertEquals(expectedSortedMap, sortedToyMap);
    }
    @Test

    public void searchTest() throws Exception{
        // Propósito: Verificar si el método search() de la clase ToyServiceImpl
        // encuentra correctamente un juguete por su nombre.

        service = new ToyServiceImpl();

        List<ToyDto> toyList = service.listToys();
        ToyDto randomToy = toyList.get(new Random().nextInt(toyList.size()));
        ToyDto searchedToy = service.search(randomToy.name());
        assertNotNull(searchedToy);

        assertEquals(randomToy.name(), searchedToy.name());
    }
    @Test
    public void increaseTest() throws Exception{
        // Propósito: Verificar si el método increase() de la clase ToyServiceImpl
        // aumenta correctamente la cantidad de un juguete existente.

        service = new ToyServiceImpl();

        List<ToyDto> toyList = service.listToys();
        ToyDto randomToy = toyList.get(new Random().nextInt(toyList.size()));
        int amountToAdd = 5;

        List<ToyDto> updatedToyList = service.increase(randomToy, amountToAdd);

        Optional<ToyDto> updatedToyOptional = updatedToyList.stream()
                .filter(toy -> toy.name().equals(randomToy.name()))
                .findFirst();

        assertTrue(updatedToyOptional.isPresent());

        ToyDto updatedToy = updatedToyOptional.get();
        assertEquals(randomToy.amount() + amountToAdd, updatedToy.amount());
    }
    @Test
    public void decreaseTest() throws Exception {
        // Propósito: Verificar si el método decrease() de la clase ToyServiceImpl
        // disminuye correctamente la cantidad de un juguete existente.

        service = new ToyServiceImpl();

        List<ToyDto> toyList = service.listToys();

        ToyDto randomToy = toyList.get(new Random().nextInt(toyList.size()));
        int amountToSubtract = 3;

        List<ToyDto> updatedToyList = service.decrease(randomToy, amountToSubtract);

        Optional<ToyDto> updatedToyOptional = updatedToyList.stream()
                .filter(toy -> toy.name().equals(randomToy.name()))
                .findFirst();

        assertTrue(updatedToyOptional.isPresent());

        ToyDto updatedToy = updatedToyOptional.get();
        assertEquals(randomToy.amount() - amountToSubtract, updatedToy.amount());
    }
    @Test
    public void showByTypeTest() throws Exception{
        // Propósito: Verificar si el método showByType() de la clase ToyServiceImpl
        // devuelve correctamente un mapa que muestra la cantidad de juguetes por tipo.

        service = new ToyServiceImpl();
        Map<TypeToy, Integer> toyCountByType = service.showByType();
        assertNotNull(toyCountByType);

        List<ToyDto> toyList = service.listToys();

        Map<TypeToy, Integer> expectedToyCountByType = new HashMap<>();
        for (ToyDto toy : toyList) {
            expectedToyCountByType.put(toy.type(), expectedToyCountByType.getOrDefault(toy.type(), 0) + 1);
        }

        assertEquals(expectedToyCountByType, toyCountByType);
    }
    @Test
    public void showLargerThanTest() throws Exception {
        // Propósito: Verificar si el método showLargerThan() de la clase ToyServiceImpl
        // devuelve correctamente una lista de juguetes cuyo precio es mayor que el valor especificado.

        service = new ToyServiceImpl();

        int filterValue = 40;

        List<ToyDto> largerToys = service.showLargerThan(filterValue);
        assertNotNull(largerToys);

        for (ToyDto toy : largerToys) {
            assertTrue(toy.price() < filterValue);
        }
    }

    @Test
    public void verifyExistTest() throws Exception {
        // Propósito: Verificar si el método verifyExist() de la clase ToyServiceImpl
        // devuelve correctamente true si un juguete con el nombre especificado existe en la lista.

        service = new ToyServiceImpl();
        List<ToyDto> toyList = service.listToys();

        String randomToyName = toyList.get(new Random().nextInt(toyList.size())).name();
        boolean exists = service.verifyExist(randomToyName);
        assertTrue(exists);
    }

    @Test
    public void totalToysTest() throws Exception {
        // Propósito: Verificar si el método totalToys() de la clase ToyServiceImpl
        // devuelve correctamente el número total de juguetes en la lista.

        service = new ToyServiceImpl();
        int totalToys = service.totalToys();
        assertTrue(totalToys >= 0);
    }*/
}