package salariati.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SortEmployeeTest {
        private EmployeeRepositoryInterface employeeRepository;
        private EmployeeRepositoryInterface employeeRepository_real;
        private EmployeeController controller;
        private EmployeeController controller_real;
        private EmployeeValidator employeeValidator;
        private Integer size;
        private final String employeeDBFile = "D:\\GIT\\An3\\Sem2\\VVSS\\02-ProiectSalariati\\ProiectSalariati\\src\\main\\java\\salariati\\repository\\employees.txt";

        @Before
        public void setUp() throws FileNotFoundException {
            employeeRepository = new EmployeeMock();
            employeeRepository_real = new EmployeeImpl();
            controller         = new EmployeeController(employeeRepository);
            controller_real         = new EmployeeController(employeeRepository_real);
            employeeValidator  = new EmployeeValidator();
            PrintWriter writer = new PrintWriter(employeeDBFile);
            writer.print("");
            writer.close();
        }

        @After
        public void tearDown(){
            this.restore_real();
        }


        @Test
        public void testSortEmployeeFail() {
            List<Employee> list = controller_real.getEmployeesListOrdered();
            double sal = -1.0;
            for (Employee e: list){
                assertTrue(sal <= e.getSalary());
                sal = e.getSalary();
            }
        }

        @Test
        public void testSortEmployeeSuccess() {
            Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
            assertTrue(employeeValidator.isValid(newEmployee));
            controller_real.addEmployee(newEmployee);

            Employee newEmployee2 = new Employee("Raluca","1961105245020", DidacticFunction.ASISTENT, 3000.0);
            assertTrue(employeeValidator.isValid(newEmployee2));
            controller_real.addEmployee(newEmployee2);
            List<Employee> list = controller_real.getEmployeesListOrdered();
            double sal = -1.0;
            for (Employee e: list){
                assertTrue(sal <= e.getSalary());
                sal = e.getSalary();
            }
        }

        private void restore_real() {
            Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
            assertTrue(employeeValidator.isValid(newEmployee));
            controller_real.addEmployee(newEmployee);
        }

    }
