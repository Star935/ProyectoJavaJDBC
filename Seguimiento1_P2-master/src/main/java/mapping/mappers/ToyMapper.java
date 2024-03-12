package mapping.mappers;  // Its goal is to convert objects of one type into another

import lombok.Builder;
import mapping.dtos.ToyDto;
import model.Toy;
@Builder
public class ToyMapper {
    // Create a new Toy object using the values from the ToyDto object
    public static Toy mapFrom(ToyDto toyDto ){
        return new Toy(toyDto.id(), toyDto .name(),toyDto.type(),toyDto.price_unit(), toyDto.price_total(), toyDto.amount());}
    // Create a new ToyDto object using the values from the Toy object
    public static ToyDto mapFrom(Toy toy){return  new ToyDto(toy.getId(), toy.getName(),toy.getType(),toy.getPrice_unit(), toy.getPrice_total(), toy.getAmount());
    }
}