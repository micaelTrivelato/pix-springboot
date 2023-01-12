package br.com.projeto.pix.projetopix.dto;

import java.util.List;

import br.com.projeto.pix.projetopix.entities.Carteira;
import br.com.projeto.pix.projetopix.entities.ChavesPIX;
import br.com.projeto.pix.projetopix.entities.Pessoa;
import br.com.projeto.pix.projetopix.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraDTO {

    private Long id;
    private Pessoa idPessoa;
    private Double saldo;
    private List<ChavesPIX> chavePix;
    private Situacao situacao;
    
    public CarteiraDTO(Carteira carteira){
        this.id = carteira.getId();
        this.idPessoa = carteira.getIdPessoa();
        this.saldo = carteira.getSaldo();
        this.chavePix = carteira.getChavePix();
        this.situacao = carteira.getSituacao();
    }
}
