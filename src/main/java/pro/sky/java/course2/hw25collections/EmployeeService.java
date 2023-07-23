package pro.sky.java.course2.hw25collections;

import java.util.Collection;

public interface EmployeeService {
    public Employee addEmployee(String firstName, String lastName, int department, double salary);
    public Employee removeEmployee(String firstName, String lastName, int department, double salary);
    public Employee findEmployee(String firstName, String lastName, int department, double salary);
    public Collection<Employee> printAll();

}
