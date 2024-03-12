package mapping.dtos;

import model.TypeToy;

public record ToyDto(Integer id,String name, TypeToy type, Integer price_unit,Integer price_total, Integer amount ){

}
