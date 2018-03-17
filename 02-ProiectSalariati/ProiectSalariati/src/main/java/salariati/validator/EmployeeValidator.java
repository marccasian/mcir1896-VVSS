package salariati.validator;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;


public class EmployeeValidator {
	
	public EmployeeValidator(){}

	public boolean isValid(Employee employee) {
		boolean isLastNameValid  = employee.getLastName().matches("[a-zA-Z]+") && (employee.getLastName().length() > 2);
		boolean isCNPValid       = CNP.validate(employee.getCnp());
		boolean isFunctionValid  = employee.getFunction().equals(DidacticFunction.ASISTENT) ||
								   employee.getFunction().equals(DidacticFunction.LECTURER) ||
								   employee.getFunction().equals(DidacticFunction.TEACHER);
		boolean isSalaryValid    =  employee.getSalary() > 0;
		
		return isLastNameValid && isCNPValid && isFunctionValid && isSalaryValid;
	}

	
}
