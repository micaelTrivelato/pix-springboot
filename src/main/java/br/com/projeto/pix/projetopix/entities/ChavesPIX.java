package br.com.projeto.pix.projetopix.entities;

import br.com.projeto.pix.projetopix.dto.ChavesPIXDTO;
import br.com.projeto.pix.projetopix.enums.TipoChave;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="chaves_pix_id_seq", initialValue=6, allocationSize=1)
public class ChavesPIX {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="chaves_pix_id_seq")
    private Long id;

    @Enumerated()
    private TipoChave tipoChave;

    @Column(unique=true)
    private String descricao;
    
    public ChavesPIX(ChavesPIXDTO chavesPIXDTO){
        this.id = chavesPIXDTO.getId();
        this.tipoChave = chavesPIXDTO.getTipoChave();
        this.descricao = chavesPIXDTO.getDescricao();
    }
}
