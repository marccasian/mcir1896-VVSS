package salariati.controller;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;

import java.util.List;

public class EmployeeController {
	
	private EmployeeRepositoryInterface employeeRepository;

	public EmployeeController(){
		this.employeeRepository = new EmployeeMock();
	}

	public EmployeeController(int a){
		if (a == 1){
			this.employeeRepository = new EmployeeMock();
		}
		else{
			this.employeeRepository = new EmployeeImpl();
		}


	}

	public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Boolean addEmployee(Employee employee) {
		return  this.employeeRepository.addEmployee(employee);
	}
	public boolean modifyDidacticFunction(DidacticFunction d, int i) {
		return employeeRepository.modifyEmployeeDidacticFunction(d,i);
	}
	
	public List<Employee> getEmployeesList() {
		return employeeRepository.getEmployeeList();
	}

	public List<Employee> getEmployeesListOrdered() {
		return employeeRepository.getEmployeeByCriteria();
	}

	
}
