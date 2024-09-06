package br.com.invext.invext_app.services;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.enums.TicketType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

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

    private TicketRequest createTicketRequest() {
        var ticketRequest = new TicketRequest();
        ticketRequest.setDescription("desc");
        ticketRequest.setTicketType(TicketType.CARDS);
        return ticketRequest;
    }
}