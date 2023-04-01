package com.springboot.cruddemo.rest;

import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Optional<Employee> getEmployee(@PathVariable int employeeId){
        Optional<Employee> theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee not found - "+employeeId);
        }
        return  theEmployee;
    }
    @PostMapping("/employees")
    public Employee addEmployees(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return  dbEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployees(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return  dbEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Optional<Employee> tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found  - "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted Employee Id - "+employeeId;
    }
}
