# Plataforma de Comunicação

## 📌 Objetivo

Desenvolver uma plataforma de comunicação com funcionalidades de **agendamento**, **consulta** e **cancelamento** de envios de mensagens via diferentes canais.

## 📦 Funcionalidades

- **Agendamento de Envio**
- **Consulta do Status do Envio**
- **Cancelamento do Envio**

## 📨 Formatos de Comunicação Suportados

- Email
- SMS
- Push Notification
- WhatsApp

## 🛠️ Tecnologias Utilizadas

- RESTful API (padrão JSON)
- Docker
- Testes unitários com suites organizadas e automatizadas

## 📱 Endpoints da API

### 🔹 POST `/agendamentos`

Agendar o envio de uma comunicação.

**Request Body:**
```json
{
  "dataHoraEnvio": "2025-06-12T15:00:00Z",
  "emailDestinatario": "exemplo@dominio.com",
  "telefoneDestinatario": "+5511999999999",
  "mensagem": "Sua mensagem aqui",
  "statusAgenda": "AGENDADO"
}

🔹 GET /agendamentos/{id}
Consultar o status de um agendamento pelo ID.

🔹 DELETE /agendamentos/{id}
Cancelar um agendamento de envio, alterando o status para CANCELADO.

🔄 Status da Agenda
AGENDADO

ENVIADO

CANCELADO

🧪 Testes
O projeto possui testes unitários automatizados e organizados em suites de teste, cobrindo as principais funcionalidades da API.

OBSERVAÇÃO: na hora de rodar pode ser que quebre algumas dependencias do Spring só ir no chatGPT ou gemini e pedir para gerar a dependencia atual isso pode acontecer nos testes tbm 
