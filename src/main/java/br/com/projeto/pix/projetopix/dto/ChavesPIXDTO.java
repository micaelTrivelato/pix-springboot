package br.com.projeto.pix.projetopix.dto;

import br.com.projeto.pix.projetopix.entities.ChavesPIX;
import br.com.projeto.pix.projetopix.enums.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChavesPIXDTO {

    private Long id;
    private TipoChave tipoChave; 
    private String descricao;
    
    public ChavesPIXDTO(ChavesPIX chavesPIX){
        this.id = chavesPIX.getId();
        this.tipoChave = chavesPIX.getTipoChave();
        this.descricao = chavesPIX.getDescricao();
    }
}
