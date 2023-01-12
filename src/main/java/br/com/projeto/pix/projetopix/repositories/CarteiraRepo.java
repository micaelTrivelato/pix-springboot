package br.com.projeto.pix.projetopix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.pix.projetopix.entities.Carteira;

public interface CarteiraRepo extends JpaRepository<Carteira, Long>{
    

    @Query("select c from Carteira c join c.chavePix cp where cp.id = :chavePix")
    List<Carteira> findByChavePix(@Param("chavePix") Long chavePix);

    @Query("select c from Carteira c join c.idPessoa ip where ip.id = :idPessoa")
    List<Carteira> findByIdPessoa(@Param("idPessoa") Long idPessoa);
}
