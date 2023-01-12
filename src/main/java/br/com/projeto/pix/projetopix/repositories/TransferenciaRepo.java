package br.com.projeto.pix.projetopix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.pix.projetopix.entities.Transferencia;

public interface TransferenciaRepo extends JpaRepository<Transferencia, Long>{
    
    @Query("select t from Transferencia t where t.idCarteiraPagadora.id = :id")
    List<Transferencia> findByIdCarteiraPagadora(@Param("id") Long id);
}
