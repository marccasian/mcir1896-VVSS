package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.validator.EmployeeValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployeeFieldsTest {

	private EmployeeValidator employeeValidator;
	private Employee employee;
	
	@Before
	public void setUp() {
		employeeValidator = new EmployeeValidator();
		employee = new Employee("Ardelean", "1234567891234", DidacticFunction.ASISTENT, 1234.0);
	}
	
	@Test
	public void testValidLastName() {
		employee.setLastName("ValidLastName");
		assertTrue(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testInvalidLastName() {
		employee.setLastName("Invalid#LastName");
		assertFalse(employeeValidator.isValid(employee));
		employee.setLastName("Invalid!@1");
		assertFalse(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testValidCNP() {
		assertTrue(employeeValidator.isValid(employee));
		employee.setCnp("1910509055057");
		assertTrue(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testInvalidCNP() {
		employee.setCnp("123456789123");
		assertFalse(employeeValidator.isValid(employee));
		employee.setCnp("12345678912345");
		assertFalse(employeeValidator.isValid(employee));
		employee.setCnp("123asd456yuio");
		assertFalse(employeeValidator.isValid(employee));
		employee.setCnp("ty1234s,.t");
		assertFalse(employeeValidator.isValid(employee));
		employee.setCnp("");
		assertFalse(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testValidSalary() {
		assertTrue(employeeValidator.isValid(employee));
		employee.setSalary(1500.0);
		assertTrue(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testInvalidSalary() {
		employee.setSalary(0.0);
		assertFalse(employeeValidator.isValid(employee));
		employee.setSalary(-123.0);
		assertFalse(employeeValidator.isValid(employee));
		employee.setSalary(-0.5);
		assertFalse(employeeValidator.isValid(employee));
		employee.setSalary(-213123123.0);
		assertFalse(employeeValidator.isValid(employee));
	}

}
