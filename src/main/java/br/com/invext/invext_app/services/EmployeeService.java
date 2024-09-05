package br.com.invext.invext_app.services;

import br.com.invext.invext_app.enums.TicketType;
import br.com.invext.invext_app.models.Employee;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeService {

    private final static Map<TicketType, Set<Employee>> employees = new HashMap<>();

    static {
        var cardEmployees = Set.of(new Employee(1L, "Renato"), new Employee(2L, "Mateus"));
        employees.put(TicketType.CARDS, cardEmployees);

        var loanEmployees = Set.of(new Employee(3L, "Maria"), new Employee(4L, "Joana"));
        employees.put(TicketType.LOANS, loanEmployees);

        var otherTopicsEmployees = Set.of(new Employee(5L, "João"), new Employee(6L, "Alberto"));
        employees.put(TicketType.OTHERS, otherTopicsEmployees);
    }

    public Set<Employee> getEmployeesBy(TicketType ticketType) {
        return employees.get(ticketType);
    }

}
