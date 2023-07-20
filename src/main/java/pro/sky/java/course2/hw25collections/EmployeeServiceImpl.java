package pro.sky.java.course2.hw25collections;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.hw25collections.exceptions.BadParamsException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;
    private int size = 2;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(size);
    }

    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (firstName == "" || lastName == "") {
            throw new BadParamsException();
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("этот сотрудник уже существует");
        }
        if (employees.size() > size) {
            throw new EmployeeStorageIsFullException("лимит сотрудников превышен");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("этот сотрудник отсутствует");
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("этот сотрудник отсутствует");
    }

    public Collection<Employee> printAll() {
        return new ArrayList<>(employees.values());
    }
}
