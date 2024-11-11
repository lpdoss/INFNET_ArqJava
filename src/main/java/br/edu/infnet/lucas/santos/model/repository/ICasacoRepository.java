package br.edu.infnet.lucas.santos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lucas.santos.model.domain.Casaco;

@Repository
public interface ICasacoRepository extends JpaRepository<Casaco, Integer> {

}
