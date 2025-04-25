package com.example.friendface;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final EmployeeOfTheMonthService employeeOfTheMonthService;

    public GreetingController(EmployeeOfTheMonthService employeeOfTheMonthService) {
        this.employeeOfTheMonthService = employeeOfTheMonthService;
    }
    @GetMapping("/{locale}")
    public String localized(@PathVariable String locale) {
        if (locale.equals("fr")) {
            return "frogs";
        }
        return "hello";
    }
    @GetMapping("/employee")
    public Employee employeeOfTheMonth() {
        return employeeOfTheMonthService.computeEmployeeOfTheMonth();
    }
    @GetMapping("/welcome")
    public String welcome(@RequestBody Employee employee) {
        return String.format("Welcome %s", employee.getName());
    }

    public static class EmployeeOfTheMonthService {
        private final boolean formatNamesInUpperCase;

        public EmployeeOfTheMonthService(boolean formatNamesInUpperCase) {
            this.formatNamesInUpperCase = formatNamesInUpperCase;
        }

        public Employee computeEmployeeOfTheMonth() {
            final var employee = new Employee();
            final var nameNominal = System.getProperty("user.name");
            final var nameFormatted = formatNamesInUpperCase
                    ? nameNominal.toUpperCase()
                    : nameNominal;
            employee.setName(nameFormatted);
            return employee;
        }
    }
}