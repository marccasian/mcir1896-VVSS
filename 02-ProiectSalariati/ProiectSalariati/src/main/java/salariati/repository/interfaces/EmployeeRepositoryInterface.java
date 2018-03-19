package salariati.repository.interfaces;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;

import java.util.List;

public interface EmployeeRepositoryInterface {
	
	boolean addEmployee(Employee employee);
	List<Employee> getEmployeeList();
	List<Employee> getEmployeeByCriteria();
	void modifyEmployeeDidacticFunction(DidacticFunction d, int i);

}

