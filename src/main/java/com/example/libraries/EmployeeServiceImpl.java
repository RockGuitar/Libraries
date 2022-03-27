package com.example.libraries;

import EmployeeClass.Employee;
import EmployeeErrors.EmployeeAlreadyExists;
import EmployeeErrors.EmployeeNotFound;
import EmployeeErrors.EmployeeWrongName;
import EmployeeServices.EmployeeService2;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isAlpha(firstName) & StringUtils.isAlpha(lastName)) {
            String oldEmployeeFullName = StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
            boolean found = false;
            if(employeeMap.containsKey(oldEmployeeFullName)){
                found = true;
                employeeMap.remove(oldEmployeeFullName);
                System.out.println("Сотрудник " + oldEmployeeFullName + " удален");
            }
            if (!found){
                throw new EmployeeNotFound();
            }
        }else{
            throw new EmployeeWrongName();
        }
    }
    @Override
    public void findEmployee ( String firstName, String lastName ) {
        if (StringUtils.isAlpha(firstName) & StringUtils.isAlpha(lastName)) {
            String requestedEmployeeFullName = StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
            boolean found = false;
            if (employeeMap.containsKey(requestedEmployeeFullName)) {
                found = true;
                System.out.println("Сотрудник " + requestedEmployeeFullName + " находится в базе данных");
            }
            if (!found) {
                throw new EmployeeNotFound();
            }
        }else{
            throw new EmployeeWrongName();
        }
    }

    public void addEmployee(String firstName, String lastName, int employeeDepartment, int employeePayment){
        if(StringUtils.isAlpha(firstName) & StringUtils.isAlpha(lastName)){
            Employee newEmployee = new Employee(
                    StringUtils.capitalize(firstName),
                    StringUtils.capitalize(lastName),
                    employeeDepartment,
                    employeePayment);
            String newEmployeeFullName = StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
            if(employeeMap.containsKey(newEmployeeFullName)){
                throw new EmployeeAlreadyExists();
            }else{
                employeeMap.put(newEmployeeFullName,newEmployee);
                System.out.println("Сотрудник " + newEmployee.getEmployeeFirstName() + " "
                        + newEmployee.getEmployeeLastName() + " добавлен");
            }
        }else{
            throw new EmployeeWrongName();
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
