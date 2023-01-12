package br.com.projeto.pix.projetopix.entities;

import java.util.List;

import br.com.projeto.pix.projetopix.dto.CarteiraDTO;
import br.com.projeto.pix.projetopix.enums.Situacao;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="carteira_id_seq", initialValue=3, allocationSize=100)
public class Carteira {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carteira_id_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idPessoa",nullable=false)
    private Pessoa idPessoa;
    
    private Double saldo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "chavePix",nullable=false)
    private List<ChavesPIX> chavePix;

    private Situacao situacao;

    public Carteira(CarteiraDTO carteiraDTO){
        this.id = carteiraDTO.getId();
        this.idPessoa = carteiraDTO.getIdPessoa();
        this.saldo = carteiraDTO.getSaldo();
        this.chavePix = carteiraDTO.getChavePix();
        this.situacao = carteiraDTO.getSituacao();
    }

}
