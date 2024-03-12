package mapping.mappers;

import mapping.dtos.EmployeeDTO;
import model.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapFromModel(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getFirst_name(), employee.getLast_name(), employee.getRol(),employee.getBirthday_date());
    }
    public static  Employee mapFromDTO(EmployeeDTO employeeDTO){
        return Employee.builder()
                .id(employeeDTO.id())
                .first_name(employeeDTO.first_name())
                .last_name(employeeDTO.last_name())
                .rol(employeeDTO.rol())
                .birthday_date(employeeDTO.birthday_date())
                .build();
    }
}
