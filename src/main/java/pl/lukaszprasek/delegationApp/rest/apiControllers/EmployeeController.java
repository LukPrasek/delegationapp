package pl.lukaszprasek.delegationApp.rest.apiControllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lukaszprasek.delegationApp.application.EmployeeManager;
import pl.lukaszprasek.delegationApp.common.dto.EmployeeDto;
import pl.lukaszprasek.delegationApp.common.mappers.EmployeeMapper;
import pl.lukaszprasek.delegationApp.common.requestMapper.RequestEmployeeToDtoMapper;
import pl.lukaszprasek.delegationApp.rest.request.CreateEmployeeRequest;
import pl.lukaszprasek.delegationApp.rest.response.EmployeeRestModel;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/v1")
@Api("Show message api")
public class EmployeeController {

    private final EmployeeManager employeeManager;
    private final EmployeeMapper employeeMapper;
    private final RequestEmployeeToDtoMapper requestEmployeeToDtoMapper;

    @Autowired
    public EmployeeController(EmployeeManager employeeManager, EmployeeMapper employeeMapper,
                              RequestEmployeeToDtoMapper requestEmployeeToDtoMapper) {
        this.employeeManager = employeeManager;
        this.employeeMapper = employeeMapper;
        this.requestEmployeeToDtoMapper = requestEmployeeToDtoMapper;
    }

    @ApiOperation(value = "Get all employees")
    @GetMapping(path = "/employees", produces = "application/json")
    public List<EmployeeRestModel> getEmployees() {
        return employeeMapper.mapList(employeeManager.getAllEmployees());

    }

    @ApiOperation(value = "Get one employee")
    @GetMapping(path = "/employee/{id}", produces = "application/json")
    public EmployeeRestModel getEmployeeById(@PathVariable("id") Long id) {
        return (EmployeeRestModel) employeeMapper.map(employeeManager.showEmployee(id));
    }

    @ApiOperation(value = "Create employee")
    @PostMapping(path = "/employee/create", produces = "application/json")
    public ResponseEntity<EmployeeRestModel> createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) {
        EmployeeDto responseEmployeeDTO = employeeManager.createEmployee(
                requestEmployeeToDtoMapper.mapCreateRequestToDTO(createEmployeeRequest));
        return new ResponseEntity<>((EmployeeRestModel) employeeMapper.map(responseEmployeeDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete employee")
    @DeleteMapping(path = "/employee/delete/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Long> deleteEmployee(@PathVariable("id") Long id) {
        employeeManager.deleteEmployee(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
