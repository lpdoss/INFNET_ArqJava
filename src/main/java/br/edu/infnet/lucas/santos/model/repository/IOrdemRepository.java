package br.edu.infnet.lucas.santos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lucas.santos.model.domain.OrdemCompra;

@Repository
public interface IOrdemRepository extends JpaRepository<OrdemCompra, Integer> {
	
	@Query(value = "SELECT * FROM TBL_ORDEM WHERE ID_CLIENTE=?",nativeQuery = true)
    public List<OrdemCompra> findByClienteId(Integer idCliente);
}
