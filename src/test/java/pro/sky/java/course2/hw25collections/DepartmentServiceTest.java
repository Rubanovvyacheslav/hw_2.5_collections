package pro.sky.java.course2.hw25collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.hw25collections.department.DepartmentService;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeNotFoundException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    EmployeeService employeeService = mock(EmployeeService.class);
    DepartmentService departmentService = new DepartmentService(employeeService);

    List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Петр", "Петров", 1, 8_000),
            new Employee("Сергей", "Сергеев", 1, 7_000),
            new Employee("Анатолий", "Анатольев", 2, 9_000),
            new Employee("Федор", "Федоров", 2, 10_000)));

    @BeforeEach
    public void employeeService() {
        when(employeeService.printAll()).thenReturn(employees);
    }

    @Test
    public void getMaxSalaryByDepartment() {
        Employee actual = departmentService.getMaxSalaryByDepartment(1);
        Employee expected = employees.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void getMaxSalaryByDepartmentExc() {
        String expectedErrorMessage = "Сотрудник с максимальной зарплатой не найден";
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMaxSalaryByDepartment(3));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void getMinSalaryByDepartment() {
        Employee actual = departmentService.getMinSalaryByDepartment(2);
        Employee expected = employees.get(2);
        assertEquals(expected, actual);
    }

    @Test
    public void getMinSalaryByDepartmentExc() {
        String expectedErrorMessage = "Сотрудник с минимальной зарплатой не найден";
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> departmentService.getMinSalaryByDepartment(3));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void getAllByDepartment() {
        assertEquals(departmentService.getAllByDepartment(1), employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == 1)
                .collect(groupingBy(Employee::getDepartment, toList())));
    }

    @Test
    public void sum() {
        Double actual = departmentService.sum(2);
        Double expected = 19_000.0;
        assertEquals(expected, actual);
    }

}
