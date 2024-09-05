package br.com.invext.invext_app.services;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.dtos.TicketResponse;
import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.models.Employee;
import br.com.invext.invext_app.models.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    private final static Queue<Ticket> TICKET_QUEUE = new LinkedList<>();

    private final static Map<Long, Integer> employeeCountTicketsCache = new HashMap<>();

    private final EmployeeService employeeService;

    public TicketService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public TicketResponse create(TicketRequest ticketRequest) {
        var ticket = TicketService.toModel(ticketRequest);
        var employees = employeeService.getEmployeesBy(ticket.getTicketType());

        employees.stream()
                .filter(TicketService::filterAvailableEmployee)
                .findFirst()
                .ifPresentOrElse(e -> {
                            log.info("Ticket {} attached to employee {}", ticket, e);
                            employeeCountTicketsCache.compute(e.getId(), (id, count) -> count == null || count == 0 ? 1 : count + 1);
                        },
                        () -> {
                            log.info("All employees has 3 tickets IN_PROGRESS sending the ticket to TICKETS_QUEUE");
                            TICKET_QUEUE.add(ticket);
                        });

        log.info("employee count tickets IN_PROGRESS cache {}", employeeCountTicketsCache);
        log.info("tickets queue {}", TICKET_QUEUE);

        return TicketService.toResponse(ticket);
    }

    private static Ticket toModel(TicketRequest ticketRequest) {
        var ticket = new Ticket();
        BeanUtils.copyProperties(ticketRequest, ticket);
        ticket.setId(UUID.randomUUID().toString());
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
        return ticket;
    }

    private static boolean filterAvailableEmployee(Employee employee) {
        var employeeTicketsCount = employeeCountTicketsCache.get(employee.getId());
        return Objects.isNull(employeeTicketsCount) || employeeTicketsCount < 3;
    }

    public static TicketResponse toResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        BeanUtils.copyProperties(ticket, response);
        return response;
    }

}
