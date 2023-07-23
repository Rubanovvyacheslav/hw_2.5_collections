package pro.sky.java.course2.hw25collections.department;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.hw25collections.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryByDepartment(@RequestParam Integer department) {
        return departmentService.getMaxSalaryByDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryByDepartment(@RequestParam Integer department) {
        return departmentService.getMinSalaryByDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllByDepartment(@RequestParam(required = false) Integer department) {
        return departmentService.getAllByDepartment(department);
    }

}
