package br.edu.infnet.lucas.santos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lucas.santos.model.domain.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Integer> {

}
