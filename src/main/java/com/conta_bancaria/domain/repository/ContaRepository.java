package com.conta_bancaria.domain.repository;

import com.conta_bancaria.application.dto.ContaResumoDTO;
import com.conta_bancaria.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {
    Optional<Conta> findByNum(String num);

    List<Conta> findAtiva();
}
