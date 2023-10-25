package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.EmployeeService;

/**
 * Контроллер для работы
 */
@RestController
@RequestMapping("/admin/employees")
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/all")
    public List<EmployeeFullInfo> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
