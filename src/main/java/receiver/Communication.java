package receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import receiver.entity.Employee;

import java.util.List;

/**
 * @author ArvikV
 * @version 1.0
 * @since 12.03.2022
 */
@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8081/restex/api/employees";

    /**
     *
     * @return
     * ResponseEntity обертка хттп респонз, в дженериках листемплои
     * 1 параметр юрл(осуществляем реквест по этому адресу)
     * 2 параметр метод
     * 3 реквест ентити в теле(у гет тело пустое)
     * 4 ParameterizedTypeReference - вспомогательный класс цель которого передача дженерик типа(список емплои)
     *
     */
    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });
        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    /**
     *
     * @param employee
     * ResponseEntity<String> стринг потому что возвращаем джсон в виде стринги
     */
    public void saveEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added to db");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("employee with id " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with id " + id + " was deleted!");
    }
}
