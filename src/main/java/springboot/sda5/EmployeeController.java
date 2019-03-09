package springboot.sda5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/sda")

@Controller
public class EmployeeController {
    private Employee foundEmployee;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping
    public String glowna(){
        return "index";
    }
            @RequestMapping("/employee-add")
            public String formularz(@ModelAttribute("form") @Valid EmployeeAddDTO form, BindingResult result) {
                // if(request.getMethod().equalsIgnoreCase("post") && !result.hasErrors())
                if (result.hasErrors()) {
                    //formularz nie jest uzupełniony prawidłowo
                    return "employee-add"; }
                else {
                    //formularz wypełniony prawidłowo

                    Employee employee = new Employee();
                    employee.setFirstName(form.getImie());
                    employee.setLastName(form.getNazwisko());
                    employee.setSalary(new BigDecimal(form.getZarobki()));
                    employeeRepository.save(employee);
                    return "redirect:/sda/employee-all";
                }
            }

            @GetMapping("/employee-all")
            String getAllEmployees(Model model) {

                List<Employee> pracownik = (List<Employee>)employeeRepository.findAll();
                String name= "";

                for (Employee e : pracownik) {
                    name += e.toString()+"\n";
                }
                model.addAttribute("name", name);
                return "hello";

            }

            @GetMapping("/employees")
            String getEmployees(@RequestParam(defaultValue = "Jan") String imie, Model model){
                List<Employee> pracownik = employeeRepository.findByFirstName(imie);
                String name= "";

                for (Employee e : pracownik) {
                    name += e.toString();
                }
                model.addAttribute("name", name);
                return "hello";
            }

            @PostMapping(value = "/employees")
            void addEmployee(@RequestParam(defaultValue = "brak") String imie,
                    @RequestParam(defaultValue = "brak") String nazwisko,
            @RequestParam(defaultValue = "0.0") float zarobki){
                Employee employee = new Employee();
                employee.setFirstName(imie);
                employee.setLastName(nazwisko);
                employee.setSalary(new BigDecimal(zarobki));
                employeeRepository.save(employee);
            }

            @PatchMapping("/employees")
            String changeEmployee(@RequestBody Employee employee, @RequestParam(defaultValue = "brak") String imie,
                    @RequestParam(defaultValue = "brak") String nazwisko,
                    @RequestParam(defaultValue = "-1") BigDecimal zarobki){
                Employee myEmployee = employeeRepository.findByFirstNameAndLastNameAndSalary(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary());
                //return myEmployee.getId();

                if(!imie.equals("brak")){
                    myEmployee.setFirstName(imie);
                }
                if(!nazwisko.equals("brak")){
                    myEmployee.setLastName(nazwisko);
                }
                if(!zarobki.equals(new BigDecimal(-1))){
                    myEmployee.setSalary(zarobki);
                }
                employeeRepository.save(myEmployee);

                return "Zmodyfikowano: "+myEmployee;
            }
            @RequestMapping("/employee-find")
            public String findEmployee(@ModelAttribute("form") @Valid EmployeeFindDTO form, BindingResult result, Model model){
                if (result.hasErrors()) {
                    System.out.println("Error!");
                    return "employee-find"; }
                else {
                    List<Employee> employeeList = (List<Employee>) employeeRepository.findByFirstNameAndLastName(form.getImie(), form.getNazwisko());
                    if(employeeList.size() > 0){
                       foundEmployee = employeeList.get(0);
                       model.addAttribute("imie", form.getImie());
                       model.addAttribute("nazwisko", form.getNazwisko());
                       model.addAttribute("zarobki", form.getZarobki());
                        return "redirect:/sda/employee-update";
                    }
                    System.out.println("Return!");
                    return "employee-find";
                }
            }

            @RequestMapping("/employee-update")
            public String updateEmployee(Model model){
                return "employee-update";
            }

        }
