package com.fulldev.plataforma_comunicacao_api.service.mapper;

import com.fulldev.plataforma_comunicacao_api.controller.dtos.in.AgendamentoDTO;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.out.AgendamentoDTOOUT;
import com.fulldev.plataforma_comunicacao_api.infra.entities.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoDTO agendamento);
    AgendamentoDTOOUT paraOut(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);



}
