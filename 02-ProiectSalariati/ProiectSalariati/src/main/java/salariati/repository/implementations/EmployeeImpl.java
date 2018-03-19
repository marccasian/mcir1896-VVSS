package salariati.repository.implementations;

import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeImpl implements EmployeeRepositoryInterface {
	
	private final String employeeDBFile = "D:\\GIT\\An3\\Sem2\\VVSS\\02-ProiectSalariati\\ProiectSalariati\\src\\main\\java\\salariati\\repository\\employees.txt";
	private EmployeeValidator employeeValidator = new EmployeeValidator();
    private List<Employee> employeeList;

    public EmployeeImpl() {
        this.employeeList = this.getEmployeeList();
    }



	@Override
	public boolean addEmployee(Employee employee) {
		if( employeeValidator.isValid(employee) ) {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
				for (Employee e : this.employeeList){
					if (e.getCnp().equals(employee.getCnp())){
						throw new EmployeeException("Employee already in system");
					}
				}

				bw.write(employee.toString());
				bw.newLine();
				bw.close();
                this.employeeList.add(employee);
				return true;
			} catch (IOException e) {
				System.out.println("Error occurred while trying to add new employee");
				e.printStackTrace();
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}

		}else{
            System.out.println("Not valid");

        }
		return false;
	}

	@Override
	public List<Employee> getEmployeeList() {
		this.employeeList = new ArrayList<Employee>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee = new Employee();
				try {
					employee = Employee.getEmployeeFromString(line, counter);
					this.employeeList.add(employee);
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
		
		return this.employeeList;
	}


	@Override
	public List<Employee> getEmployeeByCriteria() {
        System.out.println(this.employeeList.size());
        Collections.sort(this.employeeList);
        System.out.println(this.employeeList.size());
		return this.employeeList;
	}

	@Override
	public void modifyEmployeeDidacticFunction(DidacticFunction d, int i) {
		this.employeeList.get(i).setFunction(d);
	}
}
