package sn.ksb.immo.ksbimmo.application.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sn.ksb.immo.ksbimmo.application.dtos.EmployeeDto;
import sn.ksb.immo.ksbimmo.application.models.Employee;
import sn.ksb.immo.ksbimmo.application.services.EmployeeService;

import java.util.List;

@RestController
@Slf4j
@OpenAPIDefinition(tags = {@Tag(name = "Employé", description = "Employé API")})
@RequestMapping("/api/employee")
public class EmployeController {

    private final EmployeeService service;

    public EmployeController(EmployeeService service) {
        this.service = service;
    }

    //get All employees
    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        //log the entry of the method
        log.info("Entrée dans la méthode getAllEmployee du controller EmployeController");
        //initialize a list of employee
        List<Employee> employees = null;
        //try to get all employees from the service
        try {
            employees = service.getAllEmployees();
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la récupération des employés dans la base de données");
        }
        //if the list is empty
        if (employees == null || employees.isEmpty()) {
            //log the error
            log.error("Aucun employé n'a été trouvé dans la base de données");
        }
        //log the exit of the method
        log.info("Sortie de la méthode getAllEmployee du controller EmployeController");
        //return the list
        return employees;
    }

    //get all employees of a specific agency
    @GetMapping("/agency/{id}")
    public List<Employee> getAllEmployeeByAgency(@PathVariable String id) {
        //log the entry of the method
        log.info("Entrée dans la méthode getAllEmployeeByAgency du controller EmployeController");
        //initialize a list of employee
        List<Employee> employees = null;
        //log the id parameter
        log.info("Paramètre id : " + id);
        //try to get all employees from the service
        try {
            employees = service.getEmployeesByAgence(id);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la récupération des employés dans la base de données");
        }
        //if the list is empty
        if (employees == null || employees.isEmpty()) {
            //log the error
            log.error("Aucun employé n'a été trouvé dans la base de données");
        }
        //log the exit of the method
        log.info("Sortie de la méthode getAllEmployeeByAgency du controller EmployeController");
        //return the list
        return employees;
    }

    //get employee by matricule
    @GetMapping("/{matricule}")
    public Employee getEmployeeByMatricule(@PathVariable String matricule) {
        //log the entry of the method
        log.info("Entrée dans la méthode getEmployeeByMatricule du controller EmployeController");
        //initialize an employee
        Employee employee = null;
        //log the matricule parameter
        log.info("Paramètre matricule : " + matricule);
        //try to get the employee from the service
        try {
            employee = service.getEmployeeByMatricule(matricule);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la récupération de l'employé dans la base de données");
        }
        //if the employee is null
        if (employee == null) {
            //log the error
            log.error("Aucun employé n'a été trouvé dans la base de données");
        }
        //log the exit of the method
        log.info("Sortie de la méthode getEmployeeByMatricule du controller EmployeController");
        //return the employee
        return employee;
    }

    //create an employee
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDto dto) {
        //log the entry of the method
        log.info("Entrée dans la méthode createEmployee du controller EmployeController");
        Employee employee = null;
        //try to create the employee
        try {
            employee = service.addEmployee(dto);
        } catch (Exception e) {
            //log the error
            log.error("Erreur lors de la création de l'employé dans la base de données");
        }
        //if the employee is null
        if (employee == null) {
            //log the error
            log.error("Aucun employé n'a été trouvé dans la base de données");
        }
        //log the exit of the method
        log.info("Sortie de la méthode createEmployee du controller EmployeController");
        //return the employee
        return employee;
    }
}
