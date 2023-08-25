package pro.sky.java.course2.hw25collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.hw25collections.exceptions.*;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private int size = 10;

//    public EmployeeServiceImpl(){
//        employees.add(new Employee("Иван1", "Иванов1", 1, 10_000));
//        employees.add(new Employee("Иван2", "Иванов2", 1, 11_000));
//        employees.add(new Employee("Иван3", "Иванов3", 2, 12_000));
//        employees.add(new Employee("Иван4", "Иванов4", 2, 13_000));
//    }


    public Employee addEmployee(String firstName, String lastName, int department, double salary) {

        validateFullName(firstName, lastName);
        validateIsAlphaFullName(firstName, lastName);

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
    //http://localhost:8080/employee/add?firstName=Ivan&lastName=Ivanov&department=1&salary=15000

    private void validateFullName(String firstName, String lastName) {
        String capitalizeFirstName = StringUtils.capitalize(firstName);
        if (!firstName.equals(capitalizeFirstName)) {
            throw new BadRequestException("имя начинается не с заглавной буквы");
        }

        String capitalazeLastName = StringUtils.capitalize(lastName);
        if (!lastName.equals(capitalazeLastName)) {
            throw new BadRequestException("фамилия начинается не с заглавной буквы");
        }
    }

    private void validateIsAlphaFullName(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new BadRequestException("Имя должно содержать только буквы");
        }
        if (!StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Фамилия должно содержать только буквы");
        }

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
