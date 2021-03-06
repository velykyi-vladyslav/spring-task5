package velykyi.vladyslav.task4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import velykyi.vladyslav.task4.controller.assembler.EmployeeAssembler;
import velykyi.vladyslav.task4.controller.model.EmployeeModel;
import velykyi.vladyslav.task4.dto.EmployeeDto;
import velykyi.vladyslav.task4.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeAssembler employeeAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{login}")
    public EmployeeModel getEmployee(@PathVariable String login) {
        log.info("Get employee by login: " + login);
        EmployeeDto employeeDto = employeeService.getEmployee(login);
        return employeeAssembler.toModel(employeeDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeModel createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        log.info("Create employee: {}", employeeDto);
        EmployeeDto employee = employeeService.createEmployee(employeeDto);
        return employeeAssembler.toModel(employee);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{login}")
    public EmployeeModel updateEmployee(@PathVariable String login, @RequestBody EmployeeDto employeeDto) {
        log.info("Update employee: {}", employeeDto + " by login: " + login);
        EmployeeDto employee = employeeService.updateEmployee(login, employeeDto);
        return employeeAssembler.toModel(employee);
    }

    //demonstration @Validated
    @DeleteMapping(value = "/{login}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("login") @Size(min = 1, max = 2) String login) {
        log.info("Delete employee by login: " + login);
        employeeService.deleteEmployee(login);
        return ResponseEntity.noContent().build();
    }
}
