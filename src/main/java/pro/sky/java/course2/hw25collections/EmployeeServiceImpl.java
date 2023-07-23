package pro.sky.java.course2.hw25collections;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.hw25collections.exceptions.BadParamsException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private int size = 10;


    public EmployeeServiceImpl() {
        employees.add(new Employee("Иван_1.1", "Иванов_1.1", 1, 10_000));
        employees.add(new Employee("Иван_1.2", "Иванов_1.2", 1, 11_000));
        employees.add(new Employee("Иван_2.1", "Иванов_2.1", 2, 8_000));
        employees.add(new Employee("Иван_2.2", "Иванов_2.2", 2, 7_000));
        employees.add(new Employee("Иван_3.1", "Иванов_3.1", 3, 13_000));
        employees.add(new Employee("Иван_3.2", "Иванов_3.2", 3, 14_000));
    }




    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (firstName == "" || lastName == "") {
            throw new BadParamsException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("этот сотрудник уже существует");
        }
        if (employees.size() > size) {
            throw new EmployeeStorageIsFullException("лимит сотрудников превышен");
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        boolean removeResult = employees.remove(employee);
        if (removeResult) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
        }
    }

    public Employee findEmployee(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("этот сотрудник отсутствует");
    }

    public Collection<Employee> printAll() {
        return new ArrayList<>(employees);
    }
}
