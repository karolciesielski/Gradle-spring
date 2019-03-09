package springboot.sda5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(employeeGenerator.generate());
        }
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(System.out::println);
    }


    @PostConstruct
    public void runAtStart1()  {
        generateManyEmployees();
//        List<Employee> sorted = employeeRepository.findByFirstNameIgnoreCase("Piotr");
        Page<Employee> piotrPage = employeeRepository.findByFirstName("Piotr", new PageRequest(1, 5));
        piotrPage.getContent();
        for (Employee emp : piotrPage.getContent()) {
            System.out.println(emp);
        }
        System.out.println("Wszystkich stron bylo: "+piotrPage.getTotalPages());
        System.out.println("Wszystkich elementów bylo: "+piotrPage.getTotalElements());
//        printAll(employeeRepository.findByFirstNameIgnoreCase("Piotr"));
//        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
//        List<Employee> employees = employeeRepository.findBySalaryGreaterThan(new BigDecimal(3000.0));
//        for (Employee e : employees) {
//            System.out.println(e);
//        }
    }

    public void runAtStart2() {
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Nowak");
        employee.setSalary(new BigDecimal("3500.0"));

        employeeRepository.save(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Piotr");
        employee2.setLastName("Kwiatkowski");
        employee2.setSalary(new BigDecimal("3700.0"));

        employeeRepository.save(employee2);

        List<Employee> jans = employeeRepository.findByFirstNameIgnoreCase("Jan");
        Employee jan = jans.iterator().next();
//        List<Employee> sortByFirstName = employeeRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "firstName")));


//        List<Employee> sortByFirstNameAndLastName = employeeRepository.findAll(
//                new Sort(new Sort.Order(Sort.Direction.ASC, "firstName")
//                        .new Sort.Order(Sort.Direction.ASC, "lastName")
//                ));



        System.out.println("Janek: "+jan);
    }
}
