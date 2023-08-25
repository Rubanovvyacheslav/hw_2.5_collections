package pro.sky.java.course2.hw25collections;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    //http://localhost:8080/employee/add?firstName=Ivan&lastName=Ivanov&department=1&salary=15000
    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping
    public Collection<Employee> printAll() {
        return employeeService.printAll();
    }

}
