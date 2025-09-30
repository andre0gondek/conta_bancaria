package com.conta_bancaria.domain.repository;

import com.conta_bancaria.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, String> {
    Optional<Conta> findByNumAndAtivoTrue(String num);

    List<Conta> findAtiva();
}
