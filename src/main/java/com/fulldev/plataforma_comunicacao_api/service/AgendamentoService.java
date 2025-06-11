package com.fulldev.plataforma_comunicacao_api.service;

import com.fulldev.plataforma_comunicacao_api.controller.dtos.in.AgendamentoDTO;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.out.AgendamentoDTOOUT;
import com.fulldev.plataforma_comunicacao_api.exceptions.NotFoundException;
import com.fulldev.plataforma_comunicacao_api.infra.repositories.AgendamentoRepository;
import com.fulldev.plataforma_comunicacao_api.service.mapper.IAgendamentoMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.fulldev.plataforma_comunicacao_api.infra.entities.Agendamento;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

        private final AgendamentoRepository repository;
        private final IAgendamentoMapper agendamentoMapper;



        public AgendamentoDTOOUT gravarAgendamento(AgendamentoDTO agendamento){
                return agendamentoMapper.paraOut(
                        repository.save(agendamentoMapper.paraEntity(agendamento)));
        }

        public AgendamentoDTOOUT buscarAgendamentoPorId(Long id) {
                return agendamentoMapper.paraOut(repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("id não encontrado")));
        }

        public void cancelarAgendamento(Long id) {
                Agendamento agendamento = repository.findById(id).orElseThrow(() -> new NotFoundException("id não encontrado"));

                repository.save(agendamentoMapper.paraEntityCancelamento(agendamento));
        }
}
