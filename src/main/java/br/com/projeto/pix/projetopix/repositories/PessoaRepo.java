package br.com.projeto.pix.projetopix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.pix.projetopix.entities.Pessoa;

public interface PessoaRepo extends JpaRepository<Pessoa, Long> {

    /**
    @Query("SELECT p FROM Pessoa p WHERE p.status = :status and p.nome = :nome")
    Pessoa findUserByStatusAndNameNamedParams(@Param("status") Integer status,
                                                @Param("nome") String nome);
     */
}
