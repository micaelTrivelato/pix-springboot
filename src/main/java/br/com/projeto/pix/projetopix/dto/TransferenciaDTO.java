package br.com.projeto.pix.projetopix.dto;

import java.time.LocalDateTime;

import br.com.projeto.pix.projetopix.entities.Carteira;
import br.com.projeto.pix.projetopix.entities.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {

    private Long id;
    private Carteira idCarteiraPagadora;
    private Carteira idCarteiraRecebedora;
    private Double valor;
    private LocalDateTime data;
    
    public TransferenciaDTO(Transferencia transferencia){
        this.id = transferencia.getId();
        this.idCarteiraPagadora = transferencia.getIdCarteiraPagadora();
        this.idCarteiraRecebedora = transferencia.getIdCarteiraRecebedora();
        this.valor = transferencia.getValor();
        this.data = transferencia.getData();
    }
}
