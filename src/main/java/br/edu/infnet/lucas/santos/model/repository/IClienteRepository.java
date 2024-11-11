package br.edu.infnet.lucas.santos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lucas.santos.model.domain.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
	Cliente findOneByEmail(String email);
}
