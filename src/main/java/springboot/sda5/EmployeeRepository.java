package springboot.sda5;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByFirstNameIgnoreCase(String firstName);
    Employee findByFirstNameAndLastNameAndSalary(String firstName, String lastName, BigDecimal salary);
    Page<Employee> findByFirstName(String firstName, Pageable pageable);
    List<Employee> findTop10ByFirstName(String firstName);
    List<Employee> findBySalaryGreaterThan(BigDecimal salary);

    List<Employee> findByFirstNameAndLastName(String imie, String nazwisko);
}
