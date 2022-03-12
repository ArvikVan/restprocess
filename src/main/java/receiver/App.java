package receiver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import receiver.configuration.MyConfig;
import receiver.entity.Employee;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        /**
         * создаем контекст в который передаем конф.класс
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> allEmployee = communication.getAllEmployees();
        System.out.println(allEmployee);
        Employee employeeById = communication.getEmployee(2);
        System.out.println(employeeById);
        Employee emp = new Employee("Sveta", "Ivanova", "HR", 900);
        communication.saveEmployee(emp);
        communication.deleteEmployee(2);
    }
}
