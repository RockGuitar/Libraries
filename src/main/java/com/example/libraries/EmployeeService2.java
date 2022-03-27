package com.example.libraries;


import java.util.List;
import java.util.Map;

public interface EmployeeService2 extends EmployeeService{
    Employee getMaxSalaryEmployee (Map<String, Employee> employeeMap,int employeeDepartment);
    Employee getMinSalaryEmployee(Map<String, Employee> employeeMap,int employeeDepartment);
    List<Employee> getAllEmployees( Map<String, Employee> employeeMap);
    List<Employee> getAllDepartmentEmployees(Map<String, Employee> employeeMap, int employeeDepartment);
}
