package com.fulldev.plataforma_comunicacao_api.controller.dtos.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record AgendamentoDTO(
        long l, String emailDestinatario,
        String telefoneDestinatario,
        String mensagem,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataHoraEnvio,
        com.fulldev.plataforma_comunicacao_api.infra.enums.StatusNotificacaoEnum agendado) {

}
