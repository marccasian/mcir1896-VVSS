package salariati.main;

import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//functionalitati
//i.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//ii.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//iii.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).
public class StartApp {
	public static void main(String[] args)
	{
		EmployeeController employeeController = new EmployeeController(3);
		while(true){
			System.out.println("---> Meniu <---");
			System.out.println("1. Add Employee");
			System.out.println("2. Modify didactic function");
			System.out.println("3. Print order by salary and birthday");
			System.out.println("0. Exit");
			String input = null;
			int number = 0;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			number = Integer.parseInt(input);
			if (number == 0) {
				break;
			}
			else if (number == 1){
				try {
					String name = bufferedReader.readLine();
					String cnp = bufferedReader.readLine();
					String function = bufferedReader.readLine();
					String salary = bufferedReader.readLine();
					DidacticFunction f;
					Double sal = Double.parseDouble(salary);
					f = get_did_fct(function);
					Boolean b = employeeController.addEmployee(new Employee(name,cnp,f,sal));
                    System.out.println(b);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (number == 2){
				try {
					String inp = bufferedReader.readLine();
					Integer nr = Integer.parseInt(inp);
					String funct = bufferedReader.readLine();
					DidacticFunction f;
					f = get_did_fct(funct);
                    employeeController.modifyDidacticFunction(f,nr);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (EmployeeException e) {
					System.out.println(e.getMessage());
				}

			}
			else if (number == 3){
                for(Employee _employee : employeeController.getEmployeesListOrdered())
			        System.out.println(_employee.toString());
			}
			else{
				System.out.println("Incorrect option");
			}
		}
		
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//		System.out.println("-----------------------------------------");
//
//		Employee employee = new Employee("LastName", "1234567894321", DidacticFunction.ASISTENT, 2500.0);
//		employeeController.addEmployee(employee);
//
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//
//		EmployeeValidator validator = new EmployeeValidator();
//		System.out.println( validator.isValid(new Employee("LastName", "1234567894322", DidacticFunction.TEACHER, 3400.0)) );
//
	}

	private static DidacticFunction get_did_fct(String funct) throws EmployeeException {
		DidacticFunction f;
		if(funct.equals("ASISTENT"))
            f = DidacticFunction.ASISTENT;
        else if(funct.equals("LECTURER"))
            f = DidacticFunction.LECTURER;
        else if(funct.equals("TEACHER"))
            f = DidacticFunction.TEACHER;
        else if(funct.equals("CONF"))
            f = DidacticFunction.CONF;
        else
            throw new EmployeeException("Invalid function...");

        return f;
	}

}
