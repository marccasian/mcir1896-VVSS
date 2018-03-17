package salariati.repository.implementations;

import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImpl implements EmployeeRepositoryInterface {
	
	private final String employeeDBFile = "employeeDB/employees.txt";
	private EmployeeValidator employeeValidator = new EmployeeValidator();

	@Override
	public boolean addEmployee(Employee employee) {
		if( employeeValidator.isValid(employee) ) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
				for (Employee e : this.getEmployeeList()){
					if (e.getCnp() == employee.getCnp()){
						throw new EmployeeException("Employee already in system");
					}
				}

				bw.write(employee.toString());
				bw.newLine();
				bw.close();
				return true;
			} catch (IOException e) {
				System.out.println("Error occurred while trying to add new employee");
				e.printStackTrace();
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee = new Employee();
				try {
					employee = Employee.getEmployeeFromString(line, counter);
					employeeList.add(employee);
					counter += 1;
				} catch(EmployeeException ex) {
					System.err.println("Error while reading: " + ex.toString());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while reading: " + e);
		} catch (IOException e) {
			System.err.println("Error while reading: " + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error while closing the file: " + e);
				}
		}
		
		return employeeList;
	}


	@Override
	public List<Employee> getEmployeeByCriteria(String criteria) {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		return employeeList;
	}

}
