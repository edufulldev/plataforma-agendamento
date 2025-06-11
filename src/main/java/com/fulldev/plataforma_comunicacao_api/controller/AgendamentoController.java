package com.fulldev.plataforma_comunicacao_api.controller;

import com.fulldev.plataforma_comunicacao_api.controller.dtos.in.AgendamentoDTO;
import com.fulldev.plataforma_comunicacao_api.controller.dtos.out.AgendamentoDTOOUT;
import com.fulldev.plataforma_comunicacao_api.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
//
//    public AgendamentoController(AgendamentoService agendamentoService) {
//        this.agendamentoService = agendamentoService;
//    }

    @PostMapping
    public ResponseEntity<AgendamentoDTOOUT> gravarAgendamentos(@RequestBody AgendamentoDTO agendamento) {
        return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTOOUT> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> cancelarAgendamento(@PathVariable("id") Long id) {
        agendamentoService.cancelarAgendamento(id);
        return  ResponseEntity.accepted().build();
    }
}
