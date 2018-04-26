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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifyEmployeeTest {

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
    public void testRepositoryMock() {
        assertFalse(controller.getEmployeesList().isEmpty());
        assertEquals(6, controller.getEmployeesList().size());
    }

    @Test
    public void testModifyEmployeeSuccess() {
        this.restore_real();
        assertTrue(controller_real.modifyDidacticFunction(DidacticFunction.CONF, 0));
    }

    @Test
    public void testModifyEmployeeSuccess2() {
        this.restore_real2();
        assertTrue(controller_real.modifyDidacticFunction(DidacticFunction.CONF,  0));
    }

    private void restore_real2() {
        Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
        assertTrue(employeeValidator.isValid(newEmployee));
        controller_real.addEmployee(newEmployee);

        Employee newEmployee2 = new Employee("Raluca","1961105245020", DidacticFunction.ASISTENT, 3000.0);
        assertTrue(employeeValidator.isValid(newEmployee2));
        controller_real.addEmployee(newEmployee2);
    }

    @Test
    public void testModifyEmployeeFail() {
        assertFalse(controller_real.modifyDidacticFunction(DidacticFunction.CONF, 10));
    }

    @Test
    public void testModifyEmployeeFail2() {
        this.restore_real2();
        assertFalse(controller_real.modifyDidacticFunction(DidacticFunction.CONF, 10));
    }

    @Test
    public void testRepositoryReal() {
        assertTrue(controller_real.getEmployeesList().isEmpty());
        assertEquals(0, controller_real.getEmployeesList().size());
    }

    public void restore_real() {
        Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
        assertTrue(employeeValidator.isValid(newEmployee));
        controller_real.addEmployee(newEmployee);
    }

}
