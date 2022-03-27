package com.example.libraries;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class Controller {
    private final EmployeeService2 employeeService2;

    public Controller ( EmployeeService2 employeeService ) {
        this.employeeService2 = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee( @RequestParam("name") String firstName,
                                 @RequestParam("surname") String lastName,
                                 @RequestParam("id") int employeeDepartment,
                                 @RequestParam("pay") int employeePayment){
        Employee employee = new Employee(firstName, lastName,employeeDepartment,employeePayment);
        employeeService2.addEmployee(firstName,lastName,employeeDepartment,employeePayment);
        return employee;
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("name") String firstName,
                                 @RequestParam("surname") String lastName){
        String fullName = firstName +" "+ lastName;
        employeeService2.findEmployee(firstName,lastName);
        return employeeService2.getEmployeeMap().get(fullName);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("name") String firstName,
                                   @RequestParam("surname") String lastName) {
        String fullName = firstName +" "+ lastName;
        employeeService2.removeEmployee(firstName,lastName);
        return employeeService2.getEmployeeMap().get(fullName);
    }
    @GetMapping("/info")
    public Map<String, Employee> employeeInformation(){
        return employeeService2.getEmployeeMap();
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryEmployee(@RequestParam("id") int employeeDepartment){
        return employeeService2.getMaxSalaryEmployee(employeeService2.getEmployeeMap(),employeeDepartment);
    }
    @GetMapping("/min-salary")
    public Employee getMinSalaryEmployee(@RequestParam("id") int employeeDepartment){
        return employeeService2.getMinSalaryEmployee(employeeService2.getEmployeeMap(),employeeDepartment);
    }
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService2.getAllEmployees(employeeService2.getEmployeeMap());
    }
    @GetMapping("/all")
    public List<Employee> getAllDepartmentEmployees(@RequestParam("id") int employeeDepartment){
        return employeeService2.getAllDepartmentEmployees(employeeService2.getEmployeeMap(),employeeDepartment);
    }
}
