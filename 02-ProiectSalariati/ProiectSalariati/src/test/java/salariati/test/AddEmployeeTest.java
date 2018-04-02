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

import static org.junit.Assert.*;

public class AddEmployeeTest {

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
	public void testAddNewEmployee() {
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000.0);
		assertTrue(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee);
		assertEquals(7, controller.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
	}

	@Test
	public void testAddNewEmployeeFail_InvalidName() {
		Employee newEmployee = new Employee("", "1910509055057", DidacticFunction.ASISTENT, 3000.0);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());

		newEmployee.setLastName("ca");
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());
	}

	@Test
	public void testAddNewEmployeeFail_InvalidCNP() {
		Employee newEmployee = new Employee("Casian", "", DidacticFunction.ASISTENT, 3000.0);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());

		newEmployee.setCnp("sadasdas");
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());


		newEmployee.setCnp("1231212124432");
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());

		newEmployee.setCnp("123121212443");
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());
	}


	@Test
	public void testAddNewEmployeeFail_InvalidSalary() {
		Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.CONF, 0.0);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());

		newEmployee.setSalary(-0.1);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());

		newEmployee.setSalary(-131200000000000000000000000000000000000000000000000000000000000000000000000031.0);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(6, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(6, controller.getEmployeesList().size());
	}


	@Test
	public void testRepositoryReal() {
		assertTrue(controller_real.getEmployeesList().isEmpty());
		assertEquals(0, controller_real.getEmployeesList().size());
	}

	@Test
	public void testAddNewEmployee_real() {
		Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
		assertTrue(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller_real.addEmployee(newEmployee);
		assertEquals(1, controller_real.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller_real.getEmployeesList().get(controller_real.getEmployeesList().size() - 1)));
	}

    public void restore_real() {
        Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.ASISTENT, 3000.0);
        assertTrue(employeeValidator.isValid(newEmployee));
        controller_real.addEmployee(newEmployee);
    }

	@Test
	public void testAddNewEmployeeFail_InvalidName_real() {
		Employee newEmployee = new Employee("", "1910509055057", DidacticFunction.ASISTENT, 3000.0);
		assertFalse(employeeValidator.isValid(newEmployee));
		assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());

		newEmployee.setLastName("ca");
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());
	}

	@Test
	public void testAddNewEmployeeFail_InvalidCNP_real() {
		Employee newEmployee = new Employee("Casian", "", DidacticFunction.ASISTENT, 3000.0);
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());

		newEmployee.setCnp("sadasdas");
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());


		newEmployee.setCnp("1231212124432");
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());

		newEmployee.setCnp("123121212443");
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());
	}


	@Test
	public void testAddNewEmployeeFail_InvalidSalary_real() {
		Employee newEmployee = new Employee("Casian", "1961207015563", DidacticFunction.CONF, 0.0);
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());

		newEmployee.setSalary(-0.1);
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());

		newEmployee.setSalary(-131231.0);
		assertFalse(employeeValidator.isValid(newEmployee));
        assertEquals(0, controller_real.getEmployeesList().size());
		controller.addEmployee(newEmployee);
        assertEquals(0, controller_real.getEmployeesList().size());
	}
}
