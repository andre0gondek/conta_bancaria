package com.conta_bancaria.application.service;

import com.conta_bancaria.domain.entity.Cliente;
import com.conta_bancaria.domain.exception.AutenticacaoIoTExpiradaException;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IoTService {
    @MqttPublisher("")
    public String solicitarAutenticacao(String idCliente) {
        // O retorno será publicado como payload no tópico
        return "Solicitação de autenticação para cliente " + idCliente;

    }
}
