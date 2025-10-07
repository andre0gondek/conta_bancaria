package com.conta_bancaria.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade + "não existente/inativo");
    }
}
