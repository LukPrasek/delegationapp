package pl.lukaszprasek.delegationApp.common.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lukaszprasek.delegationApp.common.dto.CarDto;
import pl.lukaszprasek.delegationApp.domain.entities.CarEntity;

@Component
public class CarMapperFromEntityToDtoImpl implements CarMapperFromEntityToDto<CarEntity, CarDto> {
    //private final PassengerMapperFromEntityToDto passengerMapperFromEntityToDto;
    private  PassengerMapperFromEntityToDto passengerMapperFromEntityToDto;
    @Autowired
    public void setPassengerMapperFromEntityToDto(PassengerMapperFromEntityToDto passengerMapperFromEntityToDto) {
        this.passengerMapperFromEntityToDto = passengerMapperFromEntityToDto;
    }
//    public CarMapperFromEntityToDtoImpl(PassengerMapperFromEntityToDto passengerMapperFromEntityToDto) {
//        this.passengerMapperFromEntityToDto = passengerMapperFromEntityToDto;
//    }

    @Override
    public CarDto mapToDto(CarEntity from) {
        if (from == null) {
            return null;
        } else {
            return new CarDto.Builder().withCarId(from.getCarId())
                    .withBrand(from.getBrand())
                    .withModel(from.getModel())
                    .withSeatsNumber(from.getSeatsNumber())
                   // .withPassengers(passengerMapperFromEntityToDto.mapList(from.getPassengerEntities()))
                    .build();
        }
    }
}
