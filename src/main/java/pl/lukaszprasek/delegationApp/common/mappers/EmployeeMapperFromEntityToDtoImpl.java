package pl.lukaszprasek.delegationApp.common.mappers;


import org.springframework.stereotype.Component;
import pl.lukaszprasek.delegationApp.common.dto.EmployeeDto;
import pl.lukaszprasek.delegationApp.domain.entities.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperFromEntityToDtoImpl implements EmployeeMapperFromEntityToDto<EmployeeEntity, EmployeeDto> {

    @Override
    public EmployeeDto mapEmployeeEntityToDto(EmployeeEntity from) {
        return new EmployeeDto.Builder()
                .withEmpId(from.getEmpId())
                .withName(from.getName())
                .withSurname(from.getSurname())
                .withEmployeePosition(from.getEmployeePosition().toString())
                .withStartWorkingDay(from.getStartWorkingDate())
                .withBirthday(from.getBirthday())
                .withSiteDto(from.getSiteEntity().getSiteId())
                .build();
    }

    @Override
    public List<EmployeeDto> mapListFromEntitiesToDtos(List<EmployeeEntity> from) {
        return from.stream().map(this::mapEmployeeEntityToDto).collect(Collectors.toList());
    }
}
