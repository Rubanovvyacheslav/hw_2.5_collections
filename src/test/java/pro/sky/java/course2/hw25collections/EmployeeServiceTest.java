package pro.sky.java.course2.hw25collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    Employee employee1 = new Employee("Петр", "Петров", 1, 8_000);
    Employee employee2 = new Employee("Сергей", "Сергеев", 1, 8_000);
    Employee employee3 = new Employee("Анатолий", "Анатольев", 1, 8_000);

    @Test
    public void printAll() {

        employeeService.addEmployee("Петр", "Петров", 1, 8_000);
        employeeService.addEmployee("Сергей", "Сергеев", 1, 8_000);
        employeeService.addEmployee("Анатолий", "Анатольев", 1, 8_000);

        List<Employee> expected = (List<Employee>) employeeService.printAll();
        List<Employee> actual = new ArrayList<>();
        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);
        assertEquals(expected, actual);
    }

    @Test
    public void printAllNotNull() {
        List<Employee> expected = (List<Employee>) employeeService.printAll();
        assertNotNull(expected);
    }

    @Test
    public void addEmployee() {
        employeeService.addEmployee("Петр", "Петров", 1, 8_000);
        List<Employee> actual = (List<Employee>) employeeService.printAll();
        List<Employee> expected = new ArrayList<>();
        actual.add(employee1);
        assertEquals(expected, actual);
    }

    @Test
    public void addEmployeeAlreadyAdded() {
        employeeService.addEmployee("Петр", "Петров", 1, 8_000);
        String expectedErrorMessage = "этот сотрудник уже существует";
        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("Петр", "Петров", 1, 8_000));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void removeEmployee() {
        employeeService.addEmployee("Петр", "Петров", 1, 8_000);
        employeeService.addEmployee("Сергей", "Сергеев", 1, 8_000);
        employeeService.removeEmployee("Петр", "Петров", 1, 8_000);
        List<Employee> actual = (List<Employee>) employeeService.printAll();
        List<Employee> expected = new ArrayList<>();
        expected.add(employee2);
        assertEquals(expected, actual);
    }

    @Test
    public void removeEmployeeAlreadyRemoved() {
        employeeService.addEmployee("Сергей", "Сергеев", 1, 8_000);
        employeeService.removeEmployee("Сергей", "Сергеев", 1, 8_000);
        String expectedErrorMessage = "Сотрудник не удален - не был найден в базе";
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Сергей", "Сергеев", 1, 8_000));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void findEmployee() {
        employeeService.addEmployee("Петр", "Петров", 1, 8_000);
        employeeService.addEmployee("Сергей", "Сергеев", 1, 8_000);
        employeeService.removeEmployee("Сергей", "Сергеев", 1, 8_000);
        List<Employee> actual = (List<Employee>) employeeService.printAll();
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        assertEquals(expected, actual);
    }

    @Test
    public void findEmployeeIfNotExist() {
        employeeService.addEmployee("Сергей", "Сергеев", 1, 8_000);
        employeeService.removeEmployee("Сергей", "Сергеев", 1, 8_000);
        String expectedErrorMessage = "этот сотрудник отсутствует";
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Сергей", "Сергеев", 1, 8_000));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}

