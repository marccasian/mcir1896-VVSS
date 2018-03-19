package salariati.repository.mock;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMock implements EmployeeRepositoryInterface {

	private List<Employee> employeeList;
	private EmployeeValidator employeeValidator;
	
	public EmployeeMock() {
		
		employeeValidator = new EmployeeValidator();
		employeeList = new ArrayList<Employee>();
		
		Employee Ionel   = new Employee("Pacuraru", "1961207015563", DidacticFunction.ASISTENT, 2500.0);
		Employee Mihai   = new Employee("Dumitrescu", "1961207015563", DidacticFunction.LECTURER, 2500.0);
		Employee Ionela  = new Employee("Ionescu", "1961207015563", DidacticFunction.LECTURER, 2500.0);
		Employee Mihaela = new Employee("Pacuraru", "1961207015563", DidacticFunction.ASISTENT, 2500.0);
		Employee Vasile  = new Employee("Georgescu", "1961207015563", DidacticFunction.TEACHER,  2500.0);
		Employee Marin   = new Employee("Puscas", "1961207015563", DidacticFunction.TEACHER,  2500.0);
		
		employeeList.add( Ionel );
		employeeList.add( Mihai );
		employeeList.add( Ionela );
		employeeList.add( Mihaela );
		employeeList.add( Vasile );
		employeeList.add( Marin );
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		if ( employeeValidator.isValid(employee)) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByCriteria() {
		return null;
	}

	@Override
	public void modifyEmployeeDidacticFunction(DidacticFunction d, int i) {

	}

}
