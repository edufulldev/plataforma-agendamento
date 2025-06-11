package com.fulldev.plataforma_comunicacao_api.service;

import com.fulldev.plataforma_comunicacao_api.controller.dtos.in.AgendamentoDTO;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.out.AgendamentoDTOOUT;
import com.fulldev.plataforma_comunicacao_api.infra.entities.Agendamento;
import com.fulldev.plataforma_comunicacao_api.infra.enums.StatusNotificacaoEnum;
import com.fulldev.plataforma_comunicacao_api.infra.repositories.AgendamentoRepository;
import com.fulldev.plataforma_comunicacao_api.service.mapper.IAgendamentoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;
    private AgendamentoDTO agendamentoDTO;
    private AgendamentoDTOOUT agendamentoDTOOUT;
    private Agendamento agendamentoEntity;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper iAgendamentoMapper;

    @BeforeEach
    void setUp() {
        // Dados de entrada
        agendamentoDTO = new AgendamentoDTO(
                1L, "email@email.com",
                "55887996578",
                "Favor retornar a loja com urgência",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificacaoEnum.AGENDADO);
        agendamentoEntity = Agendamento.builder()
                .id(1L)
                .dataHoraEnvio( LocalDateTime.of(2025, 1, 2, 11, 1, 1))
                .emailDestinatario("email@email.com")
                .telefoneDestinatario("55887996578")
                .mensagem("Favor retornar a loja com urgência")
                .dataHoraAgendamento(LocalDateTime.now())
                .build();

        agendamentoDTO = new AgendamentoDTO(
                1L,
                "email@email.com",
                "55887996578",
                "Favor retornar a loja com urgência",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificacaoEnum.AGENDADO);
    }
    @Test
    void deveGravarAgendamentoSucesso() {
        when(iAgendamentoMapper.paraEntity(agendamentoDTO)).thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(iAgendamentoMapper.paraOut(agendamentoEntity)).thenReturn(agendamentoDTOOUT);

        AgendamentoDTOOUT result = agendamentoService.gravarAgendamento(agendamentoDTO);

        assertEquals(agendamentoDTOOUT, result, "retorno do objeto igual ao esperado");
    }

}
