package pro.sky.java.course2.hw25collections.department;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.java.course2.hw25collections.Employee;
import pro.sky.java.course2.hw25collections.EmployeeService;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.hw25collections.exceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getMaxSalaryByDepartment(Integer department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной зарплатой не найден"));
    }

    public Employee getMinSalaryByDepartment(Integer department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с минимальной зарплатой не найден"));
    }

    public Map<Integer, List<Employee>> getAllByDepartment(Integer department) {
        return employeeService.printAll().stream()
                .filter(employee -> department == null || employee.getDepartment() == department)
                .collect(groupingBy(Employee::getDepartment, toList()));
    }

    public Double sum(Integer department) {
        return employeeService.printAll().stream()
                .filter(employee -> department == null || employee.getDepartment() == department)
                .map(Employee::getSalary)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
