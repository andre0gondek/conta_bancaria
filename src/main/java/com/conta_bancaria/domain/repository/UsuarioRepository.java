package com.conta_bancaria.domain.repository;

import com.conta_bancaria.domain.entity.Gerente;
import com.conta_bancaria.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
