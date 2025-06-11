package com.fulldev.plataforma_comunicacao_api.infra.repositories;

import com.fulldev.plataforma_comunicacao_api.infra.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
