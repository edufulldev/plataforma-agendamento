package com.fulldev.plataforma_comunicacao_api.controller.dtos.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fulldev.plataforma_comunicacao_api.infra.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record AgendamentoDTOOUT(
        Long id,
        String emailDestinatario,
        String telefoneDestinatario,
        String mensagem,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataHoraEnvio,
        StatusNotificacaoEnum statusNotificacao
    ) {
}
