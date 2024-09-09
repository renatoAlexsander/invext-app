package br.com.invext.invext_app.services;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.dtos.TicketResponse;
import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.enums.TicketType;
import br.com.invext.invext_app.models.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private EmployeeService employeeService;

    @Test
    void shouldThrowNotFoundWhenEmployeeNotFound() {
        when(employeeService.getEmployeesBy(TicketType.CARDS)).thenReturn(Set.of());

        var responseStatusException = assertThrows(ResponseStatusException.class, () -> ticketService.create(this.createTicketRequest()));
        assertEquals(HttpStatusCode.valueOf(404), responseStatusException.getStatusCode());
        assertEquals("Not found any employees for that ticket type", responseStatusException.getReason());
    }

    @Test
    void shouldCreateTickedWithSuccess() {
        var employees = Set.of(
                new Employee(1L, "Renato"),
                new Employee(2L, "João")
        );

        when(employeeService.getEmployeesBy(TicketType.CARDS))
                .thenReturn(employees);

        var ticketResponse = ticketService.create(this.createTicketRequest());

        assertNotNull(ticketResponse);
        assertNotNull(ticketResponse.getId());
        assertEquals(TicketType.CARDS, ticketResponse.getTicketType());
        assertEquals(TicketStatus.IN_PROGRESS, ticketResponse.getTicketStatus());
        assertNotNull(ticketResponse.getCreatedAt());
        assertEquals("desc", ticketResponse.getDescription());

        var employeeCountTicketsCache = ticketService.getEmployeeCountTicketsCache();

        assertEquals(1, employeeCountTicketsCache.size());
        assertEquals(0, ticketService.getTicketQueue().size());
    }

    private TicketRequest createTicketRequest() {
        var ticketRequest = new TicketRequest();
        ticketRequest.setDescription("desc");
        ticketRequest.setTicketType(TicketType.CARDS);
        return ticketRequest;
    }
}