package br.com.invext.invext_app.controllers;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.dtos.TicketResponse;
import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.enums.TicketType;
import br.com.invext.invext_app.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TicketService ticketService;

    @Test
    void shouldReturnBadRequestWhenRequestBodyIsMissingRequiredFields() throws Exception {
        mockMvc.perform(post("/v1/tickets")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(new TicketRequest())))
                .andExpect(status().is4xxClientError());

        verify(ticketService, never()).create(any());
    }

    @Test
    void shouldCreateNewTicket() throws Exception {
        when(ticketService.create(any(TicketRequest.class)))
                .thenReturn(this.createTicketResponse());

        mockMvc.perform(post("/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(this.createTicketRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("desc"))
                .andExpect(jsonPath("$.ticketType").value("CARDS"))
                .andExpect(jsonPath("$.ticketStatus").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    private TicketRequest createTicketRequest() {
        var ticketRequest = new TicketRequest();
        ticketRequest.setDescription("desc");
        ticketRequest.setTicketType(TicketType.CARDS);
        return ticketRequest;
    }

    private TicketResponse createTicketResponse() {
        var ticketResponse = new TicketResponse();
        ticketResponse.setId("1");
        ticketResponse.setDescription("desc");
        ticketResponse.setTicketType(TicketType.CARDS);
        ticketResponse.setTicketStatus(TicketStatus.IN_PROGRESS);
        ticketResponse.setCreatedAt(LocalDateTime.now());
        return ticketResponse;
    }
}