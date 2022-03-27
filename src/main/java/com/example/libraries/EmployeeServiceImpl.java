package com.example.libraries;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService2 {

    public Map<String, Employee> getEmployeeMap(){
        return employeeMap;
    }

    @Override
    public void removeEmployee ( String firstName, String lastName) {
        String oldEmployeeFullName = firstName + " " + lastName;
        boolean found = false;
        if(employeeMap.containsKey(oldEmployeeFullName)){
            found = true;
            employeeMap.remove(oldEmployeeFullName);
            System.out.println("Сотрудник " + oldEmployeeFullName + " удален");
        }
        if (!found){
            throw new EmployeeNotFound();
        }
    }
    @Override
    public void findEmployee ( String firstName, String lastName ) {
        String requestedEmployeeFullName = firstName + " " + lastName;
        boolean found = false;
        if (employeeMap.containsKey(requestedEmployeeFullName)) {
            found = true;
            System.out.println("Сотрудник " + requestedEmployeeFullName + " находится в базе данных");
        }
        if(!found){
            throw new EmployeeNotFound();
        }
    }

    public void addEmployee(String firstName, String lastName, int employeeDepartment, int employeePayment){
        Employee newEmployee = new Employee(firstName, lastName,employeeDepartment, employeePayment);
        String newEmployeeFullName = firstName + " " + lastName;
        if(employeeMap.containsKey(newEmployeeFullName)){
            throw new EmployeeAlreadyExists();
        }else{
            employeeMap.put(newEmployeeFullName,newEmployee);
            System.out.println("Сотрудник " + newEmployee.getEmployeeFirstName() + " "
                    + newEmployee.getEmployeeLastName() + " добавлен");
        }
    }

    public List<Employee> getAllEmployees (Map<String, Employee> employeeMap) {
        return employeeMap.values().stream()
                .collect(Collectors.toList());
    }
    public List<Employee> getAllDepartmentEmployees(Map<String, Employee> employeeMap, int employeeDepartment){
        return employeeMap.values().stream()
                .filter(employee-> employee.getEmployeeDepartment()==employeeDepartment)
                .collect(Collectors.toList());
    };
    public Employee getMaxSalaryEmployee (Map<String, Employee> employeeMap,int employeeDepartment){
        return employeeMap.values().stream()
                .filter(employee-> employee.getEmployeeDepartment()==employeeDepartment)
                .max(Comparator.comparing(Employee::getEmployeePayment))
                .get();
    }
    public Employee getMinSalaryEmployee(Map<String, Employee> employeeMap,int employeeDepartment){
        return employeeMap.values().stream()
                .filter(employee-> employee.getEmployeeDepartment()==employeeDepartment)
                .min(Comparator.comparing(Employee::getEmployeePayment))
                .get();
    }


}
