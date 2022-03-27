package com.example.libraries;

import java.util.Objects;

public class Employee {
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeDepartment;
    private int employeePayment;

    public Employee ( String employeeFirstName, String employeeLastName, int employeeDepartment, int employeePayment ) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeDepartment = employeeDepartment;
        this.employeePayment = employeePayment;
    }

    public String getEmployeeFirstName () {
        return employeeFirstName;
    }
    public void setEmployeeFirstName ( String employeeFirstName ) {
        this.employeeFirstName = employeeFirstName;
    }
    public int getEmployeeDepartment () {
        return employeeDepartment;
    }
    public void setEmployeeDepartment ( int employeeDepartment ) {
        this.employeeDepartment = employeeDepartment;
    }
    public int getEmployeePayment () {
        return employeePayment;
    }
    public void setEmployeePayment ( int employeePayment ) {
        this.employeePayment = employeePayment;
    }
    public String getEmployeeLastName () {
        return employeeLastName;
    }
    public void setEmployeeLastName ( String employeeLastName ) {
        this.employeeLastName = employeeLastName;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeFirstName.equals(employee.employeeFirstName) && employeeLastName.equals(employee.employeeLastName);
    }

    @Override
    public int hashCode () {
        return Objects.hash(employeeFirstName, employeeLastName);
    }
}
