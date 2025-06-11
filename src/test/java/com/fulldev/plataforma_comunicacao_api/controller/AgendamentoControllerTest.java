package com.fulldev.plataforma_comunicacao_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.in.AgendamentoDTO;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.out.AgendamentoDTOOUT;
import com.fulldev.plataforma_comunicacao_api.infra.enums.StatusNotificacaoEnum;
import com.fulldev.plataforma_comunicacao_api.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    private MockMvc mockMvc;

    // ObjectMapper é inicializado aqui e o módulo JavaTimeModule é registrado no setUp.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    AgendamentoService agendamentoService;

    private AgendamentoDTO agendamentoDTO;
    private AgendamentoDTOOUT agendamentoDTOOUT;

    @BeforeEach
    void setUp() {
        // Configura o MockMvc para testar o AgendamentoController.
        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();
        // Registra o módulo JavaTimeModule para que o ObjectMapper possa serializar/deserializar LocalDateTime.
        objectMapper.registerModule(new JavaTimeModule());

        // Inicializa o DTO de entrada para o teste.
        agendamentoDTO = new AgendamentoDTO(
                1L, "email@email.com",
                "9988776644",
                "uma mensagem qualquer",
                LocalDateTime.of(2025, 01, 02, 11, 01), StatusNotificacaoEnum.AGENDADO); // Data e hora sem segundos

        // Inicializa o DTO de saída esperado para o teste.
        agendamentoDTOOUT = new AgendamentoDTOOUT(
                1L,
                "email@email.com",
                "9988776644",
                "uma mensagem qualquer",
                LocalDateTime.of(2025, 01, 02, 11, 01), // Data e hora sem segundos
                StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        // Configura o mock do serviço para retornar o DTO de saída esperado quando gravarAgendamento é chamado.
        when(agendamentoService.gravarAgendamento(agendamentoDTO)).thenReturn(agendamentoDTOOUT);

        // Executa a requisição POST para "/agendamento".
        mockMvc.perform(post("/agendamento") // 'post' agora vem de MockMvcRequestBuilders
                        .contentType(MediaType.APPLICATION_JSON) // Define o tipo de conteúdo da requisição como JSON.
                        .content(objectMapper.writeValueAsBytes(agendamentoDTO))) // Serializa o agendamentoDTO para bytes JSON e o define como corpo da requisição.
                .andExpect(status().isOk()) // Espera que o status da resposta seja HTTP 200 OK.
                .andExpect(jsonPath("$.id").value(1L)) // Verifica o valor do campo 'id' no JSON de resposta.
                .andExpect(jsonPath("$.emailDestinatario").value("email@email.com")) // Verifica o email.
                .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoDTOOUT.telefoneDestinatario())) // Verifica o telefone.
                .andExpect(jsonPath("$.mensagem").value(agendamentoDTOOUT.mensagem())) // Verifica a mensagem.
                // CORREÇÃO: A serialização padrão de LocalDateTime com JavaTimeModule é ISO 8601 (e.g., "YYYY-MM-DDTHH:MM:SS").
                // O .of(ano, mes, dia, hora, minuto) não define segundos, então eles serão "00".
                .andExpect(jsonPath("$.dataHoraEnvio").value(agendamentoDTOOUT.dataHoraEnvio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO")); // Verifica o status da notificação.

        // Verifica se o método gravarAgendamento do serviço foi chamado exatamente uma vez com o agendamentoDTO correto.
        verify(agendamentoService, times(1)).gravarAgendamento(agendamentoDTO);
    }
}
