package br.com.projeto.pix.projetopix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.pix.projetopix.entities.ChavesPIX;

public interface ChavesPIXRepo extends JpaRepository<ChavesPIX, Long>{

    List<ChavesPIX> findByDescricao(String descricao);

    @Modifying
    @Query("DELETE FROM ChavesPIX c where c.descricao = :descricao")
    void deleteByDescricao(@Param("descricao")String descricao);
    
}
